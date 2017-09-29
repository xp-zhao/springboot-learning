package com.xp.controller;

import com.xp.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xp-zhao on 2017/9/28.
 */
@Controller
@RequestMapping("/learn")
public class TestController
{
	@RequestMapping("/")
	public ModelAndView index()
	{
		List<User> users = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			User user = new User();
			user.setName("test"+i);
			user.setSex("男");
			user.setAge(20+i);
			users.add(user);
		}
		ModelAndView modelAndView = new ModelAndView("/template");
		modelAndView.addObject("users", users);
		return modelAndView;
	}
}
