/**
 * 
 */
package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.RoleDao;
import com.project.domain.mapping.Role;
import com.project.service.RoleService;

/**
 * @author dingjs
 */
@Service("roleService")
public class RoleServiceImpl implements RoleService {
	private RoleDao roleDao;
	
	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public List<Role> findAll() {
		return roleDao.findAll();
	}

	@Override
	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	@Override
	public void save(Role role) {
		roleDao.save(role);
	}

	@Override
	public void update(Role role) {
		roleDao.update(role);
	}

	@Override
	public void delete(Role role) {
		roleDao.delete(role);
	}
}
