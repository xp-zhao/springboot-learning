package com.xp.springbootrabbitmq.recev;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Component
@RabbitListener(queues = "hello")
public class Receiver
{
	@RabbitHandler
	public void process(String hello)
	{
		System.out.println("Recviver : "+hello);
	}
}
