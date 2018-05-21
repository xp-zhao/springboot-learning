package com.xp.controller;

import com.xp.domain.User;
import com.xp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xp-zhao on 2017/9/13.
 */
@RestController
public class UserRestController
{
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/user",method = RequestMethod.GET)
	public User findOneUser(@RequestParam(value = "userName",required = true,defaultValue = "xp") String userName)
	{
		return userService.findByName(userName);
	}
}
