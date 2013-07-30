/**
 * 
 */
package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.dao.ResourceDao;
import com.project.dao.base.DaoBaseImpl;
import com.project.domain.mapping.Resource;
import com.project.enumeration.ResourceType;

/**
 * @author dingjs
 *
 */
@Repository(value="resourceDao")
@SuppressWarnings("unchecked")
public class ResourceImpl extends DaoBaseImpl<Resource> implements ResourceDao {

	@Override
	protected List<Object> buildQueryCondition(Map<String, String> params,
			StringBuilder hql) {
		List<Object> list = new ArrayList<Object>();
		return list;
	}

	@Override
	public List<Resource> getResourceOlnyMenu() {
		return getSession().createQuery(" FROM Resource WHERE resourceType=? ")//
				.setParameter(0, ResourceType.MENU)//
				.list();
	}

	@Override
	public List<Resource> getResourceWithNoMenu() {
		return getSession().createQuery(" FROM Resource WHERE resourceType IN (?, ?, ?) ")//
		.setParameter(0, ResourceType.DEFAULT)//
		.setParameter(1, ResourceType.LINK)//
		.setParameter(2, ResourceType.BUTTON)//
		.list();
	}
	
	
}
