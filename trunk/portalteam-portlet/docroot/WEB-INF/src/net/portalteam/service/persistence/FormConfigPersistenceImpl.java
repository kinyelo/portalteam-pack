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

import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.annotation.BeanReference;
import com.liferay.portal.kernel.cache.CacheRegistry;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.EntityCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderCacheUtil;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.ModelListener;
import com.liferay.portal.service.persistence.BatchSessionUtil;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;

import net.portalteam.NoSuchFormConfigException;

import net.portalteam.model.FormConfig;
import net.portalteam.model.impl.FormConfigImpl;
import net.portalteam.model.impl.FormConfigModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="FormConfigPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormConfigPersistenceImpl extends BasePersistenceImpl
	implements FormConfigPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FormConfigImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(FormConfig formConfig) {
		EntityCacheUtil.putResult(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigImpl.class, formConfig.getPrimaryKey(), formConfig);
	}

	public void cacheResult(List<FormConfig> formConfigs) {
		for (FormConfig formConfig : formConfigs) {
			if (EntityCacheUtil.getResult(
						FormConfigModelImpl.ENTITY_CACHE_ENABLED,
						FormConfigImpl.class, formConfig.getPrimaryKey(), this) == null) {
				cacheResult(formConfig);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FormConfigImpl.class.getName());
		EntityCacheUtil.clearCache(FormConfigImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public FormConfig create(long formConfigId) {
		FormConfig formConfig = new FormConfigImpl();

		formConfig.setNew(true);
		formConfig.setPrimaryKey(formConfigId);

		return formConfig;
	}

	public FormConfig remove(long formConfigId)
		throws NoSuchFormConfigException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FormConfig formConfig = (FormConfig)session.get(FormConfigImpl.class,
					new Long(formConfigId));

			if (formConfig == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No FormConfig exists with the primary key " +
						formConfigId);
				}

				throw new NoSuchFormConfigException(
					"No FormConfig exists with the primary key " +
					formConfigId);
			}

			return remove(formConfig);
		}
		catch (NoSuchFormConfigException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public FormConfig remove(FormConfig formConfig) throws SystemException {
		for (ModelListener<FormConfig> listener : listeners) {
			listener.onBeforeRemove(formConfig);
		}

		formConfig = removeImpl(formConfig);

		for (ModelListener<FormConfig> listener : listeners) {
			listener.onAfterRemove(formConfig);
		}

		return formConfig;
	}

	protected FormConfig removeImpl(FormConfig formConfig)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (formConfig.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FormConfigImpl.class,
						formConfig.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(formConfig);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigImpl.class, formConfig.getPrimaryKey());

		return formConfig;
	}

	public FormConfig update(FormConfig formConfig) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(FormConfig formConfig) method. Use update(FormConfig formConfig, boolean merge) instead.");
		}

		return update(formConfig, false);
	}

	public FormConfig update(FormConfig formConfig, boolean merge)
		throws SystemException {
		boolean isNew = formConfig.isNew();

		for (ModelListener<FormConfig> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(formConfig);
			}
			else {
				listener.onBeforeUpdate(formConfig);
			}
		}

		formConfig = updateImpl(formConfig, merge);

		for (ModelListener<FormConfig> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(formConfig);
			}
			else {
				listener.onAfterUpdate(formConfig);
			}
		}

		return formConfig;
	}

	public FormConfig updateImpl(net.portalteam.model.FormConfig formConfig,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, formConfig, merge);

			formConfig.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
			FormConfigImpl.class, formConfig.getPrimaryKey(), formConfig);

		return formConfig;
	}

	public FormConfig findByPrimaryKey(long formConfigId)
		throws NoSuchFormConfigException, SystemException {
		FormConfig formConfig = fetchByPrimaryKey(formConfigId);

		if (formConfig == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No FormConfig exists with the primary key " +
					formConfigId);
			}

			throw new NoSuchFormConfigException(
				"No FormConfig exists with the primary key " + formConfigId);
		}

		return formConfig;
	}

	public FormConfig fetchByPrimaryKey(long formConfigId)
		throws SystemException {
		FormConfig formConfig = (FormConfig)EntityCacheUtil.getResult(FormConfigModelImpl.ENTITY_CACHE_ENABLED,
				FormConfigImpl.class, formConfigId, this);

		if (formConfig == null) {
			Session session = null;

			try {
				session = openSession();

				formConfig = (FormConfig)session.get(FormConfigImpl.class,
						new Long(formConfigId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (formConfig != null) {
					cacheResult(formConfig);
				}

				closeSession(session);
			}
		}

		return formConfig;
	}

	public List<FormConfig> findByCompanyId(long companyId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<FormConfig> list = (List<FormConfig>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormConfig WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormConfig>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<FormConfig> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<FormConfig> findByCompanyId(long companyId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormConfig> list = (List<FormConfig>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormConfig WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<FormConfig>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormConfig>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public FormConfig findByCompanyId_First(long companyId,
		OrderByComparator obc)
		throws NoSuchFormConfigException, SystemException {
		List<FormConfig> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormConfig exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormConfigException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormConfig findByCompanyId_Last(long companyId, OrderByComparator obc)
		throws NoSuchFormConfigException, SystemException {
		int count = countByCompanyId(companyId);

		List<FormConfig> list = findByCompanyId(companyId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormConfig exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormConfigException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormConfig[] findByCompanyId_PrevAndNext(long formConfigId,
		long companyId, OrderByComparator obc)
		throws NoSuchFormConfigException, SystemException {
		FormConfig formConfig = findByPrimaryKey(formConfigId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM net.portalteam.model.FormConfig WHERE ");

			query.append("companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc,
					formConfig);

			FormConfig[] array = new FormConfigImpl[3];

			array[0] = (FormConfig)objArray[0];
			array[1] = (FormConfig)objArray[1];
			array[2] = (FormConfig)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			dynamicQuery.setLimit(start, end);

			dynamicQuery.compile(session);

			return dynamicQuery.list();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<FormConfig> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<FormConfig> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	public List<FormConfig> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormConfig> list = (List<FormConfig>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormConfig ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<FormConfig>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FormConfig>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormConfig>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (FormConfig formConfig : findByCompanyId(companyId)) {
			remove(formConfig);
		}
	}

	public void removeAll() throws SystemException {
		for (FormConfig formConfig : findAll()) {
			remove(formConfig);
		}
	}

	public int countByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_COMPANYID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM net.portalteam.model.FormConfig WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_COMPANYID,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countAll() throws SystemException {
		Object[] finderArgs = new Object[0];

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_ALL,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(
						"SELECT COUNT(*) FROM net.portalteam.model.FormConfig");

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_ALL, finderArgs,
					count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public void afterPropertiesSet() {
		String[] listenerClassNames = StringUtil.split(GetterUtil.getString(
					com.liferay.util.service.ServiceProps.get(
						"value.object.listener.net.portalteam.model.FormConfig")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FormConfig>> listenersList = new ArrayList<ModelListener<FormConfig>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FormConfig>)Class.forName(
							listenerClassName).newInstance());
				}

				listeners = listenersList.toArray(new ModelListener[listenersList.size()]);
			}
			catch (Exception e) {
				_log.error(e);
			}
		}
	}

	@BeanReference(name = "net.portalteam.service.persistence.FormPersistence.impl")
	protected net.portalteam.service.persistence.FormPersistence formPersistence;
	@BeanReference(name = "net.portalteam.service.persistence.FormDataPersistence.impl")
	protected net.portalteam.service.persistence.FormDataPersistence formDataPersistence;
	@BeanReference(name = "net.portalteam.service.persistence.FormFilePersistence.impl")
	protected net.portalteam.service.persistence.FormFilePersistence formFilePersistence;
	@BeanReference(name = "net.portalteam.service.persistence.FormConfigPersistence.impl")
	protected net.portalteam.service.persistence.FormConfigPersistence formConfigPersistence;
	private static Log _log = LogFactoryUtil.getLog(FormConfigPersistenceImpl.class);
}