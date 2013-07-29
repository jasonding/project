/**
 * 
 */
package com.project.service.manage;

import java.util.List;

import com.project.domain.mapping.Role;

/**
 * @author dingjs
 *
 */
public interface RoleService {
	public void save(Role role);
	public void update(Role role);
	public List<Role> findAll();
	public Role getById(Long id);
	public void delete(Role role);
}
