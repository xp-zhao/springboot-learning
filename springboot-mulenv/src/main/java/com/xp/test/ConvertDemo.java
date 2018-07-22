package com.xp.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xp.model.BaseResp;

/**
 * Created by xp-zhao on 2018/5/23.
 */
public class ConvertDemo
{
	public static void main(String[] args)
	{
		BaseResp resp = new BaseResp();
		resp.setCode("199999");
		resp.setInfo("系统异常");
		Object proceed = resp;
		String result;
		if(proceed instanceof JSONObject)
		{
			result = proceed.toString();
		}
		else if(proceed instanceof String)
		{
			result = String.valueOf(proceed);
		}
		else
		{
			result = JSON.toJSONString(proceed);
		}
		System.out.println(result);
	}
}
