package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.Channel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Component
public class Receiver extends BaseConsumer
{
	private static final Logger logger = LogManager.getLogger("recvLog");

	public Receiver()
	{
		super("queue1");
	}

	@Override
	public void consumerHandle(Message message , Channel channel) throws Exception
	{
		String queueName = message.getMessageProperties().getConsumerQueue();
		String str = new String(message.getBody(),"UTF-8");
		System.out.println(queueName+"接收消息："+ str);
		try
		{
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(queueName+": 执行结束");
		channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
	}
}
