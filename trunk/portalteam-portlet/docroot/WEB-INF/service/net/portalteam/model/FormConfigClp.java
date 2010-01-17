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

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="FormConfigClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormConfigClp extends BaseModelImpl<FormConfig>
	implements FormConfig {
	public FormConfigClp() {
	}

	public long getPrimaryKey() {
		return _formConfigId;
	}

	public void setPrimaryKey(long pk) {
		setFormConfigId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_formConfigId);
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

	public FormConfig toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			FormConfig model = new FormConfigClp();

			model.setEscapedModel(true);

			model.setFormConfigId(getFormConfigId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setCreateUserId(getCreateUserId());
			model.setModUserId(getModUserId());
			model.setCompanyId(getCompanyId());
			model.setEnabledRecaptcha(getEnabledRecaptcha());
			model.setRecaptchaPublicKey(HtmlUtil.escape(getRecaptchaPublicKey()));
			model.setRecaptchaPrivateKey(HtmlUtil.escape(
					getRecaptchaPrivateKey()));

			model = (FormConfig)Proxy.newProxyInstance(FormConfig.class.getClassLoader(),
					new Class[] { FormConfig.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		FormConfigClp clone = new FormConfigClp();

		clone.setFormConfigId(getFormConfigId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCreateUserId(getCreateUserId());
		clone.setModUserId(getModUserId());
		clone.setCompanyId(getCompanyId());
		clone.setEnabledRecaptcha(getEnabledRecaptcha());
		clone.setRecaptchaPublicKey(getRecaptchaPublicKey());
		clone.setRecaptchaPrivateKey(getRecaptchaPrivateKey());

		return clone;
	}

	public int compareTo(FormConfig formConfig) {
		long pk = formConfig.getPrimaryKey();

		if (getPrimaryKey() < pk) {
			return -1;
		}
		else if (getPrimaryKey() > pk) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		FormConfigClp formConfig = null;

		try {
			formConfig = (FormConfigClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = formConfig.getPrimaryKey();

		if (getPrimaryKey() == pk) {
			return true;
		}
		else {
			return false;
		}
	}

	public int hashCode() {
		return (int)getPrimaryKey();
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("{formConfigId=");
		sb.append(getFormConfigId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", createUserId=");
		sb.append(getCreateUserId());
		sb.append(", modUserId=");
		sb.append(getModUserId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", enabledRecaptcha=");
		sb.append(getEnabledRecaptcha());
		sb.append(", recaptchaPublicKey=");
		sb.append(getRecaptchaPublicKey());
		sb.append(", recaptchaPrivateKey=");
		sb.append(getRecaptchaPrivateKey());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("net.portalteam.model.FormConfig");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>formConfigId</column-name><column-value><![CDATA[");
		sb.append(getFormConfigId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createUserId</column-name><column-value><![CDATA[");
		sb.append(getCreateUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modUserId</column-name><column-value><![CDATA[");
		sb.append(getModUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>enabledRecaptcha</column-name><column-value><![CDATA[");
		sb.append(getEnabledRecaptcha());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recaptchaPublicKey</column-name><column-value><![CDATA[");
		sb.append(getRecaptchaPublicKey());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>recaptchaPrivateKey</column-name><column-value><![CDATA[");
		sb.append(getRecaptchaPrivateKey());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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