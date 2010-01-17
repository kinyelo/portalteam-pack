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
 * <a href="FormDataLocalServiceUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormDataLocalServiceUtil {
	public static net.portalteam.model.FormData addFormData(
		net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException {
		return getService().addFormData(formData);
	}

	public static net.portalteam.model.FormData createFormData(long formDataId) {
		return getService().createFormData(formDataId);
	}

	public static void deleteFormData(long formDataId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		getService().deleteFormData(formDataId);
	}

	public static void deleteFormData(net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException {
		getService().deleteFormData(formData);
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

	public static net.portalteam.model.FormData getFormData(long formDataId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		return getService().getFormData(formDataId);
	}

	public static java.util.List<net.portalteam.model.FormData> getFormDatas(
		int start, int end) throws com.liferay.portal.SystemException {
		return getService().getFormDatas(start, end);
	}

	public static int getFormDatasCount()
		throws com.liferay.portal.SystemException {
		return getService().getFormDatasCount();
	}

	public static net.portalteam.model.FormData updateFormData(
		net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException {
		return getService().updateFormData(formData);
	}

	public static net.portalteam.model.FormData updateFormData(
		net.portalteam.model.FormData formData, boolean merge)
		throws com.liferay.portal.SystemException {
		return getService().updateFormData(formData, merge);
	}

	public static java.util.List<net.portalteam.model.FormData> getByForm(
		long formId) throws com.liferay.portal.SystemException {
		return getService().getByForm(formId);
	}

	public static void clearService() {
		_service = null;
	}

	public static FormDataLocalService getService() {
		if (_service == null) {
			Object obj = PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					FormDataLocalServiceUtil.class.getName());
			ClassLoader portletClassLoader = (ClassLoader)PortletBeanLocatorUtil.locate(ClpSerializer.SERVLET_CONTEXT_NAME,
					"portletClassLoader");

			ClassLoaderProxy classLoaderProxy = new ClassLoaderProxy(obj,
					portletClassLoader);

			_service = new FormDataLocalServiceClp(classLoaderProxy);

			ClpSerializer.setClassLoader(portletClassLoader);
		}

		return _service;
	}

	public void setService(FormDataLocalService service) {
		_service = service;
	}

	private static FormDataLocalService _service;
}