/**
 * 
 */
package com.project.web.action.user;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.domain.PageView;
import com.project.domain.mapping.Role;
import com.project.domain.mapping.User;
import com.project.enumeration.Gender;
import com.project.service.RoleService;
import com.project.service.UserService;
import com.project.util.web.RoleUtil;
import com.project.web.action.BaseAction;

/**
 * @author dingjs
 *
 */
@Controller(value="UserAction")
@Scope("prototype")
public class UserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private UserService userService;
	private RoleService roleService;
	
	private List<User> userList;
	private List<Role> roleList;
	
	
	//数据列表需要的参数数据
	private Map<String,String> params;
	private Integer pageSize;
	private Integer currentPage;
	private PageView<User> pageView;

	//添加user所需参数
	private User user;
	private Collection<Long> roleIds;
	
	public String input() {
		if(user != null && user.getId() != null) {
			user = userService.getById(user.getId());
			roleIds = RoleUtil.getRoleIds(user.getRoleSet());
		}
		roleList = roleService.findAll();
		return INPUT;
	}
	public String list() {
		pageView = userService.getPageViewList(params, pageSize, currentPage);
		return LIST;
	}
	
	public String manage() {
		user.setRoleSet(new HashSet<Role>(RoleUtil.getRoles(roleIds)));
		userService.saveOrUpdate(user);
		return SUCCESS;
	}
	
	public void setRoleIds(Collection<Long> roleIds) {
		this.roleIds = roleIds;
	}
	public Collection<Long> getRoleIds() {
		return roleIds;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public List<Role> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Map<String, String> getParams() {
		return params;
	}
	public void setParams(Map<String, String> params) {
		this.params = params;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public PageView<User> getPageView() {
		return pageView;
	}
	public void setPageView(PageView<User> pageView) {
		this.pageView = pageView;
	}
	
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public List<Gender> getGender() {
		return Arrays.asList(Gender.values());
	}
}