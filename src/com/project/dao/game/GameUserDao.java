package com.project.dao.game;

import com.project.dao.base.DaoBase;
import com.project.domain.game.GameUser;

public interface GameUserDao extends DaoBase<GameUser>{
	public GameUser findByUserName(String userName);
}
