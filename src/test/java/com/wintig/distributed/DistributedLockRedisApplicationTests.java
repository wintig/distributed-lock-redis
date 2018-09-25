package com.wintig.distributed;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributedLockRedisApplicationTests {

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void contextLoads() {

		redisTemplate.opsForValue().set("test1", "asd");
        Object test = redisTemplate.opsForValue().get("test");

    }

	@Test
	public void red() {

		Object test = redisTemplate.opsForValue().get("test1");
	}
}
