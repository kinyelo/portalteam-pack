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

package net.portalteam.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ClassLoaderProxy;

/**
 * <a href="FormConfigLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormConfigLocalServiceUtil {
	public static net.portalteam.model.FormConfig addFormConfig(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException {
		return getService().addFormConfig(formConfig);
	}

	public static net.portalteam.model.FormConfig createFormConfig(
		long formConfigId) {
		return getService().createFormConfig(formConfigId);
	}

	public static void deleteFormConfig(long formConfigId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteFormConfig(formConfigId);
	}

	public static void deleteFormConfig(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException {
		getService().deleteFormConfig(formConfig);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	public static net.portalteam.model.FormConfig getFormConfig(
		long formConfigId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getFormConfig(formConfigId);
	}

	public static java.util.List<net.portalteam.model.FormConfig> getFormConfigs(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getFormConfigs(start, end);
	}

	public static int getFormConfigsCount()
		throws com.liferay.portal.SystemException {
		return getService().getFormConfigsCount();
	}

	public static net.portalteam.model.FormConfig updateFormConfig(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException {
		return getService().updateFormConfig(formConfig);
	}

	public static net.portalteam.model.FormConfig updateFormConfig(
		net.portalteam.model.FormConfig formConfig, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateFormConfig(formConfig, merge);
	}

	public static net.portalteam.model.FormConfig getFormConfigByCompany(
		long companyId) throws com.liferay.portal.SystemException {
		return getService().getFormConfigByCompany(companyId);
	}

	public static void clearService() {
		_service = null;
	}

	public static FormConfigLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					FormConfigLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new FormConfigLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(FormConfigLocalService service) {
		_service = service;
	}

	private static FormConfigLocalService _service;
}