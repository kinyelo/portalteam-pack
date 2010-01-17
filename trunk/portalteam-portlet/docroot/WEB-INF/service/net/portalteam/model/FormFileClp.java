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
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.Date;

/**
 * <a href="FormFileClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormFileClp extends BaseModelImpl<FormFile> implements FormFile {
	public FormFileClp() {
	}

	public long getPrimaryKey() {
		return _formFileId;
	}

	public void setPrimaryKey(long pk) {
		setFormFileId(pk);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_formFileId);
	}

	public long getFormFileId() {
		return _formFileId;
	}

	public void setFormFileId(long formFileId) {
		_formFileId = formFileId;
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

	public long getFormDataId() {
		return _formDataId;
	}

	public void setFormDataId(long formDataId) {
		_formDataId = formDataId;
	}

	public String getFieldName() {
		return _fieldName;
	}

	public void setFieldName(String fieldName) {
		_fieldName = fieldName;
	}

	public String getFilename() {
		return _filename;
	}

	public void setFilename(String filename) {
		_filename = filename;
	}

	public String getMimeType() {
		return _mimeType;
	}

	public void setMimeType(String mimeType) {
		_mimeType = mimeType;
	}

	public String getData() {
		return _data;
	}

	public void setData(String data) {
		_data = data;
	}

	public byte[] getDataObj() {
		throw new UnsupportedOperationException();
	}

	public void setDataObj(byte[] value) {
		throw new UnsupportedOperationException();
	}

	public FormFile toEscapedModel() {
		if (isEscapedModel()) {
			return this;
		}
		else {
			FormFile model = new FormFileClp();

			model.setEscapedModel(true);

			model.setFormFileId(getFormFileId());
			model.setCreateDate(getCreateDate());
			model.setModifiedDate(getModifiedDate());
			model.setCreateUserId(getCreateUserId());
			model.setModUserId(getModUserId());
			model.setFormDataId(getFormDataId());
			model.setFieldName(HtmlUtil.escape(getFieldName()));
			model.setFilename(HtmlUtil.escape(getFilename()));
			model.setMimeType(HtmlUtil.escape(getMimeType()));
			model.setData(HtmlUtil.escape(getData()));

			model = (FormFile)Proxy.newProxyInstance(FormFile.class.getClassLoader(),
					new Class[] { FormFile.class },
					new ReadOnlyBeanHandler(model));

			return model;
		}
	}

	public Object clone() {
		FormFileClp clone = new FormFileClp();

		clone.setFormFileId(getFormFileId());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setCreateUserId(getCreateUserId());
		clone.setModUserId(getModUserId());
		clone.setFormDataId(getFormDataId());
		clone.setFieldName(getFieldName());
		clone.setFilename(getFilename());
		clone.setMimeType(getMimeType());
		clone.setData(getData());

		return clone;
	}

	public int compareTo(FormFile formFile) {
		int value = 0;

		value = DateUtil.compareTo(getCreateDate(), formFile.getCreateDate());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		FormFileClp formFile = null;

		try {
			formFile = (FormFileClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long pk = formFile.getPrimaryKey();

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

		sb.append("{formFileId=");
		sb.append(getFormFileId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", createUserId=");
		sb.append(getCreateUserId());
		sb.append(", modUserId=");
		sb.append(getModUserId());
		sb.append(", formDataId=");
		sb.append(getFormDataId());
		sb.append(", fieldName=");
		sb.append(getFieldName());
		sb.append(", filename=");
		sb.append(getFilename());
		sb.append(", mimeType=");
		sb.append(getMimeType());
		sb.append(", data=");
		sb.append(getData());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBuilder sb = new StringBuilder();

		sb.append("<model><model-name>");
		sb.append("net.portalteam.model.FormFile");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>formFileId</column-name><column-value><![CDATA[");
		sb.append(getFormFileId());
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
			"<column><column-name>formDataId</column-name><column-value><![CDATA[");
		sb.append(getFormDataId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>fieldName</column-name><column-value><![CDATA[");
		sb.append(getFieldName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>filename</column-name><column-value><![CDATA[");
		sb.append(getFilename());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>mimeType</column-name><column-value><![CDATA[");
		sb.append(getMimeType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>data</column-name><column-value><![CDATA[");
		sb.append(getData());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _formFileId;
	private Date _createDate;
	private Date _modifiedDate;
	private long _createUserId;
	private long _modUserId;
	private long _formDataId;
	private String _fieldName;
	private String _filename;
	private String _mimeType;
	private String _data;
}