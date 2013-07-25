package com.project.dao.base;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.project.exception.DaoException;

/**
 * @author dingjs
 * 
 */
@SuppressWarnings("unchecked")
public abstract class DaoBaseImpl<T> implements DaoBase<T> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	private Class<T> clazz = null;
	
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public DaoBaseImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clazz = (Class<T>) type.getActualTypeArguments()[0];
	}

	
	public void delete(T entity) {
		try {
			this.getSession().delete(entity);
		} catch (Exception e) {
			logger.error("删除实体失败,[message={}]", e.getMessage(), e);
			throw new DaoException("删除实体失败");
		}
	}

	public T getById(Serializable id) {
		Object object = this.getSession().get(clazz, id);
		return (T) object;
	}

	public List<T> findByIds(final Collection<Serializable> ids) {
		StringBuilder hql = new StringBuilder("FROM");
		hql.append(clazz.getSimpleName()).append("WHERE 1=1");
		List<Object> list = new ArrayList<Object>();
		if (ids.size() == 1) {
			hql.append(" AND id = ? ");
			list.add(ids.iterator().next());
		} else {
			hql.append(" AND id IN ( ");
			for (Serializable id : ids) {
				hql.append(" ? ,");
				list.add(id);
			}
			hql = hql.deleteCharAt(hql.lastIndexOf(","));
			hql.append(")");
		}
		Query query = this.getSession().createQuery(hql.toString());
		for (int i = 0; i < ids.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		return query.list();
	}

	public List<T> findAll() {
		return this.getSession().createQuery("FROM " + clazz.getSimpleName()).list();
	}

	public void save(T entity) {
		try {
			this.getSession().save(entity);
		} catch (Exception e) {
			logger.error("保存实体失败,[message={}]", e.getMessage(), e);
			throw new DaoException("保存实体失败");
		}
	}

	public void update(T entity) {
		try {
			this.getSession().update(entity);
		} catch (Exception e) {
			logger.error("更新实体失败,[message={}]", e.getMessage(), e);
			throw new DaoException("更新实体失败");
		}
	}
	
	

	public Integer getCount(Map<String, String> params) {
		StringBuilder hql = new StringBuilder(" SELECT COUNT(*) FROM ");
		hql.append(clazz.getSimpleName()).append(" WHERE 1=1 ");
		List<Object> list = buildQueryCondition(params,hql);
		Query query = this.getSession().createQuery(hql.toString());
		for(int i=0; i<list.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		return ((Number) query.uniqueResult()).intValue();
	}

	public List<T> getPageList(Map<String, String> params, Integer firstRow,Integer maxRow) {
		return getPageList(params, null,firstRow, maxRow);
	}

	public List<T> getPageList(Map<String, String> params, LinkedHashMap<String, String> orderMap, Integer firstRow, Integer maxRow) {
		StringBuilder hql = new StringBuilder(" FROM ");
		hql.append(clazz.getSimpleName()).append(" WHERE 1=1 ");
		//构建查询条件
		List<Object> list = buildQueryCondition(params,hql);
		Query query = this.getSession().createQuery(hql.toString());
		for(int i=0; i<list.size(); i++) {
			query.setParameter(i, list.get(i));
		}
		//构建排序结果
		hql.append(buildOrderCondition(orderMap));
		return query.setFirstResult(firstRow).setMaxResults(maxRow).list();
	}

	public void merge(T entity) {
		try {
			this.getSession().merge(entity);
		} catch (Exception e) {
			logger.error("更新实体出错,[message={}]", e.getMessage(),e);
			throw new DaoException("merge实体失败");
		}
	}

	protected static String buildOrderCondition(LinkedHashMap<String, String> orderMap) {
		if (orderMap == null || orderMap.isEmpty())
			return "";
		StringBuilder orderStr = new StringBuilder(" ORDER BY ");
		for (Map.Entry<String, String> entry : orderMap.entrySet()) {
			orderStr.append(entry.getKey()).append(" ")
					.append(entry.getValue()).append(",");
		}
		orderStr.deleteCharAt(orderStr.length() - 1);
		return orderStr.toString();
	}

	/**
	 * 得到当前线程session
	 * @return
	 */
	protected final Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 构建条件查询语句(由各个子类去实现)
	 * 
	 * @param params
	 * @return
	 */
	protected abstract List<Object> buildQueryCondition(Map<String,String> params,StringBuilder hql);

	public void clearStagingTables(String sql, Serializable ... serializableIds) {
		SQLQuery createSQLQuery = this.getSession().createSQLQuery(sql);
		for (int i=0; i<serializableIds.length; i++) {
			createSQLQuery.setParameter(i, serializableIds[i]);
		}
		createSQLQuery.executeUpdate();
	}
	
	
}
