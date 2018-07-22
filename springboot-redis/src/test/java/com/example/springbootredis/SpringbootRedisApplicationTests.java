package com.example.springbootredis;

import com.example.springbootredis.model.TestModel;
import com.example.springbootredis.util.SpringTool;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void redisTest()
	{
		StringRedisTemplate redisTemplate = (StringRedisTemplate) SpringTool.getBeanById("stringRedisTemplate");
		ValueOperations<String,String> operations = redisTemplate.opsForValue();
		operations.set("redisKey","cluster test");
		operations.set("redisKey1","cluster test");
		System.out.println("11"+operations.get("redisKey"));
	}

	@Test
	public void redisHashTest()
	{
		StringRedisTemplate redisTemplate = (StringRedisTemplate) SpringTool.getBeanById("stringRedisTemplate");
		HashOperations operations = redisTemplate.opsForHash();
		operations.increment("head","field",1);
		Map<String,String> resutl = operations.entries("head");
		TestModel testModel = new TestModel();
		String count1 = resutl.get("field");
		String count2 = resutl.get("count2");
		testModel.setCount1(StringUtils.isNotBlank(count1)?Integer.valueOf(count1):null);
		testModel.setCount2(StringUtils.isNotBlank(count2)?Integer.valueOf(count2):null);
		System.out.println(testModel);
	}

}
