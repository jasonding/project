package com.project.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.project.dao.UserDao;
import com.project.dao.base.DaoBaseImpl;
import com.project.domain.mapping.User;

@Repository(value="userDao")
public class UserDaoImpl extends DaoBaseImpl<User> implements UserDao  {
	@Override
	protected List<Object> buildQueryCondition(Map<String, String> params,
			StringBuilder hql) {
		List<Object> list = new ArrayList<Object>();
		if(params != null && !params.isEmpty()) {
			String userName = params.get("userName");
			if(userName != null && !userName.trim().equals("")) {
				hql.append(" and name = ? ");
				list.add(userName);
			}
			return list;
		}
		return list;
	}

	@Override
	public User findByUserName(String userName) {
		return (User) getSession().createQuery("FROM User WHERE name=:name")//
		.setParameter("name", userName)//
		.uniqueResult();
	}

	
}
