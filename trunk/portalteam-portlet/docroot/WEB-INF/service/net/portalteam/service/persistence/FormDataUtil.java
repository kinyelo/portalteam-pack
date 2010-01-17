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

package net.portalteam.service.persistence;

/**
 * <a href="FormDataUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormDataUtil {
	public static void cacheResult(net.portalteam.model.FormData formData) {
		getPersistence().cacheResult(formData);
	}

	public static void cacheResult(
		java.util.List<net.portalteam.model.FormData> formDatas) {
		getPersistence().cacheResult(formDatas);
	}

	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static net.portalteam.model.FormData create(long formDataId) {
		return getPersistence().create(formDataId);
	}

	public static net.portalteam.model.FormData remove(long formDataId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException {
		return getPersistence().remove(formDataId);
	}

	public static net.portalteam.model.FormData remove(
		net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(formData);
	}

	public static net.portalteam.model.FormData update(
		net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(formData);
	}

	public static net.portalteam.model.FormData update(
		net.portalteam.model.FormData formData, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(formData, merge);
	}

	public static net.portalteam.model.FormData updateImpl(
		net.portalteam.model.FormData formData, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(formData, merge);
	}

	public static net.portalteam.model.FormData findByPrimaryKey(
		long formDataId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException {
		return getPersistence().findByPrimaryKey(formDataId);
	}

	public static net.portalteam.model.FormData fetchByPrimaryKey(
		long formDataId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(formDataId);
	}

	public static java.util.List<net.portalteam.model.FormData> findByFormId(
		long formId) throws com.liferay.portal.SystemException {
		return getPersistence().findByFormId(formId);
	}

	public static java.util.List<net.portalteam.model.FormData> findByFormId(
		long formId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFormId(formId, start, end);
	}

	public static java.util.List<net.portalteam.model.FormData> findByFormId(
		long formId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFormId(formId, start, end, obc);
	}

	public static net.portalteam.model.FormData findByFormId_First(
		long formId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException {
		return getPersistence().findByFormId_First(formId, obc);
	}

	public static net.portalteam.model.FormData findByFormId_Last(long formId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException {
		return getPersistence().findByFormId_Last(formId, obc);
	}

	public static net.portalteam.model.FormData[] findByFormId_PrevAndNext(
		long formDataId, long formId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException {
		return getPersistence().findByFormId_PrevAndNext(formDataId, formId, obc);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	public static java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	public static java.util.List<net.portalteam.model.FormData> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<net.portalteam.model.FormData> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<net.portalteam.model.FormData> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByFormId(long formId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByFormId(formId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByFormId(long formId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByFormId(formId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static FormDataPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(FormDataPersistence persistence) {
		_persistence = persistence;
	}

	private static FormDataPersistence _persistence;
}