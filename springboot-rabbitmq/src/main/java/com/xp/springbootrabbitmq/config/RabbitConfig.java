package com.xp.springbootrabbitmq.config;

import com.rabbitmq.client.Channel;
import com.xp.springbootrabbitmq.recev.BaseConsumer;
import com.xp.springbootrabbitmq.recev.HandleService;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Configuration
public class RabbitConfig
{
	@Bean
	public TopicExchange exchange()
	{
		return  new TopicExchange("local.test");
	}

	@Bean
	public Queue queue1()
	{
		return new Queue("queue1");
	}
	@Bean
	public Queue queue2()
	{
		return new Queue("queue2");
	}

	@Bean
	public Binding binding(Queue queue1,TopicExchange exchange)
	{
		return BindingBuilder.bind(queue1).to(exchange).with("test");
	}
	@Bean
	public Binding binding1(Queue queue2,TopicExchange exchange)
	{
		return BindingBuilder.bind(queue2).to(exchange).with("test");
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
		SimpleRabbitListenerContainerFactoryConfigurer configurer , ConnectionFactory connectionFactory)
	{
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		configurer.configure(factory,connectionFactory);
		factory.setConnectionFactory(connectionFactory);
		return factory;
	}

	@Bean
	public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(queue1());
		container.setExposeListenerChannel(true);
		container.setConcurrentConsumers(5);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(new HandleService());
		return container;
	}
	@Bean
	public SimpleMessageListenerContainer messageContainer1(ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueues(queue2());
		container.setExposeListenerChannel(true);
		container.setConcurrentConsumers(3);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(new HandleService());
		return container;
	}

	public SimpleMessageListenerContainer messageContainer1(String queueName,ConnectionFactory connectionFactory) {
		SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
		container.setQueueNames(queueName);
		container.setExposeListenerChannel(true);
		container.setConcurrentConsumers(3);
		container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
		container.setMessageListener(new HandleService());
		return container;
	}
}
