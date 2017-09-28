package com.xp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by xp-zhao on 2017/9/15.
 */
@Controller
public class WebController
{
	@RequestMapping ("/")
	public String home() {
		return "index";
	}

	@RequestMapping(value="/web")
	public String testWebGL(){
		return "webgl";
	}
}
