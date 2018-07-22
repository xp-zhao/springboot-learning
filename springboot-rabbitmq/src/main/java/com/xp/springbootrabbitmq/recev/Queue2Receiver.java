package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * Created by xp-zhao on 2018/7/20.
 */
@Component
public class Queue2Receiver extends BaseConsumer
{
	public Queue2Receiver()
	{
		super("queue2");
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
