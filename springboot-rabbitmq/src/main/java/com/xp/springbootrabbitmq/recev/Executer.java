package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

/**
 * Created by xp-zhao on 2018/7/20.
 */
public class Executer extends DefaultConsumer
{
	private BaseConsumer baseConsumer;
	private Channel      channel;
	private String queueName;

	public Executer(Channel channel,BaseConsumer baseConsumer,String queueName)
	{
		super(channel);
		this.channel = channel;
		this.baseConsumer = baseConsumer;
		this.queueName = queueName;
	}

	@Override
	public void handleDelivery(String consumerTag , Envelope envelope ,
		AMQP.BasicProperties properties , byte[] body)
	{
		try
		{
			//简单执行，收到消息后直接调用真实执行者
			baseConsumer.consumerHandle(consumerTag , envelope ,
				 properties , body,channel,queueName);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
