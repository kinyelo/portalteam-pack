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

package net.portalteam.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.portalteam.model.Form;
import net.portalteam.model.FormConfig;
import net.portalteam.portlets.form.FormBusiness;
import net.portalteam.service.FormConfigLocalServiceUtil;
import net.portalteam.service.FormLocalServiceUtil;
import net.portalteam.util.RecaptchaUtil;
import net.portalteam.util.StreamUtil;
import net.tanesha.recaptcha.ReCaptchaResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.util.PortalUtil;

/**
 * Servlet for form submition in forms plugin.
 * 
 * @author Aleksandr Oleynik 
 */
public class FormSendServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactoryUtil.getLog(FormSendServlet.class);

	private static final long MAX_SIZE = 500000000;
	private static final String TEXT_MESSAGE = "{result:'%s', message:'%s'}";
	private static final String PARSE_REQUEST_ERROR = "Parse request error";
	private static final String FORM_ID_PARAM = "formId";

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String message = null;
		Map<String, String> parameters = new HashMap<String, String>();
		List<FileItem> files = new ArrayList<FileItem>();
		try {
			if (request.getContentType().startsWith("multipart/form-data")) {
				ServletFileUpload upload = new ServletFileUpload();
				upload.setFileSizeMax(MAX_SIZE);
				upload.setHeaderEncoding("UTF-8");
				FileItemIterator iter;
				try {
					iter = upload.getItemIterator(request);
					InputStream stream = null;
					while (iter.hasNext()) {
						FileItemStream item = iter.next();
						stream = item.openStream();
						if (item.isFormField()) {
							parameters.put(item.getFieldName(), 
								Streams.asString(stream, "UTF-8"));
						} else {
							files.add(new FileItem(item, 
								StreamUtil.readFileStream(stream)));
						}
					}
				} catch (FileUploadException e) {
					logger.error(e.getMessage());
					throw new UploadException(PARSE_REQUEST_ERROR);
				}
			}
			else {
				for (Object key : request.getParameterMap().keySet()) {
					String paramName = (String)key;
					parameters.put(paramName, request.getParameter(paramName));
				}
			}
			message = processForm(parameters, files, request);
		} catch (UploadException e) {
			message = createMessage("error", e.getMessage()); 
			logger.error(message);
		} catch (SystemException e) {
			message = createMessage("error", e.getMessage()); 
			logger.error(message);
			e.printStackTrace();
		}
		response.setContentType("text/html");
		response.setStatus(200);
		response.getWriter().write(message);
	}

	private String createMessage(final String result, final String message) {
		return String.format(TEXT_MESSAGE, result, message);
	}
	
	private String processForm(Map<String, String> parameters,
			List<FileItem> files, HttpServletRequest request) 
			throws UploadException, SystemException {
		try {
			long formId = Long.valueOf(parameters.get(FORM_ID_PARAM));
			Form form = FormLocalServiceUtil.getForm(formId);
			if (form == null) {
				throw new UploadException("Form with name " + formId 
					+ " was not found.");
			}
			long companyId = PortalUtil.getCompanyId(request);
			FormConfig config = FormConfigLocalServiceUtil.getFormConfigByCompany(
					companyId);
			String challenge = parameters.get("recaptcha_challenge_field");
			String response = parameters.get("recaptcha_response_field");
			if (form.isCaptcha() && config.isEnabledRecaptcha()) {
				ReCaptchaResponse recaptchaResponse = RecaptchaUtil.check(
					config.getRecaptchaPublicKey(), 
					config.getRecaptchaPrivateKey(), 
					challenge, response, request);
				if (!recaptchaResponse.isValid()) {
					return createMessage("error", 
						"Incorrect captcha solution.");
				}
			}
			FormBusiness formBusiness = new FormBusiness(companyId);
			formBusiness.submit(form, parameters, files, request);
			return createMessage("success", "Form was successfully submited.");
		}
		catch (UploadException e) {
			throw e;
		}
		catch (Exception e) {
			e.printStackTrace();
			throw new UploadException(e.getMessage());
		}
	}
	
}