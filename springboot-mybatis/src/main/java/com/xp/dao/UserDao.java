package com.xp.dao;

import com.xp.domain.PlayList;
import com.xp.domain.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 DAO 接口类
 * Created by xp-zhao on 2017/9/13.
 */
@Repository
public interface UserDao
{
	/**
	 * 根据用户姓名，查找用户信息
	 * @param userName 用户名称
	 * @return User 用户实体对象
	 */
	User findByName(@Param("userName") String userName);

	/**
	 * 批量插入数据
	 * @param playLists
	 * @return
	 */
	int addData(List<PlayList> playLists);
}
