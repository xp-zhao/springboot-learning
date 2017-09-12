package com.xp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ch113 on 2017/9/12.
 */
@RestController
public class HelloWorldController
{
	@RequestMapping("/hello")
	public String hello()
	{
		return "hello world!";
	}
}
