package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.xp.springbootrabbitmq.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Component
public class Queue1Receiver extends BaseConsumer
{
	public Queue1Receiver()
	{
		super("queue_test1");
	}

	@Override
	public void consumerHandle(String consumerTag , Envelope envelope ,
		AMQP.BasicProperties properties , byte[] body,Channel channel,String queueName) throws Exception
	{
		String exchange = envelope.getExchange();
		String str = new String(body,"UTF-8");
		System.out.println(exchange+":"+queueName+"接收消息："+ DateUtil.formatStandardDateTime(new Date())+": content:"+ str);
		try
		{
			TimeUnit.SECONDS.sleep(5);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println(queueName+": 执行结束,"+DateUtil.formatStandardDateTime(new Date()));
		channel.basicAck(envelope.getDeliveryTag(),false);
	}
}
