/**
 * 
 */
package com.project.service.manage.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.PrivilegeDao;
import com.project.domain.PageView;
import com.project.service.manage.PrivilegeService;

/**
 * @author dingjs
 */
@Service("privilegeService")
public class PrivilegeServiceImpl implements PrivilegeService {
	private PrivilegeDao privilegeDao;
	
	@Autowired
	public void setPrivilegeDao(PrivilegeDao privilegeDao) {
		this.privilegeDao = privilegeDao;
	}

	@Override
	public List<com.project.domain.mapping.Privilege> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageView<com.project.domain.mapping.Privilege> getPageViewList(
			Map<String, String> params, Integer pageSize, Integer currentPage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(com.project.domain.mapping.Privilege privilege) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(com.project.domain.mapping.Privilege privilege) {
		// TODO Auto-generated method stub
		
	}
	
}
