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

	public List<Role> findAll() {
		return roleDao.findAll();
	}

	public Role getById(Long id) {
		return roleDao.getById(id);
	}

	public void save(Role role) {
		roleDao.save(role);
	}

	public void update(Role role) {
		roleDao.update(role);
	}

	public void delete(Role role) {
		roleDao.delete(role);
	}
}
