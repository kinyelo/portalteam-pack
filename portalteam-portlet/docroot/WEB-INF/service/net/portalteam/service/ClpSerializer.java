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

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.model.BaseModel;

import net.portalteam.model.FormClp;
import net.portalteam.model.FormConfigClp;
import net.portalteam.model.FormDataClp;
import net.portalteam.model.FormFileClp;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <a href="ClpSerializer.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class ClpSerializer {
	public static final String SERVLET_CONTEXT_NAME = "portalteam-portlet";

	public static void setClassLoader(ClassLoader classLoader) {
		_classLoader = classLoader;
	}

	public static Object translateInput(BaseModel oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals(FormClp.class.getName())) {
			FormClp oldCplModel = (FormClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("net.portalteam.model.impl.FormImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFormId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFormId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getCreateDate();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getModifiedDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setCreateUserId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getCreateUserId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setModUserId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getModUserId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getCompanyId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setTitle",
							new Class[] { String.class });

					String value6 = oldCplModel.getTitle();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setName",
							new Class[] { String.class });

					String value7 = oldCplModel.getName();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setEmail",
							new Class[] { String.class });

					String value8 = oldCplModel.getEmail();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setLetterSubject",
							new Class[] { String.class });

					String value9 = oldCplModel.getLetterSubject();

					method9.invoke(newModel, value9);

					Method method10 = newModelClass.getMethod("setLetterTemplate",
							new Class[] { String.class });

					String value10 = oldCplModel.getLetterTemplate();

					method10.invoke(newModel, value10);

					Method method11 = newModelClass.getMethod("setSendButtonTitle",
							new Class[] { String.class });

					String value11 = oldCplModel.getSendButtonTitle();

					method11.invoke(newModel, value11);

					Method method12 = newModelClass.getMethod("setResetButtonTitle",
							new Class[] { String.class });

					String value12 = oldCplModel.getResetButtonTitle();

					method12.invoke(newModel, value12);

					Method method13 = newModelClass.getMethod("setShowResetButton",
							new Class[] { Boolean.TYPE });

					Boolean value13 = new Boolean(oldCplModel.getShowResetButton());

					method13.invoke(newModel, value13);

					Method method14 = newModelClass.getMethod("setCaptcha",
							new Class[] { Boolean.TYPE });

					Boolean value14 = new Boolean(oldCplModel.getCaptcha());

					method14.invoke(newModel, value14);

					Method method15 = newModelClass.getMethod("setStructure",
							new Class[] { String.class });

					String value15 = oldCplModel.getStructure();

					method15.invoke(newModel, value15);

					Method method16 = newModelClass.getMethod("setRenderTemplate",
							new Class[] { String.class });

					String value16 = oldCplModel.getRenderTemplate();

					method16.invoke(newModel, value16);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(FormDataClp.class.getName())) {
			FormDataClp oldCplModel = (FormDataClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("net.portalteam.model.impl.FormDataImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFormDataId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFormDataId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getCreateDate();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getModifiedDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setCreateUserId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getCreateUserId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setModUserId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getModUserId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setFormId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getFormId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setData",
							new Class[] { String.class });

					String value6 = oldCplModel.getData();

					method6.invoke(newModel, value6);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(FormFileClp.class.getName())) {
			FormFileClp oldCplModel = (FormFileClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("net.portalteam.model.impl.FormFileImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFormFileId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFormFileId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getCreateDate();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getModifiedDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setCreateUserId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getCreateUserId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setModUserId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getModUserId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setFormDataId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getFormDataId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setFieldName",
							new Class[] { String.class });

					String value6 = oldCplModel.getFieldName();

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setFilename",
							new Class[] { String.class });

					String value7 = oldCplModel.getFilename();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setMimeType",
							new Class[] { String.class });

					String value8 = oldCplModel.getMimeType();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setData",
							new Class[] { String.class });

					String value9 = oldCplModel.getData();

					method9.invoke(newModel, value9);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals(FormConfigClp.class.getName())) {
			FormConfigClp oldCplModel = (FormConfigClp)oldModel;

			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					Class<?> newModelClass = Class.forName("net.portalteam.model.impl.FormConfigImpl",
							true, _classLoader);

					Object newModel = newModelClass.newInstance();

					Method method0 = newModelClass.getMethod("setFormConfigId",
							new Class[] { Long.TYPE });

					Long value0 = new Long(oldCplModel.getFormConfigId());

					method0.invoke(newModel, value0);

					Method method1 = newModelClass.getMethod("setCreateDate",
							new Class[] { Date.class });

					Date value1 = oldCplModel.getCreateDate();

					method1.invoke(newModel, value1);

					Method method2 = newModelClass.getMethod("setModifiedDate",
							new Class[] { Date.class });

					Date value2 = oldCplModel.getModifiedDate();

					method2.invoke(newModel, value2);

					Method method3 = newModelClass.getMethod("setCreateUserId",
							new Class[] { Long.TYPE });

					Long value3 = new Long(oldCplModel.getCreateUserId());

					method3.invoke(newModel, value3);

					Method method4 = newModelClass.getMethod("setModUserId",
							new Class[] { Long.TYPE });

					Long value4 = new Long(oldCplModel.getModUserId());

					method4.invoke(newModel, value4);

					Method method5 = newModelClass.getMethod("setCompanyId",
							new Class[] { Long.TYPE });

					Long value5 = new Long(oldCplModel.getCompanyId());

					method5.invoke(newModel, value5);

					Method method6 = newModelClass.getMethod("setEnabledRecaptcha",
							new Class[] { Boolean.TYPE });

					Boolean value6 = new Boolean(oldCplModel.getEnabledRecaptcha());

					method6.invoke(newModel, value6);

					Method method7 = newModelClass.getMethod("setRecaptchaPublicKey",
							new Class[] { String.class });

					String value7 = oldCplModel.getRecaptchaPublicKey();

					method7.invoke(newModel, value7);

					Method method8 = newModelClass.getMethod("setRecaptchaPrivateKey",
							new Class[] { String.class });

					String value8 = oldCplModel.getRecaptchaPrivateKey();

					method8.invoke(newModel, value8);

					Method method9 = newModelClass.getMethod("setEmailFromAddress",
							new Class[] { String.class });

					String value9 = oldCplModel.getEmailFromAddress();

					method9.invoke(newModel, value9);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	public static Object translateInput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateInput(curObj));
		}

		return newList;
	}

	public static Object translateInput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateInput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateInput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	public static Object translateOutput(BaseModel oldModel) {
		Class<?> oldModelClass = oldModel.getClass();

		String oldModelClassName = oldModelClass.getName();

		if (oldModelClassName.equals("net.portalteam.model.impl.FormImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					FormClp newModel = new FormClp();

					Method method0 = oldModelClass.getMethod("getFormId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFormId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCreateDate");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value1);

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2);

					Method method3 = oldModelClass.getMethod("getCreateUserId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setCreateUserId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getModUserId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setModUserId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getCompanyId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getTitle");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setTitle(value6);

					Method method7 = oldModelClass.getMethod("getName");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setName(value7);

					Method method8 = oldModelClass.getMethod("getEmail");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setEmail(value8);

					Method method9 = oldModelClass.getMethod("getLetterSubject");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setLetterSubject(value9);

					Method method10 = oldModelClass.getMethod(
							"getLetterTemplate");

					String value10 = (String)method10.invoke(oldModel,
							(Object[])null);

					newModel.setLetterTemplate(value10);

					Method method11 = oldModelClass.getMethod(
							"getSendButtonTitle");

					String value11 = (String)method11.invoke(oldModel,
							(Object[])null);

					newModel.setSendButtonTitle(value11);

					Method method12 = oldModelClass.getMethod(
							"getResetButtonTitle");

					String value12 = (String)method12.invoke(oldModel,
							(Object[])null);

					newModel.setResetButtonTitle(value12);

					Method method13 = oldModelClass.getMethod(
							"getShowResetButton");

					Boolean value13 = (Boolean)method13.invoke(oldModel,
							(Object[])null);

					newModel.setShowResetButton(value13.booleanValue());

					Method method14 = oldModelClass.getMethod("getCaptcha");

					Boolean value14 = (Boolean)method14.invoke(oldModel,
							(Object[])null);

					newModel.setCaptcha(value14.booleanValue());

					Method method15 = oldModelClass.getMethod("getStructure");

					String value15 = (String)method15.invoke(oldModel,
							(Object[])null);

					newModel.setStructure(value15);

					Method method16 = oldModelClass.getMethod(
							"getRenderTemplate");

					String value16 = (String)method16.invoke(oldModel,
							(Object[])null);

					newModel.setRenderTemplate(value16);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals("net.portalteam.model.impl.FormDataImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					FormDataClp newModel = new FormDataClp();

					Method method0 = oldModelClass.getMethod("getFormDataId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFormDataId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCreateDate");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value1);

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2);

					Method method3 = oldModelClass.getMethod("getCreateUserId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setCreateUserId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getModUserId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setModUserId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getFormId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setFormId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getData");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setData(value6);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals("net.portalteam.model.impl.FormFileImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					FormFileClp newModel = new FormFileClp();

					Method method0 = oldModelClass.getMethod("getFormFileId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFormFileId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCreateDate");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value1);

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2);

					Method method3 = oldModelClass.getMethod("getCreateUserId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setCreateUserId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getModUserId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setModUserId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getFormDataId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setFormDataId(value5.longValue());

					Method method6 = oldModelClass.getMethod("getFieldName");

					String value6 = (String)method6.invoke(oldModel,
							(Object[])null);

					newModel.setFieldName(value6);

					Method method7 = oldModelClass.getMethod("getFilename");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setFilename(value7);

					Method method8 = oldModelClass.getMethod("getMimeType");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setMimeType(value8);

					Method method9 = oldModelClass.getMethod("getData");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setData(value9);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		if (oldModelClassName.equals("net.portalteam.model.impl.FormConfigImpl")) {
			ClassLoader contextClassLoader = Thread.currentThread()
												   .getContextClassLoader();

			try {
				Thread.currentThread().setContextClassLoader(_classLoader);

				try {
					FormConfigClp newModel = new FormConfigClp();

					Method method0 = oldModelClass.getMethod("getFormConfigId");

					Long value0 = (Long)method0.invoke(oldModel, (Object[])null);

					newModel.setFormConfigId(value0.longValue());

					Method method1 = oldModelClass.getMethod("getCreateDate");

					Date value1 = (Date)method1.invoke(oldModel, (Object[])null);

					newModel.setCreateDate(value1);

					Method method2 = oldModelClass.getMethod("getModifiedDate");

					Date value2 = (Date)method2.invoke(oldModel, (Object[])null);

					newModel.setModifiedDate(value2);

					Method method3 = oldModelClass.getMethod("getCreateUserId");

					Long value3 = (Long)method3.invoke(oldModel, (Object[])null);

					newModel.setCreateUserId(value3.longValue());

					Method method4 = oldModelClass.getMethod("getModUserId");

					Long value4 = (Long)method4.invoke(oldModel, (Object[])null);

					newModel.setModUserId(value4.longValue());

					Method method5 = oldModelClass.getMethod("getCompanyId");

					Long value5 = (Long)method5.invoke(oldModel, (Object[])null);

					newModel.setCompanyId(value5.longValue());

					Method method6 = oldModelClass.getMethod(
							"getEnabledRecaptcha");

					Boolean value6 = (Boolean)method6.invoke(oldModel,
							(Object[])null);

					newModel.setEnabledRecaptcha(value6.booleanValue());

					Method method7 = oldModelClass.getMethod(
							"getRecaptchaPublicKey");

					String value7 = (String)method7.invoke(oldModel,
							(Object[])null);

					newModel.setRecaptchaPublicKey(value7);

					Method method8 = oldModelClass.getMethod(
							"getRecaptchaPrivateKey");

					String value8 = (String)method8.invoke(oldModel,
							(Object[])null);

					newModel.setRecaptchaPrivateKey(value8);

					Method method9 = oldModelClass.getMethod(
							"getEmailFromAddress");

					String value9 = (String)method9.invoke(oldModel,
							(Object[])null);

					newModel.setEmailFromAddress(value9);

					return newModel;
				}
				catch (Exception e) {
					_log.error(e, e);
				}
			}
			finally {
				Thread.currentThread().setContextClassLoader(contextClassLoader);
			}
		}

		return oldModel;
	}

	public static Object translateOutput(List<Object> oldList) {
		List<Object> newList = new ArrayList<Object>(oldList.size());

		for (int i = 0; i < oldList.size(); i++) {
			Object curObj = oldList.get(i);

			newList.add(translateOutput(curObj));
		}

		return newList;
	}

	public static Object translateOutput(Object obj) {
		if (obj instanceof BaseModel) {
			return translateOutput((BaseModel)obj);
		}
		else if (obj instanceof List) {
			return translateOutput((List<Object>)obj);
		}
		else {
			return obj;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(ClpSerializer.class);
	private static ClassLoader _classLoader;
}