package com.xp.springbootrabbitmq.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Component
public class Sender
{
	@Autowired
	private AmqpTemplate template;

	public void send()
	{
		String context = "hello " +new Date();
		System.out.println("send: "+context);
		this.template.convertAndSend("hello",context);
	}
}
