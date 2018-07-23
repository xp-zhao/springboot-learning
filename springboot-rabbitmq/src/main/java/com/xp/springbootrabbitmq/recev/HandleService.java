package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Created by xp-zhao on 2018/7/18.
 */
public class HandleService implements ChannelAwareMessageListener
{
	private BaseConsumer baseConsumer;
	public HandleService(){}

	public HandleService(BaseConsumer baseConsumer)
	{
		this.baseConsumer = baseConsumer;
	}

	@Override
	public void onMessage(Message message , Channel channel) throws Exception
	{
//		String str = new String(message.getBody(),"UTF-8");
//		String queueName = message.getMessageProperties().getConsumerQueue();
//		baseConsumer = RabbitFactory.queryHandleService(queueName);
//		System.out.println(queueName+"接收消息："+str);
//		baseConsumer.consumerHandle(message,channel);
	}
}
