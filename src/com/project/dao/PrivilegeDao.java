/**
 * 
 */
package com.project.dao;

import java.util.List;

import com.project.dao.base.DaoBase;
import com.project.domain.Privilege;

/**
 * @author dingjs
 *
 */
public interface PrivilegeDao extends DaoBase<Privilege> {

	List<Privilege> findTopPrivilegeList();

}
