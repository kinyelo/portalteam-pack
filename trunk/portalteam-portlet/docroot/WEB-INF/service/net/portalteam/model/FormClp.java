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
 * <a href="FormClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormClp extends BaseModelImpl<Form> implements Form {
	public FormClp() {
	}

	public long getPrimaryKey() {
		return _formId;
	}

	public void setPrimaryKey(long pk) {
		setFormId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_formId);
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

	public Form toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			Form model = new FormClp();

			model.setEscapedModel(true);

			model.setFormId(getFormId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setCreateUserId(getCreateUserId());
			model.setModUserId(getModUserId());
			model.setCompanyId(getCompanyId());
			model.setTitle(HtmlUtil.escape(getTitle()));
			model.setName(HtmlUtil.escape(getName()));
			model.setEmail(HtmlUtil.escape(getEmail()));
			model.setLetterSubject(HtmlUtil.escape(getLetterSubject()));
			model.setLetterTemplate(HtmlUtil.escape(getLetterTemplate()));
			model.setSendButtonTitle(HtmlUtil.escape(getSendButtonTitle()));
			model.setResetButtonTitle(HtmlUtil.escape(getResetButtonTitle()));
			model.setShowResetButton(getShowResetButton());
			model.setCaptcha(getCaptcha());
			model.setStructure(HtmlUtil.escape(getStructure()));
			model.setRenderTemplate(HtmlUtil.escape(getRenderTemplate()));

			model = (Form)Proxy.newProxyInstance(Form.class.getClassLoader(),
					new Class[] { Form.class }, new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		FormClp clone = new FormClp();

		clone.setFormId(getFormId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCreateUserId(getCreateUserId());
		clone.setModUserId(getModUserId());
		clone.setCompanyId(getCompanyId());
		clone.setTitle(getTitle());
		clone.setName(getName());
		clone.setEmail(getEmail());
		clone.setLetterSubject(getLetterSubject());
		clone.setLetterTemplate(getLetterTemplate());
		clone.setSendButtonTitle(getSendButtonTitle());
		clone.setResetButtonTitle(getResetButtonTitle());
		clone.setShowResetButton(getShowResetButton());
		clone.setCaptcha(getCaptcha());
		clone.setStructure(getStructure());
		clone.setRenderTemplate(getRenderTemplate());

		return clone;
	}

	public int compareTo(Form form) {
		long pk = form.getPrimaryKey();

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

		FormClp form = null;

		try {
			form = (FormClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = form.getPrimaryKey();

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

		sb.append("{formId=");
		sb.append(getFormId());
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
		sb.append(", title=");
		sb.append(getTitle());
		sb.append(", name=");
		sb.append(getName());
		sb.append(", email=");
		sb.append(getEmail());
		sb.append(", letterSubject=");
		sb.append(getLetterSubject());
		sb.append(", letterTemplate=");
		sb.append(getLetterTemplate());
		sb.append(", sendButtonTitle=");
		sb.append(getSendButtonTitle());
		sb.append(", resetButtonTitle=");
		sb.append(getResetButtonTitle());
		sb.append(", showResetButton=");
		sb.append(getShowResetButton());
		sb.append(", captcha=");
		sb.append(getCaptcha());
		sb.append(", structure=");
		sb.append(getStructure());
		sb.append(", renderTemplate=");
		sb.append(getRenderTemplate());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("net.portalteam.model.Form");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>formId</column-name><column-value><![CDATA[");
		sb.append(getFormId());
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
			"<column><column-name>title</column-name><column-value><![CDATA[");
		sb.append(getTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>email</column-name><column-value><![CDATA[");
		sb.append(getEmail());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>letterSubject</column-name><column-value><![CDATA[");
		sb.append(getLetterSubject());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>letterTemplate</column-name><column-value><![CDATA[");
		sb.append(getLetterTemplate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sendButtonTitle</column-name><column-value><![CDATA[");
		sb.append(getSendButtonTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>resetButtonTitle</column-name><column-value><![CDATA[");
		sb.append(getResetButtonTitle());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>showResetButton</column-name><column-value><![CDATA[");
		sb.append(getShowResetButton());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>captcha</column-name><column-value><![CDATA[");
		sb.append(getCaptcha());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>structure</column-name><column-value><![CDATA[");
		sb.append(getStructure());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>renderTemplate</column-name><column-value><![CDATA[");
		sb.append(getRenderTemplate());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
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