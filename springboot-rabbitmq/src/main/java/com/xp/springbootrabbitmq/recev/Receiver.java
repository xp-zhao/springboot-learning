package com.xp.springbootrabbitmq.recev;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver
{
	private static final Logger logger = LogManager.getLogger("recvLog");
	@RabbitHandler
	public void process(String hello)
	{
		logger.info("Recviver : "+hello);
//		try
//		{
////			TimeUnit.SECONDS.sleep(3);
//		}
//		catch (InterruptedException e)
//		{
//			e.printStackTrace();
//		}
	}
}
