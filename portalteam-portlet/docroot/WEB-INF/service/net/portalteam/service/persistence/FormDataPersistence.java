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
 * <a href="FormDataPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface FormDataPersistence extends BasePersistence {
	public void cacheResult(net.portalteam.model.FormData formData);

	public void cacheResult(
		java.util.List<net.portalteam.model.FormData> formDatas);

	public void clearCache();

	public net.portalteam.model.FormData create(long formDataId);

	public net.portalteam.model.FormData remove(long formDataId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException;

	public net.portalteam.model.FormData remove(
		net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormData update(
		net.portalteam.model.FormData formData)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormData update(
		net.portalteam.model.FormData formData, boolean merge)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormData updateImpl(
		net.portalteam.model.FormData formData, boolean merge)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormData findByPrimaryKey(long formDataId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException;

	public net.portalteam.model.FormData fetchByPrimaryKey(long formDataId)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormData> findByFormId(
		long formId) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormData> findByFormId(
		long formId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormData> findByFormId(
		long formId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormData findByFormId_First(long formId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException;

	public net.portalteam.model.FormData findByFormId_Last(long formId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException;

	public net.portalteam.model.FormData[] findByFormId_PrevAndNext(
		long formDataId, long formId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormDataException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormData> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormData> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormData> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByFormId(long formId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByFormId(long formId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}