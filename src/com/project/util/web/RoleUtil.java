package com.project.util.web;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import com.project.domain.mapping.Role;
import com.project.util.NullUtil;

public class RoleUtil {
	/**
	 * 得到roleId集合
	 * @param roles
	 * @return
	 */
	public static Collection<Role> getRoles(Collection<Long> roleIds) {
		if(NullUtil.isNullOrEmpty(roleIds)) return Collections.emptySet();
		Collection<Role> roleSet = new HashSet<Role>();
		for (Long roleId : roleIds) {
			roleSet.add(new Role(roleId));
		}
		return roleSet;
	}
	/**
	 * 得到roleId集合
	 * @param roles
	 * @return
	 */
	public static Collection<Long> getRoleIds(Collection<Role> roles) {
		if(NullUtil.isNullOrEmpty(roles)) return Collections.emptySet();
		Collection<Long> ids = new HashSet<Long>();
		for (Role role : roles) {
			ids.add(role.getId());
		}
		return ids;
	}
}
