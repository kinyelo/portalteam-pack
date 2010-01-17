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

import net.portalteam.NoSuchFormException;

import net.portalteam.model.Form;
import net.portalteam.model.impl.FormImpl;
import net.portalteam.model.impl.FormModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="FormPersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormPersistenceImpl extends BasePersistenceImpl
	implements FormPersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FormImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_COMPANYID = new FinderPath(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_COMPANYID = new FinderPath(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByCompanyId",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_COMPANYID = new FinderPath(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByCompanyId", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(Form form) {
		EntityCacheUtil.putResult(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormImpl.class, form.getPrimaryKey(), form);
	}

	public void cacheResult(List<Form> forms) {
		for (Form form : forms) {
			if (EntityCacheUtil.getResult(FormModelImpl.ENTITY_CACHE_ENABLED,
						FormImpl.class, form.getPrimaryKey(), this) == null) {
				cacheResult(form);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FormImpl.class.getName());
		EntityCacheUtil.clearCache(FormImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public Form create(long formId) {
		Form form = new FormImpl();

		form.setNew(true);
		form.setPrimaryKey(formId);

		return form;
	}

	public Form remove(long formId) throws NoSuchFormException, SystemException {
		Session session = null;

		try {
			session = openSession();

			Form form = (Form)session.get(FormImpl.class, new Long(formId));

			if (form == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No Form exists with the primary key " + formId);
				}

				throw new NoSuchFormException(
					"No Form exists with the primary key " + formId);
			}

			return remove(form);
		}
		catch (NoSuchFormException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public Form remove(Form form) throws SystemException {
		for (ModelListener<Form> listener : listeners) {
			listener.onBeforeRemove(form);
		}

		form = removeImpl(form);

		for (ModelListener<Form> listener : listeners) {
			listener.onAfterRemove(form);
		}

		return form;
	}

	protected Form removeImpl(Form form) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (form.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FormImpl.class,
						form.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(form);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormImpl.class, form.getPrimaryKey());

		return form;
	}

	public Form update(Form form) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(Form form) method. Use update(Form form, boolean merge) instead.");
		}

		return update(form, false);
	}

	public Form update(Form form, boolean merge) throws SystemException {
		boolean isNew = form.isNew();

		for (ModelListener<Form> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(form);
			}
			else {
				listener.onBeforeUpdate(form);
			}
		}

		form = updateImpl(form, merge);

		for (ModelListener<Form> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(form);
			}
			else {
				listener.onAfterUpdate(form);
			}
		}

		return form;
	}

	public Form updateImpl(net.portalteam.model.Form form, boolean merge)
		throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, form, merge);

			form.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FormModelImpl.ENTITY_CACHE_ENABLED,
			FormImpl.class, form.getPrimaryKey(), form);

		return form;
	}

	public Form findByPrimaryKey(long formId)
		throws NoSuchFormException, SystemException {
		Form form = fetchByPrimaryKey(formId);

		if (form == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No Form exists with the primary key " + formId);
			}

			throw new NoSuchFormException(
				"No Form exists with the primary key " + formId);
		}

		return form;
	}

	public Form fetchByPrimaryKey(long formId) throws SystemException {
		Form form = (Form)EntityCacheUtil.getResult(FormModelImpl.ENTITY_CACHE_ENABLED,
				FormImpl.class, formId, this);

		if (form == null) {
			Session session = null;

			try {
				session = openSession();

				form = (Form)session.get(FormImpl.class, new Long(formId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (form != null) {
					cacheResult(form);
				}

				closeSession(session);
			}
		}

		return form;
	}

	public List<Form> findByCompanyId(long companyId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(companyId) };

		List<Form> list = (List<Form>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.Form WHERE ");

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
					list = new ArrayList<Form>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<Form> findByCompanyId(long companyId, int start, int end)
		throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	public List<Form> findByCompanyId(long companyId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(companyId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Form> list = (List<Form>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.Form WHERE ");

				query.append("companyId = ?");

				query.append(" ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(companyId);

				list = (List<Form>)QueryUtil.list(q, getDialect(), start, end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Form>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_COMPANYID,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public Form findByCompanyId_First(long companyId, OrderByComparator obc)
		throws NoSuchFormException, SystemException {
		List<Form> list = findByCompanyId(companyId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Form exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Form findByCompanyId_Last(long companyId, OrderByComparator obc)
		throws NoSuchFormException, SystemException {
		int count = countByCompanyId(companyId);

		List<Form> list = findByCompanyId(companyId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No Form exists with the key {");

			msg.append("companyId=" + companyId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public Form[] findByCompanyId_PrevAndNext(long formId, long companyId,
		OrderByComparator obc) throws NoSuchFormException, SystemException {
		Form form = findByPrimaryKey(formId);

		int count = countByCompanyId(companyId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM net.portalteam.model.Form WHERE ");

			query.append("companyId = ?");

			query.append(" ");

			if (obc != null) {
				query.append("ORDER BY ");
				query.append(obc.getOrderBy());
			}

			Query q = session.createQuery(query.toString());

			QueryPos qPos = QueryPos.getInstance(q);

			qPos.add(companyId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, form);

			Form[] array = new FormImpl[3];

			array[0] = (Form)objArray[0];
			array[1] = (Form)objArray[1];
			array[2] = (Form)objArray[2];

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

	public List<Form> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<Form> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<Form> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<Form> list = (List<Form>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.Form ");

				if (obc != null) {
					query.append("ORDER BY ");
					query.append(obc.getOrderBy());
				}

				Query q = session.createQuery(query.toString());

				if (obc == null) {
					list = (List<Form>)QueryUtil.list(q, getDialect(), start,
							end, false);

					Collections.sort(list);
				}
				else {
					list = (List<Form>)QueryUtil.list(q, getDialect(), start,
							end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<Form>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByCompanyId(long companyId) throws SystemException {
		for (Form form : findByCompanyId(companyId)) {
			remove(form);
		}
	}

	public void removeAll() throws SystemException {
		for (Form form : findAll()) {
			remove(form);
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
				query.append("FROM net.portalteam.model.Form WHERE ");

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
						"SELECT COUNT(*) FROM net.portalteam.model.Form");

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
						"value.object.listener.net.portalteam.model.Form")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<Form>> listenersList = new ArrayList<ModelListener<Form>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<Form>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(FormPersistenceImpl.class);
}