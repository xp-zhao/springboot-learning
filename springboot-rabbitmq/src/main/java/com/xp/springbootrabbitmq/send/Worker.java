package com.xp.springbootrabbitmq.send;

import com.xp.springbootrabbitmq.util.SpringTool;

/**
 * Created by xp-zhao on 2018/6/7.
 */
public class Worker implements Runnable
{
	private Sender sender = (Sender) SpringTool.getBeanById("sender");
	@Override
	public void run()
	{
		sender.send();
	}
}
