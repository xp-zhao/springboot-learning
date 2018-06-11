package com.xp.springbootrabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
	public Queue helloQueue()
	{
		return new Queue("hello");
	}

	@Bean
	public Binding binding(Queue queue,TopicExchange exchange)
	{
		return BindingBuilder.bind(queue).to(exchange).with("test");
	}
}
