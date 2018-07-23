package com.xp.springbootrabbitmq;

import com.rabbitmq.client.Channel;
import com.xp.springbootrabbitmq.config.RabbitMQConfig;
import com.xp.springbootrabbitmq.recev.Executer;
import com.xp.springbootrabbitmq.recev.RabbitFactory;
import com.xp.springbootrabbitmq.send.Worker;
import com.xp.springbootrabbitmq.util.HostUtil;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xp-zhao on 2018/6/7.
 */
@Component
public class Init implements ApplicationRunner
{
	@Autowired
	private        ConnectionFactory factory;
	private static Connection        connection;
	@Autowired
	private        RabbitMQConfig    config;

	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		init();

		ExecutorService pool = Executors.newFixedThreadPool(1);
		for(int i = 0; i < 1; i++)
		{
			pool.execute(new Worker());
		}
		System.out.println("消息发送结束！");
		pool.shutdown();
	}

	public void init()
	{
		String ipaddr = HostUtil.localIp();
		System.out.println(ipaddr);
		List<RabbitMQConfig.Exchange> exchanges = config.getExchange();
		for(RabbitMQConfig.Exchange exchange : exchanges)
		{
			try
			{
				List<RabbitMQConfig.QueueList> queueLists = exchange.getQueues();
				for(RabbitMQConfig.QueueList queue : queueLists)
				{
					if(CollectionUtils.isEmpty(queue.getExecuteHost()) || queue.getExecuteHost().contains(ipaddr))
					{
						connection = factory.createConnection();
						for(int i = 0; i < queue.getConcurrentConsumers(); i++)
						{
							Channel channel = connection.createChannel(false);
							channel.exchangeDeclare(exchange.getExchangeName(),"topic");
							channel.queueDeclare(queue.getQueueName(), true, false, false, null);
							for(String routingKey : queue.getRoutingKeys())
							{
								channel.queueBind(queue.getQueueName(), exchange.getExchangeName(), routingKey);
							}
							channel.basicQos(1);
							channel.basicConsume(queue.getQueueName() , false ,new Executer(channel,
								RabbitFactory.queryConsumer(queue.getQueueName()),queue.getQueueName()));
						}
					}
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
