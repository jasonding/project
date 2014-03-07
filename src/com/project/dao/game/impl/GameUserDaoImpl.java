package com.project.dao.game.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.dao.base.DaoBaseImpl;
import com.project.dao.game.GameUserDao;
import com.project.domain.game.GameUser;


@Repository(value="gameUserDao")
public class GameUserDaoImpl extends DaoBaseImpl<GameUser> implements GameUserDao {

	@Override
	protected List<Object> buildQueryCondition(Map<String, String> params,
			StringBuilder hql) {
		return null;
	}

	@Override
	public GameUser findByUserName(String userName) {
		 return (GameUser) getSession().createQuery("FROM GameUser WHERE name=:name")//
		.setParameter("name", userName)//
		.uniqueResult();
	}

}
