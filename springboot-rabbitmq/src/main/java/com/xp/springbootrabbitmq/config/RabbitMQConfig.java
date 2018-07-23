package com.xp.springbootrabbitmq.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xp-zhao on 2018/7/17.
 */
@Component
@ConfigurationProperties (prefix = "rabbitmq")
public class RabbitMQConfig
{
	private List<Exchange> exchange = new ArrayList<>();

	public List<Exchange> getExchange()
	{
		return exchange;
	}

	public void setExchange(List<Exchange> exchange)
	{
		this.exchange = exchange;
	}

	public static class Exchange
	{
		private String exchangeName;
		private List<QueueList> queues = new ArrayList<>();

		public String getExchangeName()
		{
			return exchangeName;
		}

		public void setExchangeName(String exchangeName)
		{
			this.exchangeName = exchangeName;
		}

		public List<QueueList> getQueues()
		{
			return queues;
		}

		public void setQueues(List<QueueList> queues)
		{
			this.queues = queues;
		}
	}

	public static class QueueList
	{
		private String queueName;
		private List<String> executeHost = new ArrayList<>();
		private Integer concurrentConsumers = 1;
		private List<String> routingKeys = new ArrayList<>();

		public Integer getConcurrentConsumers()
		{
			return concurrentConsumers;
		}

		public void setConcurrentConsumers(Integer concurrentConsumers)
		{
			this.concurrentConsumers = concurrentConsumers;
		}

		public String getQueueName()
		{
			return queueName;
		}

		public void setQueueName(String queueName)
		{
			this.queueName = queueName;
		}

		public List<String> getExecuteHost()
		{
			return executeHost;
		}

		public void setExecuteHost(List<String> executeHost)
		{
			this.executeHost = executeHost;
		}

		public List<String> getRoutingKeys()
		{
			return routingKeys;
		}

		public void setRoutingKeys(List<String> routingKeys)
		{
			this.routingKeys = routingKeys;
		}
	}
}
