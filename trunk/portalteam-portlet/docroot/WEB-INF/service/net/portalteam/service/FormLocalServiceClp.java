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

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="FormLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormLocalServiceClp implements FormLocalService {
	public FormLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public net.portalteam.model.Form addForm(net.portalteam.model.Form form)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(form);

		if (form == null) {
			paramObj0 = new NullWrapper("net.portalteam.model.Form");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addForm",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.portalteam.model.Form)ClpSerializer.translateOutput(returnObj);
	}

	public net.portalteam.model.Form createForm(long formId) {
		Object paramObj0 = new LongWrapper(formId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createForm",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.portalteam.model.Form)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteForm(long formId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(formId);

		try {
			_classLoaderProxy.invoke("deleteForm", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public void deleteForm(net.portalteam.model.Form form)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(form);

		if (form == null) {
			paramObj0 = new NullWrapper("net.portalteam.model.Form");
		}

		try {
			_classLoaderProxy.invoke("deleteForm", new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<Object> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<Object>)ClpSerializer.translateOutput(returnObj);
	}

	public net.portalteam.model.Form getForm(long formId)
		throws com.liferay.portal.PortalException,
			com.liferay.portal.SystemException {
		Object paramObj0 = new LongWrapper(formId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getForm",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.PortalException) {
				throw (com.liferay.portal.PortalException)t;
			}

			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.portalteam.model.Form)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<net.portalteam.model.Form> getForms(int start, int end)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getForms",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<net.portalteam.model.Form>)ClpSerializer.translateOutput(returnObj);
	}

	public int getFormsCount() throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getFormsCount", new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public net.portalteam.model.Form updateForm(net.portalteam.model.Form form)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(form);

		if (form == null) {
			paramObj0 = new NullWrapper("net.portalteam.model.Form");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateForm",
					new Object[] { paramObj0 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.portalteam.model.Form)ClpSerializer.translateOutput(returnObj);
	}

	public net.portalteam.model.Form updateForm(
		net.portalteam.model.Form form, boolean merge)
		throws com.liferay.portal.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(form);

		if (form == null) {
			paramObj0 = new NullWrapper("net.portalteam.model.Form");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateForm",
					new Object[] { paramObj0, paramObj1 });
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.portalteam.model.Form)ClpSerializer.translateOutput(returnObj);
	}

	public net.portalteam.model.Form createForm()
		throws com.liferay.portal.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createForm", new Object[0]);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.SystemException) {
				throw (com.liferay.portal.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (net.portalteam.model.Form)ClpSerializer.translateOutput(returnObj);
	}

	private ClassLoaderProxy _classLoaderProxy;
}