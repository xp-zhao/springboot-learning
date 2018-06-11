package com.xp.springbootrabbitmq.send;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Component("sender")
public class Sender
{
	private static final Logger logger = LogManager.getLogger("sendLog");
	@Autowired
	private AmqpTemplate template;

	public void send()
	{
		String context = "hello " +new Date();
		this.template.convertAndSend("local.test","test",context);
		logger.info("send: "+context);
	}
}
