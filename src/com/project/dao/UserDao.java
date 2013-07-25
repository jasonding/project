package com.project.dao;

import com.project.dao.base.DaoBase;
import com.project.domain.mapping.User;

public interface UserDao extends DaoBase<User> {
	public User findByUserName(String userName);

}
