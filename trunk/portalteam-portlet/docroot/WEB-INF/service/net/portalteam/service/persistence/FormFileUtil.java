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
 * <a href="FormFileUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormFileUtil {
	public static void cacheResult(net.portalteam.model.FormFile formFile) {
		getPersistence().cacheResult(formFile);
	}

	public static void cacheResult(
		java.util.List<net.portalteam.model.FormFile> formFiles) {
		getPersistence().cacheResult(formFiles);
	}

	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static net.portalteam.model.FormFile create(long formFileId) {
		return getPersistence().create(formFileId);
	}

	public static net.portalteam.model.FormFile remove(long formFileId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence().remove(formFileId);
	}

	public static net.portalteam.model.FormFile remove(
		net.portalteam.model.FormFile formFile)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(formFile);
	}

	public static net.portalteam.model.FormFile update(
		net.portalteam.model.FormFile formFile)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(formFile);
	}

	public static net.portalteam.model.FormFile update(
		net.portalteam.model.FormFile formFile, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(formFile, merge);
	}

	public static net.portalteam.model.FormFile updateImpl(
		net.portalteam.model.FormFile formFile, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(formFile, merge);
	}

	public static net.portalteam.model.FormFile findByPrimaryKey(
		long formFileId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence().findByPrimaryKey(formFileId);
	}

	public static net.portalteam.model.FormFile fetchByPrimaryKey(
		long formFileId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(formFileId);
	}

	public static java.util.List<net.portalteam.model.FormFile> findByFieldName(
		long formDataId, java.lang.String fieldName)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFieldName(formDataId, fieldName);
	}

	public static java.util.List<net.portalteam.model.FormFile> findByFieldName(
		long formDataId, java.lang.String fieldName, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByFieldName(formDataId, fieldName, start, end);
	}

	public static java.util.List<net.portalteam.model.FormFile> findByFieldName(
		long formDataId, java.lang.String fieldName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence()
				   .findByFieldName(formDataId, fieldName, start, end, obc);
	}

	public static net.portalteam.model.FormFile findByFieldName_First(
		long formDataId, java.lang.String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence().findByFieldName_First(formDataId, fieldName, obc);
	}

	public static net.portalteam.model.FormFile findByFieldName_Last(
		long formDataId, java.lang.String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence().findByFieldName_Last(formDataId, fieldName, obc);
	}

	public static net.portalteam.model.FormFile[] findByFieldName_PrevAndNext(
		long formFileId, long formDataId, java.lang.String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence()
				   .findByFieldName_PrevAndNext(formFileId, formDataId,
			fieldName, obc);
	}

	public static java.util.List<net.portalteam.model.FormFile> findByFormData(
		long formDataId) throws com.liferay.portal.SystemException {
		return getPersistence().findByFormData(formDataId);
	}

	public static java.util.List<net.portalteam.model.FormFile> findByFormData(
		long formDataId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFormData(formDataId, start, end);
	}

	public static java.util.List<net.portalteam.model.FormFile> findByFormData(
		long formDataId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByFormData(formDataId, start, end, obc);
	}

	public static net.portalteam.model.FormFile findByFormData_First(
		long formDataId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence().findByFormData_First(formDataId, obc);
	}

	public static net.portalteam.model.FormFile findByFormData_Last(
		long formDataId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence().findByFormData_Last(formDataId, obc);
	}

	public static net.portalteam.model.FormFile[] findByFormData_PrevAndNext(
		long formFileId, long formDataId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException {
		return getPersistence()
				   .findByFormData_PrevAndNext(formFileId, formDataId, obc);
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

	public static java.util.List<net.portalteam.model.FormFile> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<net.portalteam.model.FormFile> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<net.portalteam.model.FormFile> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByFieldName(long formDataId,
		java.lang.String fieldName) throws com.liferay.portal.SystemException {
		getPersistence().removeByFieldName(formDataId, fieldName);
	}

	public static void removeByFormData(long formDataId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByFormData(formDataId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByFieldName(long formDataId,
		java.lang.String fieldName) throws com.liferay.portal.SystemException {
		return getPersistence().countByFieldName(formDataId, fieldName);
	}

	public static int countByFormData(long formDataId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByFormData(formDataId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static FormFilePersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(FormFilePersistence persistence) {
		_persistence = persistence;
	}

	private static FormFilePersistence _persistence;
}