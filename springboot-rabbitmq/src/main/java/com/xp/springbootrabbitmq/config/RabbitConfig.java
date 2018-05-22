package com.xp.springbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by xp-zhao on 2018/5/22.
 */
@Configuration
public class RabbitConfig
{
	@Bean
	public Queue helloQueue()
	{
		return new Queue("hello");
	}
}
