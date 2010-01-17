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

package net.portalteam.service.base;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.util.PortalUtil;

import net.portalteam.model.FormData;

import net.portalteam.service.FormConfigLocalService;
import net.portalteam.service.FormDataLocalService;
import net.portalteam.service.FormFileLocalService;
import net.portalteam.service.FormLocalService;
import net.portalteam.service.persistence.FormConfigPersistence;
import net.portalteam.service.persistence.FormDataPersistence;
import net.portalteam.service.persistence.FormFilePersistence;
import net.portalteam.service.persistence.FormPersistence;

import java.util.List;

/**
 * <a href="FormDataLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class FormDataLocalServiceBaseImpl
	implements FormDataLocalService {
	public FormData addFormData(FormData formData) throws SystemException {
		formData.setNew(true);

		return formDataPersistence.update(formData, false);
	}

	public FormData createFormData(long formDataId) {
		return formDataPersistence.create(formDataId);
	}

	public void deleteFormData(long formDataId)
		throws PortalException, SystemException {
		formDataPersistence.remove(formDataId);
	}

	public void deleteFormData(FormData formData) throws SystemException {
		formDataPersistence.remove(formData);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return formDataPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return formDataPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public FormData getFormData(long formDataId)
		throws PortalException, SystemException {
		return formDataPersistence.findByPrimaryKey(formDataId);
	}

	public List<FormData> getFormDatas(int start, int end)
		throws SystemException {
		return formDataPersistence.findAll(start, end);
	}

	public int getFormDatasCount() throws SystemException {
		return formDataPersistence.countAll();
	}

	public FormData updateFormData(FormData formData) throws SystemException {
		formData.setNew(false);

		return formDataPersistence.update(formData, true);
	}

	public FormData updateFormData(FormData formData, boolean merge)
		throws SystemException {
		formData.setNew(false);

		return formDataPersistence.update(formData, merge);
	}

	public FormLocalService getFormLocalService() {
		return formLocalService;
	}

	public void setFormLocalService(FormLocalService formLocalService) {
		this.formLocalService = formLocalService;
	}

	public FormPersistence getFormPersistence() {
		return formPersistence;
	}

	public void setFormPersistence(FormPersistence formPersistence) {
		this.formPersistence = formPersistence;
	}

	public FormDataLocalService getFormDataLocalService() {
		return formDataLocalService;
	}

	public void setFormDataLocalService(
		FormDataLocalService formDataLocalService) {
		this.formDataLocalService = formDataLocalService;
	}

	public FormDataPersistence getFormDataPersistence() {
		return formDataPersistence;
	}

	public void setFormDataPersistence(FormDataPersistence formDataPersistence) {
		this.formDataPersistence = formDataPersistence;
	}

	public FormFileLocalService getFormFileLocalService() {
		return formFileLocalService;
	}

	public void setFormFileLocalService(
		FormFileLocalService formFileLocalService) {
		this.formFileLocalService = formFileLocalService;
	}

	public FormFilePersistence getFormFilePersistence() {
		return formFilePersistence;
	}

	public void setFormFilePersistence(FormFilePersistence formFilePersistence) {
		this.formFilePersistence = formFilePersistence;
	}

	public FormConfigLocalService getFormConfigLocalService() {
		return formConfigLocalService;
	}

	public void setFormConfigLocalService(
		FormConfigLocalService formConfigLocalService) {
		this.formConfigLocalService = formConfigLocalService;
	}

	public FormConfigPersistence getFormConfigPersistence() {
		return formConfigPersistence;
	}

	public void setFormConfigPersistence(
		FormConfigPersistence formConfigPersistence) {
		this.formConfigPersistence = formConfigPersistence;
	}

	protected void runSQL(String sql) throws SystemException {
		try {
			PortalUtil.runSQL(sql);
		}
		catch (Exception e) {
			throw new SystemException(e);
		}
	}

	@BeanReference(name = "net.portalteam.service.FormLocalService.impl")
	protected FormLocalService formLocalService;
	@BeanReference(name = "net.portalteam.service.persistence.FormPersistence.impl")
	protected FormPersistence formPersistence;
	@BeanReference(name = "net.portalteam.service.FormDataLocalService.impl")
	protected FormDataLocalService formDataLocalService;
	@BeanReference(name = "net.portalteam.service.persistence.FormDataPersistence.impl")
	protected FormDataPersistence formDataPersistence;
	@BeanReference(name = "net.portalteam.service.FormFileLocalService.impl")
	protected FormFileLocalService formFileLocalService;
	@BeanReference(name = "net.portalteam.service.persistence.FormFilePersistence.impl")
	protected FormFilePersistence formFilePersistence;
	@BeanReference(name = "net.portalteam.service.FormConfigLocalService.impl")
	protected FormConfigLocalService formConfigLocalService;
	@BeanReference(name = "net.portalteam.service.persistence.FormConfigPersistence.impl")
	protected FormConfigPersistence formConfigPersistence;
}