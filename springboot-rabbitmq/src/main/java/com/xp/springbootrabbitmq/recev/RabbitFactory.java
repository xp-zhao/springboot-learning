package com.xp.springbootrabbitmq.recev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xp-zhao on 2018/7/18.
 */
public class RabbitFactory
{
	private static final Logger                    logger    = LogManager.getLogger("myLog");
	private static       Map<String, BaseConsumer> queueCache = new HashMap<>();

	public static void registQueue(String queueName,BaseConsumer baseConsumer)
	{
		if( null != queueCache.get(queueName))
		{
			System.out.println("此队列对应监听已存在，请更换！");
			logger.error("此队列对应监听已存在，请更换！");
			System.exit(0);
		}
		queueCache.put(queueName, baseConsumer);
	}

	public static BaseConsumer queryConsumer(String queueName)
	{
		BaseConsumer baseConsumer = queueCache.get(queueName);
		return baseConsumer;
	}
}
