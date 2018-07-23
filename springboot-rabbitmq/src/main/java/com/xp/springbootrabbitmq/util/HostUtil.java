package com.xp.springbootrabbitmq.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.InetAddress;

/**
 * Created by xp-zhao on 2018/6/25.
 */
public class HostUtil
{
	private final static Logger logger = LogManager.getLogger("myLog");

	//获取本机ip
	public static String localIp(){
		InetAddress ia=null;
		try {
			ia=ia.getLocalHost();
			String localip=ia.getHostAddress();
			return localip;
		} catch (Exception e) {
			logger.error("获取本机IP失败"+e);
		}
		return null;
	}
}
