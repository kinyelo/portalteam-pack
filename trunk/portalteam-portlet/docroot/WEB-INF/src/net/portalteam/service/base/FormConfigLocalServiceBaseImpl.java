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

import net.portalteam.model.FormConfig;

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
 * <a href="FormConfigLocalServiceBaseImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public abstract class FormConfigLocalServiceBaseImpl
	implements FormConfigLocalService {
	public FormConfig addFormConfig(FormConfig formConfig)
		throws SystemException {
		formConfig.setNew(true);

		return formConfigPersistence.update(formConfig, false);
	}

	public FormConfig createFormConfig(long formConfigId) {
		return formConfigPersistence.create(formConfigId);
	}

	public void deleteFormConfig(long formConfigId)
		throws PortalException, SystemException {
		formConfigPersistence.remove(formConfigId);
	}

	public void deleteFormConfig(FormConfig formConfig)
		throws SystemException {
		formConfigPersistence.remove(formConfig);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return formConfigPersistence.findWithDynamicQuery(dynamicQuery);
	}

	public List<Object> dynamicQuery(DynamicQuery dynamicQuery, int start,
		int end) throws SystemException {
		return formConfigPersistence.findWithDynamicQuery(dynamicQuery, start,
			end);
	}

	public FormConfig getFormConfig(long formConfigId)
		throws PortalException, SystemException {
		return formConfigPersistence.findByPrimaryKey(formConfigId);
	}

	public List<FormConfig> getFormConfigs(int start, int end)
		throws SystemException {
		return formConfigPersistence.findAll(start, end);
	}

	public int getFormConfigsCount() throws SystemException {
		return formConfigPersistence.countAll();
	}

	public FormConfig updateFormConfig(FormConfig formConfig)
		throws SystemException {
		formConfig.setNew(false);

		return formConfigPersistence.update(formConfig, true);
	}

	public FormConfig updateFormConfig(FormConfig formConfig, boolean merge)
		throws SystemException {
		formConfig.setNew(false);

		return formConfigPersistence.update(formConfig, merge);
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