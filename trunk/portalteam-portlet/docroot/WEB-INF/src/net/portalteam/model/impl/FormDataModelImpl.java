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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.model.impl.ExpandoBridgeImpl;

import net.portalteam.model.FormData;
import net.portalteam.model.FormDataSoap;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="FormDataModelImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormDataModelImpl extends BaseModelImpl<FormData> {
	public static final String TABLE_NAME = "PORTALTEAM_FormData";
	public static final Object[][] TABLE_COLUMNS = {
			{ "formDataId", new Integer(Types.BIGINT) },
			

			{ "createDate", new Integer(Types.TIMESTAMP) },
			

			{ "modifiedDate", new Integer(Types.TIMESTAMP) },
			

			{ "createUserId", new Integer(Types.BIGINT) },
			

			{ "modUserId", new Integer(Types.BIGINT) },
			

			{ "formId", new Integer(Types.BIGINT) },
			

			{ "data_", new Integer(Types.CLOB) }
		};
	public static final String TABLE_SQL_CREATE = "create table PORTALTEAM_FormData (formDataId LONG not null primary key,createDate DATE null,modifiedDate DATE null,createUserId LONG,modUserId LONG,formId LONG,data_ TEXT null)";
	public static final String TABLE_SQL_DROP = "drop table PORTALTEAM_FormData";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.entity.cache.enabled.net.portalteam.model.FormData"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.util.service.ServiceProps.get(
				"value.object.finder.cache.enabled.net.portalteam.model.FormData"),
			true);

	public static FormData toModel(FormDataSoap soapModel) {
		FormData model = new FormDataImpl();

		model.setFormDataId(soapModel.getFormDataId());
		model.setCreateDate(soapModel.getCreateDate());
		model.setModifiedDate(soapModel.getModifiedDate());
		model.setCreateUserId(soapModel.getCreateUserId());
		model.setModUserId(soapModel.getModUserId());
		model.setFormId(soapModel.getFormId());
		model.setData(soapModel.getData());

		return model;
	}

	public static List<FormData> toModels(FormDataSoap[] soapModels) {
		List<FormData> models = new ArrayList<FormData>(soapModels.length);

		for (FormDataSoap soapModel : soapModels) {
			models.add(toModel(soapModel));
		}

		return models;
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.util.service.ServiceProps.get(
				"lock.expiration.time.net.portalteam.model.FormData"));

	public FormDataModelImpl() {
	}

	public long getPrimaryKey() {
		return _formDataId;
	}

	public void setPrimaryKey(long pk) {
		setFormDataId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_formDataId);
	}

	public long getFormDataId() {
		return _formDataId;
	}

	public void setFormDataId(long formDataId) {
		_formDataId = formDataId;
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

	public long getFormId() {
		return _formId;
	}

	public void setFormId(long formId) {
		_formId = formId;
	}

	public String getData() {
		return GetterUtil.getString(_data);
	}

	public void setData(String data) {
		_data = data;
	}

	public FormData toEscapedModel() {
		if (isEscapedModel()) {
			return (FormData)this;
		}
		else {
			FormData model = new FormDataImpl();

			model.setNew(isNew());
			model.setEscapedModel(true);

			model.setFormDataId(getFormDataId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setCreateUserId(getCreateUserId());
			model.setModUserId(getModUserId());
			model.setFormId(getFormId());
			model.setData(HtmlUtil.escape(getData()));

			model = (FormData)Proxy.newProxyInstance(FormData.class.getClassLoader(),
					new Class[] { FormData.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = new ExpandoBridgeImpl(FormData.class.getName(),
					getPrimaryKey());
		}

		return _expandoBridge;
	}

	public Object clone() {
		FormDataImpl clone = new FormDataImpl();

		clone.setFormDataId(getFormDataId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCreateUserId(getCreateUserId());
		clone.setModUserId(getModUserId());
		clone.setFormId(getFormId());
		clone.setData(getData());

		return clone;
	}

	public int compareTo(FormData formData) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), formData.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		FormData formData = null;

		try {
			formData = (FormData)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = formData.getPrimaryKey();

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

		sb.append("{formDataId=");
		sb.append(getFormDataId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", createUserId=");
		sb.append(getCreateUserId());
		sb.append(", modUserId=");
		sb.append(getModUserId());
		sb.append(", formId=");
		sb.append(getFormId());
		sb.append(", data=");
		sb.append(getData());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("net.portalteam.model.FormData");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>formDataId</column-name><column-value><![CDATA[");
		sb.append(getFormDataId());
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
			"<column><column-name>formId</column-name><column-value><![CDATA[");
		sb.append(getFormId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>data</column-name><column-value><![CDATA[");
		sb.append(getData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _formDataId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _createUserId;
	private long _modUserId;
	private long _formId;
	private String _data;
	private transient ExpandoBridge _expandoBridge;
}