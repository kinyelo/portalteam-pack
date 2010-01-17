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

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import net.portalteam.model.Form;
import net.portalteam.model.FormConfig;
import net.portalteam.model.FormFile;
import net.portalteam.portlets.AbstractService;
import net.portalteam.portlets.ServiceResponse;
import net.portalteam.service.FormConfigLocalServiceUtil;
import net.portalteam.service.FormDataLocalServiceUtil;
import net.portalteam.service.FormFileLocalServiceUtil;
import net.portalteam.service.FormLocalServiceUtil;
import net.portalteam.service.impl.VelocityService;
import net.portalteam.util.ResourceUtil;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.VelocityContext;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.theme.ThemeDisplay;

public class FormServiceImpl extends AbstractService implements FormService {

	private static Log logger = LogFactoryUtil.getLog(FormServiceImpl.class);
	
	public FormServiceImpl(ThemeDisplay aThemeDisplay) {
		super(aThemeDisplay);
	}

	private List<String> validateForm(Form form) {
		List<String> errors = new ArrayList<String>();
		try {
			if (!StringUtils.isEmpty(form.getStructure())) {
				DocumentHelper.parseText(form.getStructure());
			}
		}
		catch (DocumentException e) {
			errors.add("Problems when parsing XML structure. " + e.getMessage());
		}
		return errors;
	}
	
	@Override
	public ServiceResponse save(Map<String, String> vo) throws SystemException, 
			PortalException {
		Form form = null;
		if (!StringUtils.isEmpty(vo.get("id"))) {
			form = FormLocalServiceUtil.getForm(Long.valueOf(vo.get("id")));
		}
		if (form == null) {
			form = FormLocalServiceUtil.createForm();
		}
		form.setCompanyId(getThemeDisplay().getCompanyId());
		form.setTitle(vo.get("title"));
		form.setName(vo.get("name"));
		form.setEmail(vo.get("email"));
		form.setLetterSubject(vo.get("letterSubject"));
		form.setSendButtonTitle(vo.get("sendButtonTitle"));
		form.setResetButtonTitle(vo.get("resetButtonTitle"));
		form.setShowResetButton(Boolean.valueOf(vo.get("showResetButton")));
		form.setCaptcha(Boolean.valueOf(vo.get("captcha")));
		if (StringUtils.isEmpty(vo.get("letterTemplate"))) {
			form.setLetterTemplate(getDefaultLetterTemplate());		
		}
		else {
			form.setLetterTemplate(vo.get("letterTemplate"));
		}
		if (StringUtils.isEmpty(vo.get("renderTemplate"))) {
			form.setRenderTemplate(getDefaultRenderTemplate());
		}
		else {
			form.setRenderTemplate(vo.get("renderTemplate"));
		}
		form.setStructure(vo.get("structure"));
		List<String> errors = validateForm(form);
		if (errors.isEmpty()) {
			FormLocalServiceUtil.updateForm(form);
			return ServiceResponse.createSuccessResponse(String.valueOf(
					form.getFormId()));
		}
		else {
			return ServiceResponse.createErrorsResponse(errors);
		}
	}

	@Override
	public List<Form> select() throws SystemException {
		return FormLocalServiceUtil.getForms(0, 
				FormLocalServiceUtil.getFormsCount());
	}

	@Override
	public Form getById(long id) throws PortalException, SystemException {
		return FormLocalServiceUtil.getForm(id);
	}

	@Override
	public ServiceResponse remove(List<String> ids) 
			throws NumberFormatException, PortalException, SystemException {
		for (String id : ids) {
			Form form = getById(Long.valueOf(id));
			if (form != null) {
				FormLocalServiceUtil.deleteForm(form);
			}
		}
		return ServiceResponse.createSuccessResponse(
				"Forms were successfully removed");
	}

	@Override
	public String getDefaultLetterTemplate() {
		try {
			return ResourceUtil.getTextResource(
					"net/portalteam/resources/form-letter.html");
		}
		catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public String getDefaultRenderTemplate() {
		try {
			return ResourceUtil.getTextResource(
					"net/portalteam/resources/form-template.html");
		}
		catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	@Override
	public List<FieldVO> getFields(long formId) {
		try {
			Form form = getById(formId);
			if (form == null) {
				return Collections.EMPTY_LIST;
			}
			return FieldVO.create(form.getStructure());
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			return Collections.EMPTY_LIST;
		}
	}

	private FormConfig getFormConfig() throws SystemException {
		return FormConfigLocalServiceUtil.getFormConfigByCompany(getCompanyId());
	}

	@Override
	public String renderForm(Form form) throws SystemException {
		List<FieldVO> fields = FieldVO.create(form.getStructure());
		VelocityContext context = new VelocityContext();
		context.put("formConfig", getFormConfig());
		context.put("form", form);
		context.put("fields", fields);
		if (StringUtils.isEmpty(form.getRenderTemplate())) {
			return "Error! Form render template is empty.";
		}
		return VelocityService.render(form.getRenderTemplate(), context);
	}

	@Override
	public FormConfig getConfig() throws SystemException {
		return FormConfigLocalServiceUtil.getFormConfigByCompany(getCompanyId());
	}

	@Override
	public ServiceResponse saveConfig(Map<String, String> vo) 
			throws SystemException {
		FormConfig config = getConfig();
		config.setEnabledRecaptcha(Boolean.valueOf(vo.get("enabledRecaptcha")));
		config.setRecaptchaPublicKey(vo.get("recaptchaPublicKey"));
		config.setRecaptchaPrivateKey(vo.get("recaptchaPrivateKey"));
		FormConfigLocalServiceUtil.updateFormConfig(config);
		return ServiceResponse.createSuccessResponse(
				"Form configuration was successfully saved.");
	}

	@Override
	public FormDataVO selectFormData(long formId) 
			throws PortalException, SystemException {
		Form form = getById(formId);
		if (form == null) {
			return null;
		}
		return new FormDataVO(getFields(formId),
				FormDataLocalServiceUtil.getByForm(formId));
	}

	@Override
	public List<FormFileVO> getFormDataFiles(long formDataId) 
			throws SystemException {
		List<FormFileVO> result = new ArrayList<FormFileVO>();
		List<FormFile> formFiles = FormFileLocalServiceUtil.getByFormData(formDataId);
		for (FormFile formFile : formFiles) {
			result.add(new FormFileVO(formFile));
		}
		return result;
	}

}
