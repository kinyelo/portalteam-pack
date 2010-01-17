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

import net.portalteam.model.Form;

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
 * <a href="FormLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class FormLocalServiceBaseImpl implements FormLocalService {
	public Form addForm(Form form) throws SystemException {
		form.setNew(true);

		return formPersistence.update(form, false);
	}

	public Form createForm(long formId) {
		return formPersistence.create(formId);
	}

	public void deleteForm(long formId) throws PortalException, SystemException {
		formPersistence.remove(formId);
	}

	public void deleteForm(Form form) throws SystemException {
		formPersistence.remove(form);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return formPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return formPersistence.findWithDynamicQuery(dynamicQuery, start, end);
	}

	public Form getForm(long formId) throws PortalException, SystemException {
		return formPersistence.findByPrimaryKey(formId);
	}

	public List<Form> getForms(int start, int end) throws SystemException {
		return formPersistence.findAll(start, end);
	}

	public int getFormsCount() throws SystemException {
		return formPersistence.countAll();
	}

	public Form updateForm(Form form) throws SystemException {
		form.setNew(false);

		return formPersistence.update(form, true);
	}

	public Form updateForm(Form form, boolean merge) throws SystemException {
		form.setNew(false);

		return formPersistence.update(form, merge);
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