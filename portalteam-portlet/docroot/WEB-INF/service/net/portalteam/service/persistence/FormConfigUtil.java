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
 * <a href="FormConfigUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormConfigUtil {
	public static void cacheResult(net.portalteam.model.FormConfig formConfig) {
		getPersistence().cacheResult(formConfig);
	}

	public static void cacheResult(
		java.util.List<net.portalteam.model.FormConfig> formConfigs) {
		getPersistence().cacheResult(formConfigs);
	}

	public static void clearCache() {
		getPersistence().clearCache();
	}

	public static net.portalteam.model.FormConfig create(long formConfigId) {
		return getPersistence().create(formConfigId);
	}

	public static net.portalteam.model.FormConfig remove(long formConfigId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException {
		return getPersistence().remove(formConfigId);
	}

	public static net.portalteam.model.FormConfig remove(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException {
		return getPersistence().remove(formConfig);
	}

	public static net.portalteam.model.FormConfig update(
		net.portalteam.model.FormConfig formConfig)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(formConfig);
	}

	public static net.portalteam.model.FormConfig update(
		net.portalteam.model.FormConfig formConfig, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().update(formConfig, merge);
	}

	public static net.portalteam.model.FormConfig updateImpl(
		net.portalteam.model.FormConfig formConfig, boolean merge)
		throws com.liferay.portal.SystemException {
		return getPersistence().updateImpl(formConfig, merge);
	}

	public static net.portalteam.model.FormConfig findByPrimaryKey(
		long formConfigId)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException {
		return getPersistence().findByPrimaryKey(formConfigId);
	}

	public static net.portalteam.model.FormConfig fetchByPrimaryKey(
		long formConfigId) throws com.liferay.portal.SystemException {
		return getPersistence().fetchByPrimaryKey(formConfigId);
	}

	public static java.util.List<net.portalteam.model.FormConfig> findByCompanyId(
		long companyId) throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<net.portalteam.model.FormConfig> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<net.portalteam.model.FormConfig> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end, obc);
	}

	public static net.portalteam.model.FormConfig findByCompanyId_First(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException {
		return getPersistence().findByCompanyId_First(companyId, obc);
	}

	public static net.portalteam.model.FormConfig findByCompanyId_Last(
		long companyId, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException {
		return getPersistence().findByCompanyId_Last(companyId, obc);
	}

	public static net.portalteam.model.FormConfig[] findByCompanyId_PrevAndNext(
		long formConfigId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException,
			net.portalteam.NoSuchFormConfigException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(formConfigId, companyId, obc);
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

	public static java.util.List<net.portalteam.model.FormConfig> findAll()
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<net.portalteam.model.FormConfig> findAll(
		int start, int end) throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<net.portalteam.model.FormConfig> findAll(
		int start, int end, com.liferay.portal.kernel.util.OrderByComparator obc)
		throws com.liferay.portal.SystemException {
		return getPersistence().findAll(start, end, obc);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeAll() throws com.liferay.portal.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countAll() throws com.liferay.portal.SystemException {
		return getPersistence().countAll();
	}

	public static FormConfigPersistence getPersistence() {
		return _persistence;
	}

	public void setPersistence(FormConfigPersistence persistence) {
		_persistence = persistence;
	}

	private static FormConfigPersistence _persistence;
}