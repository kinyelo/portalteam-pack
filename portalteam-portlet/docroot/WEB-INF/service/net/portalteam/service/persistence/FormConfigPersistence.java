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
 * <a href="FormConfigPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public interface FormConfigPersistence extends BasePersistence {
	public void cacheResult(net.portalteam.model.FormConfig formConfig);

	public void cacheResult(
		java.util.List<net.portalteam.model.FormConfig> formConfigs);

	public void clearCache();

	public net.portalteam.model.FormConfig create(long formConfigId);

	public net.portalteam.model.FormConfig remove(long formConfigId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException;

	public net.portalteam.model.FormConfig remove(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormConfig update(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormConfig update(
		net.portalteam.model.FormConfig formConfig, boolean merge)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormConfig updateImpl(
		net.portalteam.model.FormConfig formConfig, boolean merge)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormConfig findByPrimaryKey(long formConfigId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException;

	public net.portalteam.model.FormConfig fetchByPrimaryKey(long formConfigId)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormConfig> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormConfig> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormConfig> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public net.portalteam.model.FormConfig findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException;

	public net.portalteam.model.FormConfig findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException;

	public net.portalteam.model.FormConfig[] findByCompanyId_PrevAndNext(
		long formConfigId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.SystemException;

	public java.util.List<Object> findWithDynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormConfig> findAll()
		throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormConfig> findAll(int start,
		int end) throws com.liferay.portal.SystemException;

	public java.util.List<net.portalteam.model.FormConfig> findAll(int start,
		int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public void removeAll() throws com.liferay.portal.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException;

	public int countAll() throws com.liferay.portal.SystemException;
}