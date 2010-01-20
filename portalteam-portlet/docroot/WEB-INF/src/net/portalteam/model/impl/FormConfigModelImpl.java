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

package net.portalteam.model.impl;

import com.liferay.portal.kernel.bean.ReadOnlyBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import net.portalteam.model.FormConfig;
import net.portalteam.model.FormConfigSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FormConfigModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormConfigModelImpl extends BaseModelImpl<FormConfig> {
	public static final String TABLE_NAME = "PORTALTEAM_FormConfig";
	public static final Object[][] TABLE_COLUMNS = {
			{ "formConfigId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "createUserId", new Integer(Types.BIGINT) },
			

			{ "modUserId", new Integer(Types.BIGINT) },
			

			{ "companyId", new Integer(Types.BIGINT) },
			

			{ "enabledRecaptcha", new Integer(Types.BOOLEAN) },
			

			{ "recaptchaPublicKey", new Integer(Types.VARCHAR) },
			

			{ "recaptchaPrivateKey", new Integer(Types.VARCHAR) },
			

			{ "emailFromAddress", new Integer(Types.VARCHAR) }
		};
	public static final String TABLE_SQL_CREATE = "create table PORTALTEAM_FormConfig (formConfigId LONG not null primary key,createDate DATE null,modifiedDate DATE null,createUserId LONG,modUserId LONG,companyId LONG,enabledRecaptcha BOOLEAN,recaptchaPublicKey VARCHAR(75) null,recaptchaPrivateKey VARCHAR(75) null,emailFromAddress VARCHAR(75) null)";
	public static final String TABLE_SQL_DROP = "drop table PORTALTEAM_FormConfig";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.net.portalteam.model.FormConfig"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.net.portalteam.model.FormConfig"),
			true);

	public static FormConfig toModel(FormConfigSoap soapModel) {
		FormConfig model = new FormConfigImpl();

		model.setFormConfigId(soapModel.getFormConfigId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCreateUserId(soapModel.getCreateUserId());
		model.setModUserId(soapModel.getModUserId());
		model.setCompanyId(soapModel.getCompanyId());
		model.setEnabledRecaptcha(soapModel.getEnabledRecaptcha());
		model.setRecaptchaPublicKey(soapModel.getRecaptchaPublicKey());
		model.setRecaptchaPrivateKey(soapModel.getRecaptchaPrivateKey());
		model.setEmailFromAddress(soapModel.getEmailFromAddress());

		return model;
	}

	public static List<FormConfig> toModels(FormConfigSoap[] soapModels) {
		List<FormConfig> models = new ArrayList<FormConfig>(soapModels.length);

		for (FormConfigSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.net.portalteam.model.FormConfig"));

	public FormConfigModelImpl() {
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
		return GetterUtil.getString(_recaptchaPublicKey);
	}

	public void setRecaptchaPublicKey(String recaptchaPublicKey) {
		_recaptchaPublicKey = recaptchaPublicKey;
	}

	public String getRecaptchaPrivateKey() {
		return GetterUtil.getString(_recaptchaPrivateKey);
	}

	public void setRecaptchaPrivateKey(String recaptchaPrivateKey) {
		_recaptchaPrivateKey = recaptchaPrivateKey;
	}

	public String getEmailFromAddress() {
		return GetterUtil.getString(_emailFromAddress);
	}

	public void setEmailFromAddress(String emailFromAddress) {
		_emailFromAddress = emailFromAddress;
	}

	public FormConfig toEscapedModel() {
		if (isEscapedModel()) {
			return (FormConfig)this;
		}
		else {
			FormConfig model = new FormConfigImpl();

			model.setNew(isNew());
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
			model.setEmailFromAddress(HtmlUtil.escape(getEmailFromAddress()));

			model = (FormConfig)Proxy.newProxyInstance(FormConfig.class.getClassLoader(),
					new Class[] { FormConfig.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(FormConfig.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		FormConfigImpl clone = new FormConfigImpl();

		clone.setFormConfigId(getFormConfigId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCreateUserId(getCreateUserId());
		clone.setModUserId(getModUserId());
		clone.setCompanyId(getCompanyId());
		clone.setEnabledRecaptcha(getEnabledRecaptcha());
		clone.setRecaptchaPublicKey(getRecaptchaPublicKey());
		clone.setRecaptchaPrivateKey(getRecaptchaPrivateKey());
		clone.setEmailFromAddress(getEmailFromAddress());

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

		FormConfig formConfig = null;

		try {
			formConfig = (FormConfig)obj;
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
		sb.append(", emailFromAddress=");
		sb.append(getEmailFromAddress());
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
		sb.append(
			"<column><column-name>emailFromAddress</column-name><column-value><![CDATA[");
		sb.append(getEmailFromAddress());
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
	private String _emailFromAddress;
	private transient ExpandoBridge _expandoBridge;
}