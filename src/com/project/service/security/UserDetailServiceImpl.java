/**
 * 
 */
package com.project.service.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.domain.mapping.Role;
import com.project.domain.security.Authority;
import com.project.service.manage.UserService;


/**
 * @author dingjs
 *
 */
@Service(value="userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		com.project.domain.mapping.User user = userService.findByUserName(username);
		
		if(user == null) throw new UsernameNotFoundException( username + " is not register");
		
		//用户访问资源的权限列表
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role : user.getRoleSet()) {
			authorities.add(new Authority(role.getAuthority(),role.getName()));
		}
		
		return new User(username, user.getPassword(), true, true, true, true, authorities);
	}
}
