/**
 * 
 */
package com.project.dao.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 *
 */
public interface DaoBase<T> {
	/**
	 * 保存或更新实体，在不确定是保存还是更新情况下使用
	 * @param entity
	 * @return
	 */
	public void merge(T entity);
	/**
	 * 保存实体
	 * @param entity
	 * @return
	 */
	public void save(T entity);
	/**
	 * 删除实体
	 * @param entity
	 * @return
	 */
	public void delete(T entity);
	/**
	 * 更新实体
	 * @param entiry
	 * @return
	 */
	public void update(T entity);
	/**
	 * 根据主键id查找实体
	 * @param Id
	 * @return
	 */
	public T getById(Serializable Id);
	
	/**
	 * 根据主键id集合查找实体集合
	 * @param ids
	 * @return
	 */
	public List<T> findByIds(Collection<Serializable> ids);
	
	/**
	 * 得到数据库中总记录数
	 * @return
	 */
	public Integer getCount(Map<String,String> params);
	/**
	 * 得到数据库中指定条件和排序的的记录数
	 * @param params
	 * @return
	 */
	public List<T> getPageList(Map<String,String> params,LinkedHashMap<String,String> orderMap,Integer firstRow,Integer maxRow);
	
	/**
	 * 得到数据库中指定条件的记录数
	 * @param params
	 * @return
	 */
	public List<T> getPageList(Map<String, String> params,Integer firstRow,Integer maxRow);
	
	/**
	 * 查找所有实体
	 * @return
	 */
	public List<T> findAll();
}
