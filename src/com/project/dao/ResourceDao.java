/**
 * 
 */
package com.project.dao;

import java.util.List;

import com.project.dao.base.DaoBase;
import com.project.domain.mapping.Resource;

/**
 * @author dingjs
 *
 */
public interface ResourceDao extends DaoBase<Resource> {
	public List<Resource> getResourceOlnyMenu();
}
