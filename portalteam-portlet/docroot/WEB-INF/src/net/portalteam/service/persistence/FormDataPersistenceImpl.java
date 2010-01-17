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

import net.portalteam.NoSuchFormDataException;

import net.portalteam.model.FormData;
import net.portalteam.model.impl.FormDataImpl;
import net.portalteam.model.impl.FormDataModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="FormDataPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormDataPersistenceImpl extends BasePersistenceImpl
	implements FormDataPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FormDataImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_FORMID = new FinderPath(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFormId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_FORMID = new FinderPath(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFormId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_FORMID = new FinderPath(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByFormId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(FormData formData) {
		EntityCacheUtil.putResult(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataImpl.class, formData.getPrimaryKey(), formData);
	}

	public void cacheResult(List<FormData> formDatas) {
		for (FormData formData : formDatas) {
			if (EntityCacheUtil.getResult(
						FormDataModelImpl.ENTITY_CACHE_ENABLED,
						FormDataImpl.class, formData.getPrimaryKey(), this) == null) {
				cacheResult(formData);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FormDataImpl.class.getName());
		EntityCacheUtil.clearCache(FormDataImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public FormData create(long formDataId) {
		FormData formData = new FormDataImpl();

		formData.setNew(true);
		formData.setPrimaryKey(formDataId);

		return formData;
	}

	public FormData remove(long formDataId)
		throws NoSuchFormDataException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FormData formData = (FormData)session.get(FormDataImpl.class,
					new Long(formDataId));

			if (formData == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No FormData exists with the primary key " +
						formDataId);
				}

				throw new NoSuchFormDataException(
					"No FormData exists with the primary key " + formDataId);
			}

			return remove(formData);
		}
		catch (NoSuchFormDataException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public FormData remove(FormData formData) throws SystemException {
		for (ModelListener<FormData> listener : listeners) {
			listener.onBeforeRemove(formData);
		}

		formData = removeImpl(formData);

		for (ModelListener<FormData> listener : listeners) {
			listener.onAfterRemove(formData);
		}

		return formData;
	}

	protected FormData removeImpl(FormData formData) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (formData.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FormDataImpl.class,
						formData.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(formData);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataImpl.class, formData.getPrimaryKey());

		return formData;
	}

	public FormData update(FormData formData) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(FormData formData) method. Use update(FormData formData, boolean merge) instead.");
		}

		return update(formData, false);
	}

	public FormData update(FormData formData, boolean merge)
		throws SystemException {
		boolean isNew = formData.isNew();

		for (ModelListener<FormData> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(formData);
			}
			else {
				listener.onBeforeUpdate(formData);
			}
		}

		formData = updateImpl(formData, merge);

		for (ModelListener<FormData> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(formData);
			}
			else {
				listener.onAfterUpdate(formData);
			}
		}

		return formData;
	}

	public FormData updateImpl(net.portalteam.model.FormData formData,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, formData, merge);

			formData.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FormDataModelImpl.ENTITY_CACHE_ENABLED,
			FormDataImpl.class, formData.getPrimaryKey(), formData);

		return formData;
	}

	public FormData findByPrimaryKey(long formDataId)
		throws NoSuchFormDataException, SystemException {
		FormData formData = fetchByPrimaryKey(formDataId);

		if (formData == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No FormData exists with the primary key " +
					formDataId);
			}

			throw new NoSuchFormDataException(
				"No FormData exists with the primary key " + formDataId);
		}

		return formData;
	}

	public FormData fetchByPrimaryKey(long formDataId)
		throws SystemException {
		FormData formData = (FormData)EntityCacheUtil.getResult(FormDataModelImpl.ENTITY_CACHE_ENABLED,
				FormDataImpl.class, formDataId, this);

		if (formData == null) {
			Session session = null;

			try {
				session = openSession();

				formData = (FormData)session.get(FormDataImpl.class,
						new Long(formDataId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (formData != null) {
					cacheResult(formData);
				}

				closeSession(session);
			}
		}

		return formData;
	}

	public List<FormData> findByFormId(long formId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(formId) };

		List<FormData> list = (List<FormData>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FORMID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormData WHERE ");

				query.append("formId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormData>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FORMID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<FormData> findByFormId(long formId, int start, int end)
		throws SystemException {
		return findByFormId(formId, start, end, null);
	}

	public List<FormData> findByFormId(long formId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(formId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormData> list = (List<FormData>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_FORMID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormData WHERE ");

				query.append("formId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate ASC");
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formId);

				list = (List<FormData>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormData>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_FORMID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public FormData findByFormId_First(long formId, OrderByComparator obc)
		throws NoSuchFormDataException, SystemException {
		List<FormData> list = findByFormId(formId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormData exists with the key {");

			msg.append("formId=" + formId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormDataException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormData findByFormId_Last(long formId, OrderByComparator obc)
		throws NoSuchFormDataException, SystemException {
		int count = countByFormId(formId);

		List<FormData> list = findByFormId(formId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormData exists with the key {");

			msg.append("formId=" + formId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormDataException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormData[] findByFormId_PrevAndNext(long formDataId, long formId,
		OrderByComparator obc) throws NoSuchFormDataException, SystemException {
		FormData formData = findByPrimaryKey(formDataId);

		int count = countByFormId(formId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM net.portalteam.model.FormData WHERE ");

			query.append("formId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			else {
				query.append("ORDER BY ");

				query.append("createDate ASC");
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(formId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, formData);

			FormData[] array = new FormDataImpl[3];

			array[0] = (FormData)objArray[0];
			array[1] = (FormData)objArray[1];
			array[2] = (FormData)objArray[2];

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

	public List<FormData> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<FormData> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<FormData> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormData> list = (List<FormData>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormData ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				else {
					query.append("ORDER BY ");

					query.append("createDate ASC");
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<FormData>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FormData>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormData>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByFormId(long formId) throws SystemException {
		for (FormData formData : findByFormId(formId)) {
			remove(formData);
		}
	}

	public void removeAll() throws SystemException {
		for (FormData formData : findAll()) {
			remove(formData);
		}
	}

	public int countByFormId(long formId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(formId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FORMID,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM net.portalteam.model.FormData WHERE ");

				query.append("formId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FORMID,
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
						"SELECT COUNT(*) FROM net.portalteam.model.FormData");

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
						"value.object.listener.net.portalteam.model.FormData")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FormData>> listenersList = new ArrayList<ModelListener<FormData>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FormData>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(FormDataPersistenceImpl.class);
}