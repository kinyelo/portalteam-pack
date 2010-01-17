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
 * <a href="FormFileSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormFileSoap implements Serializable {
	public static FormFileSoap toSoapModel(FormFile model) {
		FormFileSoap soapModel = new FormFileSoap();

		soapModel.setFormFileId(model.getFormFileId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreateUserId(model.getCreateUserId());
		soapModel.setModUserId(model.getModUserId());
		soapModel.setFormDataId(model.getFormDataId());
		soapModel.setFieldName(model.getFieldName());
		soapModel.setFilename(model.getFilename());
		soapModel.setMimeType(model.getMimeType());
		soapModel.setData(model.getData());

		return soapModel;
	}

	public static FormFileSoap[] toSoapModels(FormFile[] models) {
		FormFileSoap[] soapModels = new FormFileSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static FormFileSoap[][] toSoapModels(FormFile[][] models) {
		FormFileSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new FormFileSoap[models.length][models[0].length];
		}
		else {
			soapModels = new FormFileSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static FormFileSoap[] toSoapModels(List<FormFile> models) {
		List<FormFileSoap> soapModels = new ArrayList<FormFileSoap>(models.size());

		for (FormFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new FormFileSoap[soapModels.size()]);
	}

	public FormFileSoap() {
	}

	public long getPrimaryKey() {
		return _formFileId;
	}

	public void setPrimaryKey(long pk) {
		setFormFileId(pk);
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