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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * <a href="FormFilePersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface FormFilePersistence extends BasePersistence {
	public void cacheResult(net.portalteam.model.FormFile formFile);

	public void cacheResult(
		java.util.List<net.portalteam.model.FormFile> formFiles);

	public void clearCache();

	public net.portalteam.model.FormFile create(long formFileId);

	public net.portalteam.model.FormFile remove(long formFileId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public net.portalteam.model.FormFile remove(
		net.portalteam.model.FormFile formFile)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormFile update(
		net.portalteam.model.FormFile formFile)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormFile update(
		net.portalteam.model.FormFile formFile, boolean merge)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormFile updateImpl(
		net.portalteam.model.FormFile formFile, boolean merge)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormFile findByPrimaryKey(long formFileId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public net.portalteam.model.FormFile fetchByPrimaryKey(long formFileId)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findByFieldName(
		long formDataId, java.lang.String fieldName)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findByFieldName(
		long formDataId, java.lang.String fieldName, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findByFieldName(
		long formDataId, java.lang.String fieldName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormFile findByFieldName_First(
		long formDataId, java.lang.String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public net.portalteam.model.FormFile findByFieldName_Last(long formDataId,
		java.lang.String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public net.portalteam.model.FormFile[] findByFieldName_PrevAndNext(
		long formFileId, long formDataId, java.lang.String fieldName,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public java.util.List<net.portalteam.model.FormFile> findByFormData(
		long formDataId) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findByFormData(
		long formDataId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findByFormData(
		long formDataId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormFile findByFormData_First(long formDataId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public net.portalteam.model.FormFile findByFormData_Last(long formDataId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public net.portalteam.model.FormFile[] findByFormData_PrevAndNext(
		long formFileId, long formDataId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormFileException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormFile> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByFieldName(long formDataId, java.lang.String fieldName)
		throws com.liferay.portal.SystemException;

	public void removeByFormData(long formDataId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByFieldName(long formDataId, java.lang.String fieldName)
		throws com.liferay.portal.SystemException;

	public int countByFormData(long formDataId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}