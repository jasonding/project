/**
 * 
 */
package com.project.web.action.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.constant.WebConstant;
import com.project.domain.Privilege;
import com.project.domain.mapping.User;
import com.project.service.manage.UserService;
import com.project.web.action.BaseAction;

/**
 * @author dingjs
 */
@Controller("LoginAction")
@Scope("prototype")
public class LoginAction extends BaseAction {
	private static final long serialVersionUID = 5903206882067954785L;
	private UserService userService;
	
	private User user;
	private String randomCode;
	
	private List<Privilege> menuList;
	
	public String input() {
		return INPUT;
	}
	
	public String login() {
//		Map<String, Object> session = getSession();
//		String verifyCode = (String) session.get(WebConstant.WEB_RANDOM_CODE);
//		session.remove(WebConstant.WEB_RANDOM_CODE);
//		
//		if(verifyCode == null || !randomCode.equalsIgnoreCase(verifyCode)) {
//			System.out.println("验证码不正确");
//			addFieldError("randomcode", "验证码不正确");
//			return INPUT;
//		}
//		user = userService.findByUserName(user.getName(),user.getPassword());
//		session.put(WebConstant.WEB_USER, user);
		return LOGIN;
	}
	
	public String logout() {
		Map<String, Object> session = getSession();
		session.remove(WebConstant.WEB_USER);
		return INPUT;
	}
	
	public String main() {
//		Map<String, Object> session = getSession();
//		User user = (User) session.get(WebConstant.WEB_USER);
//		Set<Role> roleSet = user.getRoleSet();
//		menuList = PrivilegeTreeUtil.getPrivilegeTreeMenuList(roleSet);
		return SUCCESS;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Privilege> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Privilege> menuList) {
		this.menuList = menuList;
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRandomCode(String randomCode) {
		this.randomCode = randomCode;
	}
}
