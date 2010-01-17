/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
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

package net.portalteam.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FormSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormSoap implements Serializable {
	public static FormSoap toSoapModel(Form model) {
		FormSoap soapModel = new FormSoap();

		soapModel.setFormId(model.getFormId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreateUserId(model.getCreateUserId());
		soapModel.setModUserId(model.getModUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setTitle(model.getTitle());
		soapModel.setName(model.getName());
		soapModel.setEmail(model.getEmail());
		soapModel.setLetterSubject(model.getLetterSubject());
		soapModel.setLetterTemplate(model.getLetterTemplate());
		soapModel.setSendButtonTitle(model.getSendButtonTitle());
		soapModel.setResetButtonTitle(model.getResetButtonTitle());
		soapModel.setShowResetButton(model.getShowResetButton());
		soapModel.setCaptcha(model.getCaptcha());
		soapModel.setStructure(model.getStructure());
		soapModel.setRenderTemplate(model.getRenderTemplate());

		return soapModel;
	}

	public static FormSoap[] toSoapModels(Form[] models) {
		FormSoap[] soapModels = new FormSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FormSoap[][] toSoapModels(Form[][] models) {
		FormSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FormSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FormSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FormSoap[] toSoapModels(List<Form> models) {
		List<FormSoap> soapModels = new ArrayList<FormSoap>(models.size());

		for (Form model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FormSoap[soapModels.size()]);
	}

	public FormSoap() {
	}

	public long getPrimaryKey() {
		return _formId;
	}

	public void setPrimaryKey(long pk) {
		setFormId(pk);
	}

	public long getFormId() {
		return _formId;
	}

	public void setFormId(long formId) {
		_formId = formId;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public long getCreateUserId() {
		return _createUserId;
	}

	public void setCreateUserId(long createUserId) {
		_createUserId = createUserId;
	}

	public long getModUserId() {
		return _modUserId;
	}

	public void setModUserId(long modUserId) {
		_modUserId = modUserId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	public String getLetterSubject() {
		return _letterSubject;
	}

	public void setLetterSubject(String letterSubject) {
		_letterSubject = letterSubject;
	}

	public String getLetterTemplate() {
		return _letterTemplate;
	}

	public void setLetterTemplate(String letterTemplate) {
		_letterTemplate = letterTemplate;
	}

	public String getSendButtonTitle() {
		return _sendButtonTitle;
	}

	public void setSendButtonTitle(String sendButtonTitle) {
		_sendButtonTitle = sendButtonTitle;
	}

	public String getResetButtonTitle() {
		return _resetButtonTitle;
	}

	public void setResetButtonTitle(String resetButtonTitle) {
		_resetButtonTitle = resetButtonTitle;
	}

	public boolean getShowResetButton() {
		return _showResetButton;
	}

	public boolean isShowResetButton() {
		return _showResetButton;
	}

	public void setShowResetButton(boolean showResetButton) {
		_showResetButton = showResetButton;
	}

	public boolean getCaptcha() {
		return _captcha;
	}

	public boolean isCaptcha() {
		return _captcha;
	}

	public void setCaptcha(boolean captcha) {
		_captcha = captcha;
	}

	public String getStructure() {
		return _structure;
	}

	public void setStructure(String structure) {
		_structure = structure;
	}

	public String getRenderTemplate() {
		return _renderTemplate;
	}

	public void setRenderTemplate(String renderTemplate) {
		_renderTemplate = renderTemplate;
	}

	private long _formId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _createUserId;
	private long _modUserId;
	private long _companyId;
	private String _title;
	private String _name;
	private String _email;
	private String _letterSubject;
	private String _letterTemplate;
	private String _sendButtonTitle;
	private String _resetButtonTitle;
	private boolean _showResetButton;
	private boolean _captcha;
	private String _structure;
	private String _renderTemplate;
}