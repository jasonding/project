package com.project.service.game.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.game.GameUserDao;
import com.project.domain.game.GameUser;
import com.project.service.game.GameUserService;

@Service("gameUserService")
public class GameUserServiceImpl implements GameUserService {
	
	private GameUserDao gameUserDao;
	@Autowired(required=true)
	public void setGameUserDao(GameUserDao gameUserDao) {
		this.gameUserDao = gameUserDao;
	}
	
	@Override
	public GameUser findByUserName(String userName, String password) {
		GameUser gameUser = gameUserDao.findByUserName(userName);
		if(gameUser!= null) {
			if(password.equals(gameUser.getPassword())) {
				return gameUser;
			}
		}
		return null;
	}

	@Override
	public GameUser getById(Long id) {
		return gameUserDao.getById(id);
	}

	@Override
	public void saveOrUpdate(GameUser gameUser) {
		gameUserDao.merge(gameUser);
	}
}
