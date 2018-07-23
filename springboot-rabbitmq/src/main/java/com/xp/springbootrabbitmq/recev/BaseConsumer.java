package com.xp.springbootrabbitmq.recev;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import com.xp.springbootrabbitmq.config.RabbitMQConfig;
import com.xp.springbootrabbitmq.util.HostUtil;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by xp-zhao on 2018/7/20.
 */
public abstract class BaseConsumer
{
	@Autowired
	private        ConnectionFactory factory;
	private static Connection        connection;
	@Autowired
	private        RabbitMQConfig    config;
	private String queueName;

	public BaseConsumer(String queueName)
	{
		RabbitFactory.registQueue(queueName,this);
		this.queueName = queueName;
	}

	/**
	 * mq消费者真实执行逻辑方法
	 *
	 */
	public abstract void consumerHandle(String consumerTag , Envelope envelope ,
		AMQP.BasicProperties properties , byte[] body,Channel channel,String queueName) throws Exception;
//	public abstract void consumerHandle(Message message , Channel channel) throws Exception;

//	@PostConstruct
//	public void init()
//	{
//		String ipaddr = HostUtil.localIp();
//		System.out.println(ipaddr);
//		List<RabbitMQConfig.Exchange> exchanges = config.getExchange();
//		connection = factory.createConnection();
//		for(RabbitMQConfig.Exchange exchange : exchanges)
//		{
//			try
//			{
//				List<RabbitMQConfig.QueueList> queueLists = exchange.getQueues();
//				for(RabbitMQConfig.QueueList queue : queueLists)
//				{
//					for(int i = 0; i < queue.getConcurrentConsumers(); i++)
//					{
//						if(queue.getQueueName().equals(queueName) && (CollectionUtils.isEmpty(queue.getExecuteHost()) || queue.getExecuteHost().contains(ipaddr)))
//						{
//							Channel channel = connection.createChannel(false);
//							channel.exchangeDeclare(exchange.getExchangeName(),"topic");
//							channel.queueDeclare(queue.getQueueName(), true, false, false, null);
//							for(String routingKey : queue.getRoutingKeys())
//							{
//								channel.queueBind(queue.getQueueName(), exchange.getExchangeName(), routingKey);
//							}
//							channel.basicQos(1);
//							channel.basicConsume(queue.getQueueName() , false ,new Executer(channel,this,queueName));
//						}
//					}
//				}
//			}
//			catch (IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//	}
}
