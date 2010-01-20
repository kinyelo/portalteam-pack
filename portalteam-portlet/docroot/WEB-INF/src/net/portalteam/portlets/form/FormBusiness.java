/**
 * Copyright (c) 2010 PortalTeam.net All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package net.portalteam.portlets.form;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.servlet.http.HttpServletRequest;

import net.portalteam.model.Form;
import net.portalteam.model.FormConfig;
import net.portalteam.model.FormData;
import net.portalteam.model.FormFile;
import net.portalteam.service.FormConfigLocalServiceUtil;
import net.portalteam.service.FormDataLocalServiceUtil;
import net.portalteam.service.FormFileLocalServiceUtil;
import net.portalteam.service.impl.VelocityService;
import net.portalteam.servlet.FileItem;
import net.portalteam.servlet.FormSendServlet;
import net.portalteam.servlet.UploadException;
import net.portalteam.util.FileUtil;
import net.portalteam.util.MimeType;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.model.Company;
import com.liferay.portal.service.CompanyLocalServiceUtil;

public class FormBusiness {

	private static final Log logger = LogFactoryUtil.getLog(FormBusiness.class);

	private long companyId;
	
	public FormBusiness(long aCompanyId) {
		companyId = aCompanyId;
	}
	
	/**
	 * Save form data in the db and send letter.
	 * @param form
	 * @param parameters
	 * @param files
	 * @param request
	 * @throws UploadException
	 * @throws SystemException
	 */
	public void submit(Form form, Map<String, String> parameters,
			List<FileItem> files, HttpServletRequest request) 
			throws UploadException, SystemException {
		
		List<FieldVO> fields = FieldVO.create(form.getStructure());
		validateFiles(FieldVO.getIdMap(fields), files);
		FormData formData = FormDataLocalServiceUtil.createFormData(
				CounterLocalServiceUtil.increment());
		formData.setFormId(form.getFormId());
		Document doc = DocumentHelper.createDocument();
		Element root = doc.addElement("formData");
		for (FieldVO field : fields) {
			Element e = root.addElement(field.getName());
			String value = "";
			if (parameters.containsKey(field.getName())) {
				value = parameters.get(field.getName());
			}
			e.setText(value);
		}
		formData.setData(doc.asXML());
		FormDataLocalServiceUtil.updateFormData(formData);
		saveFiles(formData, files);
		if (!StringUtils.isEmpty(form.getEmail())) {
			sendSubmitLetter(form, formData);
		}
	}

	private static final int MB = 1024*1024;
	
	private void validateFiles(Map<String, FieldVO> fields,
			List<FileItem> files) 
			throws SystemException, UploadException {
		for (FileItem item : files) {
			FieldVO field = fields.get(item.getFieldName());
			if (field != null) {
				if (item.getData().length > field.getWidth() * MB) {
					logger.error("File is too large");
					throw new UploadException("File is too large.");
				}
			}
			else {
				logger.error("Field not found " + item.getFieldName());
			}
		}
	}
	
	private void saveFiles(FormData formData, List<FileItem> files) 
			throws SystemException {
		for (FileItem item : files) {
			FormFile formFile = FormFileLocalServiceUtil.createFormFile(
					CounterLocalServiceUtil.increment());
			formFile.setFormDataId(formData.getFormDataId());
			formFile.setFieldName(item.getFieldName());
			formFile.setFilename(item.getFilename());
			formFile.setMimeType(MimeType.getContentTypeByName(item.getFilename()));
			formFile.setDataObj(item.getData());
			FormFileLocalServiceUtil.updateFormFile(formFile);
		}
	}

	private Map<String, String> getFormDataMap(String xml) 
			throws DocumentException {
		Map<String, String> result = new HashMap<String, String>();
		Document doc = DocumentHelper.parseText(xml);
		Iterator<Element> iter = doc.getRootElement().elementIterator();
		while (iter.hasNext()) {
			Element e = iter.next();
			result.put(e.getName(), e.getText());
		}
		return result;
	}
	
	/**
	 * Send letter with form data and attachments if exist.
	 * @param form
	 * @param formData
	 * @throws UploadException
	 */
	public void sendSubmitLetter(Form form, FormData formData) 
			throws UploadException {
		try {
			VelocityContext context = new VelocityContext();
			List<FieldVO> fields = FieldVO.create(form.getStructure());
			Company company = CompanyLocalServiceUtil.getCompany(companyId);
			context.put("company", company);
			context.put("form", form);
			context.put("fields", fields);
			context.put("values", getFormDataMap(formData.getData()));
			String letter = VelocityService.render(
				form.getLetterTemplate(), context);
		
			InternetAddress toAddress = new InternetAddress(form.getEmail());
			String subject = form.getLetterSubject();
			InternetAddress fromAddress = new InternetAddress(
					getConfig().getEmailFromAddress());
	
			MailMessage mailMessage = new MailMessage(fromAddress, toAddress, 
					subject, letter, true);
			for (FieldVO field : fields) {
				if (field.getType().equals("FILE")) {
					mailMessage.addAttachment(getAttachment(formData, field));
				}
			}
			MailServiceUtil.sendEmail(mailMessage);
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UploadException(e.getMessage());
		}
	}
	
	private FormConfig getConfig() throws SystemException {
		return FormConfigLocalServiceUtil.getFormConfigByCompany(companyId);
	}
	
	private static File getAttachment(FormData formData, FieldVO field)  
			throws IOException, SystemException {
		FormFile formFile = FormFileLocalServiceUtil.getByFieldName(
				formData.getFormDataId(), field.getName());
		File file = File.createTempFile(
				FileUtil.getFilenameWithoutExtension(formFile.getFilename()),
				FileUtil.getFilenameExtension(formFile.getFilename()));
		OutputStream os = new BufferedOutputStream(new FileOutputStream(
				file.getPath()));
		os.write(formFile.getDataObj());
		os.close();
		return file;
	}
	
}
