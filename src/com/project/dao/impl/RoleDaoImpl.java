/**
 * 
 */
package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.dao.RoleDao;
import com.project.dao.base.DaoBaseImpl;
import com.project.domain.mapping.Role;

/**
 * @author dingjs
 *
 */
@Repository("roleDao")
public class RoleDaoImpl extends DaoBaseImpl<Role> implements RoleDao {

	@Override
	protected List<Object> buildQueryCondition(Map<String, String> params,
			StringBuilder hql) {
		List<Object> list = new ArrayList<Object>();
		if(params == null || params.isEmpty()) {
			return list;
		}
		return list;
	}

}
