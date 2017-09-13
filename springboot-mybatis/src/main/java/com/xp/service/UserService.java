package com.xp.service;

import com.xp.domain.User;
import org.apache.ibatis.annotations.Param;

/**
 * 用户业务逻辑接口类
 * Created by xp-zhao on 2017/9/13.
 */
public interface UserService
{
	/**
	 * 根据用户姓名，查找用户信息
	 * @param userName 用户名称
	 * @return User 用户实体对象
	 */
	User findByName(@Param ("userName") String userName);
}
