package com.xp.springbootrabbitmq;

import com.xp.springbootrabbitmq.send.Sender;
import com.xp.springbootrabbitmq.send.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xp-zhao on 2018/6/7.
 */
@Component
public class Init implements ApplicationRunner
{
	@Override
	public void run(ApplicationArguments args) throws Exception
	{
		ExecutorService pool = Executors.newFixedThreadPool(10);
		for(int i = 0; i < 100000; i++)
		{
			pool.execute(new Worker());
		}
		System.out.println("消息发送结束！");
		pool.shutdown();
	}
}
