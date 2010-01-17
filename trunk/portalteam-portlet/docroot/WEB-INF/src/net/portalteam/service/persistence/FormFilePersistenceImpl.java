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

import net.portalteam.NoSuchFormFileException;

import net.portalteam.model.FormFile;
import net.portalteam.model.impl.FormFileImpl;
import net.portalteam.model.impl.FormFileModelImpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <a href="FormFilePersistenceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class FormFilePersistenceImpl extends BasePersistenceImpl
	implements FormFilePersistence {
	public static final String FINDER_CLASS_NAME_ENTITY = FormFileImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST = FINDER_CLASS_NAME_ENTITY +
		".List";
	public static final FinderPath FINDER_PATH_FIND_BY_FIELDNAME = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFieldName",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_FIELDNAME = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFieldName",
			new String[] {
				Long.class.getName(), String.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_FIELDNAME = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByFieldName",
			new String[] { Long.class.getName(), String.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_FORMDATA = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFormData", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_BY_OBC_FORMDATA = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findByFormData",
			new String[] {
				Long.class.getName(),
				
			"java.lang.Integer", "java.lang.Integer",
				"com.liferay.portal.kernel.util.OrderByComparator"
			});
	public static final FinderPath FINDER_PATH_COUNT_BY_FORMDATA = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countByFormData", new String[] { Long.class.getName() });
	public static final FinderPath FINDER_PATH_FIND_ALL = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileModelImpl.FINDER_CACHE_ENABLED, FINDER_CLASS_NAME_LIST,
			"countAll", new String[0]);

	public void cacheResult(FormFile formFile) {
		EntityCacheUtil.putResult(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileImpl.class, formFile.getPrimaryKey(), formFile);
	}

	public void cacheResult(List<FormFile> formFiles) {
		for (FormFile formFile : formFiles) {
			if (EntityCacheUtil.getResult(
						FormFileModelImpl.ENTITY_CACHE_ENABLED,
						FormFileImpl.class, formFile.getPrimaryKey(), this) == null) {
				cacheResult(formFile);
			}
		}
	}

	public void clearCache() {
		CacheRegistry.clear(FormFileImpl.class.getName());
		EntityCacheUtil.clearCache(FormFileImpl.class.getName());
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_ENTITY);
		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);
	}

	public FormFile create(long formFileId) {
		FormFile formFile = new FormFileImpl();

		formFile.setNew(true);
		formFile.setPrimaryKey(formFileId);

		return formFile;
	}

	public FormFile remove(long formFileId)
		throws NoSuchFormFileException, SystemException {
		Session session = null;

		try {
			session = openSession();

			FormFile formFile = (FormFile)session.get(FormFileImpl.class,
					new Long(formFileId));

			if (formFile == null) {
				if (_log.isWarnEnabled()) {
					_log.warn("No FormFile exists with the primary key " +
						formFileId);
				}

				throw new NoSuchFormFileException(
					"No FormFile exists with the primary key " + formFileId);
			}

			return remove(formFile);
		}
		catch (NoSuchFormFileException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public FormFile remove(FormFile formFile) throws SystemException {
		for (ModelListener<FormFile> listener : listeners) {
			listener.onBeforeRemove(formFile);
		}

		formFile = removeImpl(formFile);

		for (ModelListener<FormFile> listener : listeners) {
			listener.onAfterRemove(formFile);
		}

		return formFile;
	}

	protected FormFile removeImpl(FormFile formFile) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			if (formFile.isCachedModel() || BatchSessionUtil.isEnabled()) {
				Object staleObject = session.get(FormFileImpl.class,
						formFile.getPrimaryKeyObj());

				if (staleObject != null) {
					session.evict(staleObject);
				}
			}

			session.delete(formFile);

			session.flush();
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.removeResult(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileImpl.class, formFile.getPrimaryKey());

		return formFile;
	}

	public FormFile update(FormFile formFile) throws SystemException {
		if (_log.isWarnEnabled()) {
			_log.warn(
				"Using the deprecated update(FormFile formFile) method. Use update(FormFile formFile, boolean merge) instead.");
		}

		return update(formFile, false);
	}

	public FormFile update(FormFile formFile, boolean merge)
		throws SystemException {
		boolean isNew = formFile.isNew();

		for (ModelListener<FormFile> listener : listeners) {
			if (isNew) {
				listener.onBeforeCreate(formFile);
			}
			else {
				listener.onBeforeUpdate(formFile);
			}
		}

		formFile = updateImpl(formFile, merge);

		for (ModelListener<FormFile> listener : listeners) {
			if (isNew) {
				listener.onAfterCreate(formFile);
			}
			else {
				listener.onAfterUpdate(formFile);
			}
		}

		return formFile;
	}

	public FormFile updateImpl(net.portalteam.model.FormFile formFile,
		boolean merge) throws SystemException {
		Session session = null;

		try {
			session = openSession();

			BatchSessionUtil.update(session, formFile, merge);

			formFile.setNew(false);
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		FinderCacheUtil.clearCache(FINDER_CLASS_NAME_LIST);

		EntityCacheUtil.putResult(FormFileModelImpl.ENTITY_CACHE_ENABLED,
			FormFileImpl.class, formFile.getPrimaryKey(), formFile);

		return formFile;
	}

	public FormFile findByPrimaryKey(long formFileId)
		throws NoSuchFormFileException, SystemException {
		FormFile formFile = fetchByPrimaryKey(formFileId);

		if (formFile == null) {
			if (_log.isWarnEnabled()) {
				_log.warn("No FormFile exists with the primary key " +
					formFileId);
			}

			throw new NoSuchFormFileException(
				"No FormFile exists with the primary key " + formFileId);
		}

		return formFile;
	}

	public FormFile fetchByPrimaryKey(long formFileId)
		throws SystemException {
		FormFile formFile = (FormFile)EntityCacheUtil.getResult(FormFileModelImpl.ENTITY_CACHE_ENABLED,
				FormFileImpl.class, formFileId, this);

		if (formFile == null) {
			Session session = null;

			try {
				session = openSession();

				formFile = (FormFile)session.get(FormFileImpl.class,
						new Long(formFileId));
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (formFile != null) {
					cacheResult(formFile);
				}

				closeSession(session);
			}
		}

		return formFile;
	}

	public List<FormFile> findByFieldName(long formDataId, String fieldName)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(formDataId), fieldName };

		List<FormFile> list = (List<FormFile>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FIELDNAME,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormFile WHERE ");

				query.append("formDataId = ?");

				query.append(" AND ");

				if (fieldName == null) {
					query.append("fieldName IS NULL");
				}
				else {
					query.append("fieldName = ?");
				}

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formDataId);

				if (fieldName != null) {
					qPos.add(fieldName);
				}

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormFile>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FIELDNAME,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<FormFile> findByFieldName(long formDataId, String fieldName,
		int start, int end) throws SystemException {
		return findByFieldName(formDataId, fieldName, start, end, null);
	}

	public List<FormFile> findByFieldName(long formDataId, String fieldName,
		int start, int end, OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(formDataId),
				
				fieldName,
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormFile> list = (List<FormFile>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_FIELDNAME,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormFile WHERE ");

				query.append("formDataId = ?");

				query.append(" AND ");

				if (fieldName == null) {
					query.append("fieldName IS NULL");
				}
				else {
					query.append("fieldName = ?");
				}

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

				qPos.add(formDataId);

				if (fieldName != null) {
					qPos.add(fieldName);
				}

				list = (List<FormFile>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormFile>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_FIELDNAME,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public FormFile findByFieldName_First(long formDataId, String fieldName,
		OrderByComparator obc) throws NoSuchFormFileException, SystemException {
		List<FormFile> list = findByFieldName(formDataId, fieldName, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormFile exists with the key {");

			msg.append("formDataId=" + formDataId);

			msg.append(", ");
			msg.append("fieldName=" + fieldName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormFileException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormFile findByFieldName_Last(long formDataId, String fieldName,
		OrderByComparator obc) throws NoSuchFormFileException, SystemException {
		int count = countByFieldName(formDataId, fieldName);

		List<FormFile> list = findByFieldName(formDataId, fieldName, count - 1,
				count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormFile exists with the key {");

			msg.append("formDataId=" + formDataId);

			msg.append(", ");
			msg.append("fieldName=" + fieldName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormFileException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormFile[] findByFieldName_PrevAndNext(long formFileId,
		long formDataId, String fieldName, OrderByComparator obc)
		throws NoSuchFormFileException, SystemException {
		FormFile formFile = findByPrimaryKey(formFileId);

		int count = countByFieldName(formDataId, fieldName);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM net.portalteam.model.FormFile WHERE ");

			query.append("formDataId = ?");

			query.append(" AND ");

			if (fieldName == null) {
				query.append("fieldName IS NULL");
			}
			else {
				query.append("fieldName = ?");
			}

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

			qPos.add(formDataId);

			if (fieldName != null) {
				qPos.add(fieldName);
			}

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, formFile);

			FormFile[] array = new FormFileImpl[3];

			array[0] = (FormFile)objArray[0];
			array[1] = (FormFile)objArray[1];
			array[2] = (FormFile)objArray[2];

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	public List<FormFile> findByFormData(long formDataId)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(formDataId) };

		List<FormFile> list = (List<FormFile>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_FORMDATA,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormFile WHERE ");

				query.append("formDataId = ?");

				query.append(" ");

				query.append("ORDER BY ");

				query.append("createDate ASC");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formDataId);

				list = q.list();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormFile>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_FORMDATA,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public List<FormFile> findByFormData(long formDataId, int start, int end)
		throws SystemException {
		return findByFormData(formDataId, start, end, null);
	}

	public List<FormFile> findByFormData(long formDataId, int start, int end,
		OrderByComparator obc) throws SystemException {
		Object[] finderArgs = new Object[] {
				new Long(formDataId),
				
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormFile> list = (List<FormFile>)FinderCacheUtil.getResult(FINDER_PATH_FIND_BY_OBC_FORMDATA,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormFile WHERE ");

				query.append("formDataId = ?");

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

				qPos.add(formDataId);

				list = (List<FormFile>)QueryUtil.list(q, getDialect(), start,
						end);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormFile>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_BY_OBC_FORMDATA,
					finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public FormFile findByFormData_First(long formDataId, OrderByComparator obc)
		throws NoSuchFormFileException, SystemException {
		List<FormFile> list = findByFormData(formDataId, 0, 1, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormFile exists with the key {");

			msg.append("formDataId=" + formDataId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormFileException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormFile findByFormData_Last(long formDataId, OrderByComparator obc)
		throws NoSuchFormFileException, SystemException {
		int count = countByFormData(formDataId);

		List<FormFile> list = findByFormData(formDataId, count - 1, count, obc);

		if (list.isEmpty()) {
			StringBuilder msg = new StringBuilder();

			msg.append("No FormFile exists with the key {");

			msg.append("formDataId=" + formDataId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchFormFileException(msg.toString());
		}
		else {
			return list.get(0);
		}
	}

	public FormFile[] findByFormData_PrevAndNext(long formFileId,
		long formDataId, OrderByComparator obc)
		throws NoSuchFormFileException, SystemException {
		FormFile formFile = findByPrimaryKey(formFileId);

		int count = countByFormData(formDataId);

		Session session = null;

		try {
			session = openSession();

			StringBuilder query = new StringBuilder();

			query.append("FROM net.portalteam.model.FormFile WHERE ");

			query.append("formDataId = ?");

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

			qPos.add(formDataId);

			Object[] objArray = QueryUtil.getPrevAndNext(q, count, obc, formFile);

			FormFile[] array = new FormFileImpl[3];

			array[0] = (FormFile)objArray[0];
			array[1] = (FormFile)objArray[1];
			array[2] = (FormFile)objArray[2];

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

	public List<FormFile> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	public List<FormFile> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	public List<FormFile> findAll(int start, int end, OrderByComparator obc)
		throws SystemException {
		Object[] finderArgs = new Object[] {
				String.valueOf(start), String.valueOf(end), String.valueOf(obc)
			};

		List<FormFile> list = (List<FormFile>)FinderCacheUtil.getResult(FINDER_PATH_FIND_ALL,
				finderArgs, this);

		if (list == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("FROM net.portalteam.model.FormFile ");

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
					list = (List<FormFile>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);
				}
				else {
					list = (List<FormFile>)QueryUtil.list(q, getDialect(),
							start, end);
				}
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (list == null) {
					list = new ArrayList<FormFile>();
				}

				cacheResult(list);

				FinderCacheUtil.putResult(FINDER_PATH_FIND_ALL, finderArgs, list);

				closeSession(session);
			}
		}

		return list;
	}

	public void removeByFieldName(long formDataId, String fieldName)
		throws SystemException {
		for (FormFile formFile : findByFieldName(formDataId, fieldName)) {
			remove(formFile);
		}
	}

	public void removeByFormData(long formDataId) throws SystemException {
		for (FormFile formFile : findByFormData(formDataId)) {
			remove(formFile);
		}
	}

	public void removeAll() throws SystemException {
		for (FormFile formFile : findAll()) {
			remove(formFile);
		}
	}

	public int countByFieldName(long formDataId, String fieldName)
		throws SystemException {
		Object[] finderArgs = new Object[] { new Long(formDataId), fieldName };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FIELDNAME,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM net.portalteam.model.FormFile WHERE ");

				query.append("formDataId = ?");

				query.append(" AND ");

				if (fieldName == null) {
					query.append("fieldName IS NULL");
				}
				else {
					query.append("fieldName = ?");
				}

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formDataId);

				if (fieldName != null) {
					qPos.add(fieldName);
				}

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FIELDNAME,
					finderArgs, count);

				closeSession(session);
			}
		}

		return count.intValue();
	}

	public int countByFormData(long formDataId) throws SystemException {
		Object[] finderArgs = new Object[] { new Long(formDataId) };

		Long count = (Long)FinderCacheUtil.getResult(FINDER_PATH_COUNT_BY_FORMDATA,
				finderArgs, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				StringBuilder query = new StringBuilder();

				query.append("SELECT COUNT(*) ");
				query.append("FROM net.portalteam.model.FormFile WHERE ");

				query.append("formDataId = ?");

				query.append(" ");

				Query q = session.createQuery(query.toString());

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(formDataId);

				count = (Long)q.uniqueResult();
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				FinderCacheUtil.putResult(FINDER_PATH_COUNT_BY_FORMDATA,
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
						"SELECT COUNT(*) FROM net.portalteam.model.FormFile");

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
						"value.object.listener.net.portalteam.model.FormFile")));

		if (listenerClassNames.length > 0) {
			try {
				List<ModelListener<FormFile>> listenersList = new ArrayList<ModelListener<FormFile>>();

				for (String listenerClassName : listenerClassNames) {
					listenersList.add((ModelListener<FormFile>)Class.forName(
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
	private static Log _log = LogFactoryUtil.getLog(FormFilePersistenceImpl.class);
}