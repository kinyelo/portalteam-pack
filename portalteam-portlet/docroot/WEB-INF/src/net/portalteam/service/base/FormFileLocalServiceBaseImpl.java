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

import net.portalteam.model.FormFile;

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
 * <a href="FormFileLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class FormFileLocalServiceBaseImpl
	implements FormFileLocalService {
	public FormFile addFormFile(FormFile formFile) throws SystemException {
		formFile.setNew(true);

		return formFilePersistence.update(formFile, false);
	}

	public FormFile createFormFile(long formFileId) {
		return formFilePersistence.create(formFileId);
	}

	public void deleteFormFile(long formFileId)
		throws PortalException, SystemException {
		formFilePersistence.remove(formFileId);
	}

	public void deleteFormFile(FormFile formFile) throws SystemException {
		formFilePersistence.remove(formFile);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return formFilePersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return formFilePersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public FormFile getFormFile(long formFileId)
		throws PortalException, SystemException {
		return formFilePersistence.findByPrimaryKey(formFileId);
	}

	public List<FormFile> getFormFiles(int start, int end)
		throws SystemException {
		return formFilePersistence.findAll(start, end);
	}

	public int getFormFilesCount() throws SystemException {
		return formFilePersistence.countAll();
	}

	public FormFile updateFormFile(FormFile formFile) throws SystemException {
		formFile.setNew(false);

		return formFilePersistence.update(formFile, true);
	}

	public FormFile updateFormFile(FormFile formFile, boolean merge)
		throws SystemException {
		formFile.setNew(false);

		return formFilePersistence.update(formFile, merge);
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