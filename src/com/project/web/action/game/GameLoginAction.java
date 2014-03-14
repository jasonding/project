package com.project.web.action.game;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.constant.WebConstant;
import com.project.domain.game.GameUser;
import com.project.service.game.GameUserService;
import com.project.util.NullUtil;
import com.project.util.RamCacheUtil;
import com.project.web.action.BaseAction;

@Controller("GameLoginAction")
@Scope("prototype")
public class GameLoginAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private GameUserService gameUserService;
	
	
	private String username;
	private String password;
	
	@Autowired(required=true)
	public void setGameUserService(GameUserService gameUserService) {
		this.gameUserService = gameUserService;
	}

	public String input() {
		return INPUT;
	}
	
	public String loginUI() {
		return SUCCESS;
	}
	

	public String gameLogin() {
		if(NullUtil.isNull(username) || NullUtil.isNull(password)){
			return INPUT;
		}
		GameUser gameUser = null;
		gameUser = (GameUser) super.getSession().get(WebConstant.WEB_GAME_USER);
		if(gameUser == null) {
			gameUser = gameUserService.findByUserName(username, password);
			if(gameUser == null) return INPUT;
			
			super.getSession().put(WebConstant.WEB_GAME_USER, gameUser);
			RamCacheUtil.put(gameUser.getName(), ServletActionContext.getRequest().getSession().getId());
		}
		return SUCCESS;
	}
	
	public String main() {
		return SUCCESS;
	}
	public String mainTest() {
		GameUser gameUser = (GameUser) super.getSession().get(WebConstant.WEB_GAME_USER);
		if(gameUser == null){
			return INPUT;
		}
		
		return SUCCESS;
	}
	
	public String logout() {
		Map<String, Object> session = getSession();
		if(session != null) {
			GameUser gameUser =  (GameUser) session.get(WebConstant.WEB_GAME_USER);
			if(gameUser != null ){
				session.remove(WebConstant.WEB_GAME_USER);
				RamCacheUtil.remove(gameUser.getName());
			}
		}
		
		return LOGIN;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
