package com.project.service.game;

import com.project.domain.game.GameUser;

public interface GameUserService {
	public GameUser findByUserName(String userName,String password);

	public GameUser getById(Long id);
	
	public void saveOrUpdate(GameUser gameUser);
}
