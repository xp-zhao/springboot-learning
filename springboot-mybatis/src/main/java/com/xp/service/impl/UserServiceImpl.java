package com.xp.service.impl;

import com.xp.dao.UserDao;
import com.xp.domain.User;
import com.xp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用户业务逻辑实现类
 * Created by xp-zhao on 2017/9/13.
 */
@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserDao userDao;

	@Override
	public User findByName(String userName)
	{
		return userDao.findByName(userName);
	}
}
