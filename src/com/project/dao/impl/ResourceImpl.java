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
import com.project.dao.ResourceDao;
import com.project.dao.base.DaoBaseImpl;
import com.project.domain.PrivilegePK;
import com.project.domain.mapping.Resource;

/**
 * @author dingjs
 *
 */
@Repository(value="resourceDao")
public class ResourceImpl extends DaoBaseImpl<Resource> implements ResourceDao {

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
}
