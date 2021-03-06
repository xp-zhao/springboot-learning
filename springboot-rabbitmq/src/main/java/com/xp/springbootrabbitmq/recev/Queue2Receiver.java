package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.xp.springbootrabbitmq.util.DateUtil;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xp-zhao on 2018/7/20.
 */
@Component
public class Queue2Receiver extends BaseConsumer
{
	public Queue2Receiver()
	{
		super("queue_test2");
	}

	@Override
	public void consumerHandle(String consumerTag , Envelope envelope ,
		AMQP.BasicProperties properties , byte[] body,Channel channel,String queueName) throws Exception
	{
		String str = new String(body,"UTF-8");
		String exchange = envelope.getExchange();
		System.out.println(exchange+":"+queueName+"接收消息："+ DateUtil.formatStandardDateTime(new Date())+": content:"+ str);
		try
		{
			TimeUnit.SECONDS.sleep(2);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(queueName+": 执行结束,"+DateUtil.formatStandardDateTime(new Date()));
		channel.basicAck(envelope.getDeliveryTag(),false);
	}
}
