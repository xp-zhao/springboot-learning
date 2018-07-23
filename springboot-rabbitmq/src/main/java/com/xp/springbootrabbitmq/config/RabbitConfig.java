package com.xp.springbootrabbitmq.config;

import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Configuration
public class RabbitConfig
{
	@Autowired
	private        ConnectionFactory factory;
	private static Connection        connection;
	@Autowired
	private        RabbitMQConfig    config;

	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
	{
		RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
		return rabbitTemplate;
	}

//	@PostConstruct
//	public void initss()
//	{
//		String ipaddr = HostUtil.localIp();
//		System.out.println(ipaddr);
//		List<RabbitMQConfig.Exchange> exchanges = config.getExchange();
//		for(RabbitMQConfig.Exchange exchange : exchanges)
//		{
//			try
//			{
//				List<RabbitMQConfig.QueueList> queueLists = exchange.getQueues();
//				for(RabbitMQConfig.QueueList queue : queueLists)
//				{
//					if(CollectionUtils.isEmpty(queue.getExecuteHost()) || queue.getExecuteHost().contains(ipaddr))
//					{
//						connection = factory.createConnection();
//						for(int i = 0; i < queue.getConcurrentConsumers(); i++)
//						{
//							Channel channel = connection.createChannel(false);
//							channel.exchangeDeclare(exchange.getExchangeName(),"topic");
//							channel.queueDeclare(queue.getQueueName(), true, false, false, null);
//							for(String routingKey : queue.getRoutingKeys())
//							{
//								channel.queueBind(queue.getQueueName(), exchange.getExchangeName(), routingKey);
//							}
//							channel.basicQos(1);
//							channel.basicConsume(queue.getQueueName() , false ,new Executer(channel,
//								RabbitFactory.queryConsumer(queue.getQueueName()),queue.getQueueName()));
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

//	@Bean
//	public TopicExchange exchange()
//	{
//		return  new TopicExchange("local.test");
//	}
//
//	@Bean
//	public Queue queue1()
//	{
//		return new Queue("queue1");
//	}
//	@Bean
//	public Queue queue2()
//	{
//		return new Queue("queue2");
//	}
//
//	@Bean
//	public Binding binding(Queue queue1,TopicExchange exchange)
//	{
//		return BindingBuilder.bind(queue1).to(exchange).with("test");
//	}
//	@Bean
//	public Binding binding1(Queue queue2,TopicExchange exchange)
//	{
//		return BindingBuilder.bind(queue2).to(exchange).with("test");
//	}
//
//	@Bean
//	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
//		SimpleRabbitListenerContainerFactoryConfigurer configurer , ConnectionFactory connectionFactory)
//	{
//		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//		configurer.configure(factory,connectionFactory);
//		factory.setConnectionFactory(connectionFactory);
//		return factory;
//	}
//
//	@Bean
//	public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setQueues(queue1());
//		container.setExposeListenerChannel(true);
//		container.setConcurrentConsumers(5);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//		container.setMessageListener(new HandleService());
//		return container;
//	}
//	@Bean
//	public SimpleMessageListenerContainer messageContainer1(ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setQueues(queue2());
//		container.setExposeListenerChannel(true);
//		container.setConcurrentConsumers(3);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//		container.setMessageListener(new HandleService());
//		return container;
//	}
//
//	public SimpleMessageListenerContainer messageContainer1(String queueName,ConnectionFactory connectionFactory) {
//		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
//		container.setQueueNames(queueName);
//		container.setExposeListenerChannel(true);
//		container.setConcurrentConsumers(3);
//		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//		container.setMessageListener(new HandleService());
//		return container;
//	}
}
