/**
 * 
 */
package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.constant.Global;
import com.project.constant.WebConstant;
import com.project.dao.PrivilegeDao;
import com.project.dao.base.DaoBaseImpl;
import com.project.domain.Privilege;
import com.project.domain.PrivilegePK;

/**
 * @author dingjs
 *
 */
@Repository(value="privilegeDao")
@SuppressWarnings("unchecked")
public class PrivilegeDaoImpl extends DaoBaseImpl<Privilege> implements PrivilegeDao {

	@Override
	protected List<Object> buildQueryCondition(Map<String, String> params,
			StringBuilder hql) {
		List<Object> list = new ArrayList<Object>();
		if(params != null) {
			if(params.get("parentPrivilege") != null) {
				String privilegeStr = params.get("parentPrivilege");
				PrivilegePK privilegePK = null;
				String strs[] = privilegeStr.split(WebConstant.WEB_LINK_CHAR);
				if(strs.length == 1) privilegePK = new PrivilegePK(strs[0],Global.STRING_EMPTY);
				else  privilegePK = new PrivilegePK(strs[0],strs[1]);
				hql.append(" AND parentPrivilege.privilegePK.module = ? AND parentPrivilege.privilegePK.privilege=?");
				list.add(privilegePK.getModule());
				list.add(privilegePK.getPrivilege());
			}
		}else {
			hql.append(" AND parentPrivilege IS NULL ");
		}
		return list;
	}

	public List<Privilege> findTopPrivilegeList() {
		return getSession().createQuery("FROM Privilege WHERE parentPrivilege IS NULL").list();
	}

}
