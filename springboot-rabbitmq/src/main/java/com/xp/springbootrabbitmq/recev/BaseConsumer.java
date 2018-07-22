package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;

/**
 * Created by xp-zhao on 2018/7/20.
 */
public abstract class BaseConsumer
{
	public BaseConsumer(String queueName)
	{
		RabbitFactory.registQueue(queueName,this);
	}

	/**
	 * mq消费者真实执行逻辑方法
	 *
	 * @param message
	 * @param channel
	 */
	public abstract void consumerHandle(Message message , Channel channel) throws Exception;

}
