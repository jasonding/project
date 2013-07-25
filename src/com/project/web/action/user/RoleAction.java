package com.project.web.action.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.domain.Privilege;
import com.project.domain.mapping.Role;
import com.project.service.RoleService;
import com.project.web.action.BaseAction;

/**
 * @author dingjs
 */
@Controller("RoleAction")
@Scope("prototype")
public class RoleAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	
	private RoleService roleService;
	
	private List<Privilege> privilegeList;
	private List<Role> roleList;
	private Role role;
	
	public String list() {
		roleList = roleService.findAll();
		return LIST;
	}
	
	public String manage() {
		if(role.getId() == null) {
			roleService.save(role);
		}else {
			roleService.update(role);
		}
		return SUCCESS;
	}
	
	public String delete() {
		role = roleService.getById(role.getId());
		roleService.delete(role);
		return SUCCESS;
	}
	
	public String input() {
		if(role == null) {
			
		}else if (role.getId() != null) {
			role = roleService.getById(role.getId());
		}
		//privilegeList = privilegeService.findAll();
		//privilegeList = PrivilegeTreeUtil.getPrivilegeTreeList(privilegeService.findTopPrivilegeList());;
		return INPUT;
	}
	
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<Privilege> getPrivilegeList() {
		return privilegeList;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getRoleList() {
		return roleList;
	}
}
