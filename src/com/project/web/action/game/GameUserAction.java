package com.project.web.action.game;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.project.domain.game.GameUser;
import com.project.service.game.GameUserService;
import com.project.web.action.BaseAction;

@Controller("GameUserAction")
@Scope("prototype")
public class GameUserAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private GameUserService gameUserService;
	
	@Autowired(required=true)
	public void setGameUserService(GameUserService gameUserService) {
		this.gameUserService = gameUserService;
	}
	
	private GameUser gameUser;
	
	public String input() {
		if(gameUser != null && gameUser.getId() != null) {
			gameUser = gameUserService.getById(gameUser.getId());
		}
		return INPUT;
	}
	
	public String manage() {
		
		if(gameUser == null) return INPUT;
		if(gameUser.getId() != null) {
			GameUser oldUser = gameUserService.getById(gameUser.getId());
			gameUser.setPassword(oldUser.getPassword());
		}else {
			gameUser.setCreateTime(new Date());
		}
		gameUserService.saveOrUpdate(gameUser);
		
		return SUCCESS;
	}

	public GameUser getGameUser() {
		return gameUser;
	}

	public void setGameUser(GameUser gameUser) {
		this.gameUser = gameUser;
	}
	
}
