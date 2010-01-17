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
 * <a href="FormConfigSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormConfigSoap implements Serializable {
	public static FormConfigSoap toSoapModel(FormConfig model) {
		FormConfigSoap soapModel = new FormConfigSoap();

		soapModel.setFormConfigId(model.getFormConfigId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreateUserId(model.getCreateUserId());
		soapModel.setModUserId(model.getModUserId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setEnabledRecaptcha(model.getEnabledRecaptcha());
		soapModel.setRecaptchaPublicKey(model.getRecaptchaPublicKey());
		soapModel.setRecaptchaPrivateKey(model.getRecaptchaPrivateKey());

		return soapModel;
	}

	public static FormConfigSoap[] toSoapModels(FormConfig[] models) {
		FormConfigSoap[] soapModels = new FormConfigSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FormConfigSoap[][] toSoapModels(FormConfig[][] models) {
		FormConfigSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FormConfigSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FormConfigSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FormConfigSoap[] toSoapModels(List<FormConfig> models) {
		List<FormConfigSoap> soapModels = new ArrayList<FormConfigSoap>(models.size());

		for (FormConfig model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FormConfigSoap[soapModels.size()]);
	}

	public FormConfigSoap() {
	}

	public long getPrimaryKey() {
		return _formConfigId;
	}

	public void setPrimaryKey(long pk) {
		setFormConfigId(pk);
	}

	public long getFormConfigId() {
		return _formConfigId;
	}

	public void setFormConfigId(long formConfigId) {
		_formConfigId = formConfigId;
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

	public boolean getEnabledRecaptcha() {
		return _enabledRecaptcha;
	}

	public boolean isEnabledRecaptcha() {
		return _enabledRecaptcha;
	}

	public void setEnabledRecaptcha(boolean enabledRecaptcha) {
		_enabledRecaptcha = enabledRecaptcha;
	}

	public String getRecaptchaPublicKey() {
		return _recaptchaPublicKey;
	}

	public void setRecaptchaPublicKey(String recaptchaPublicKey) {
		_recaptchaPublicKey = recaptchaPublicKey;
	}

	public String getRecaptchaPrivateKey() {
		return _recaptchaPrivateKey;
	}

	public void setRecaptchaPrivateKey(String recaptchaPrivateKey) {
		_recaptchaPrivateKey = recaptchaPrivateKey;
	}

	private long _formConfigId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _createUserId;
	private long _modUserId;
	private long _companyId;
	private boolean _enabledRecaptcha;
	private String _recaptchaPublicKey;
	private String _recaptchaPrivateKey;
}