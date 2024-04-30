package com.example;

import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis08ApplicationTests {
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;


	@Test
	void testString(){
		//写入一条数据
		redisTemplate.opsForValue().set("地址", "上海");

		Object addr = redisTemplate.opsForValue().get("地址");
		System.out.println("地址：" + addr);
	}

	@Test
	void testSaveUser(){
		redisTemplate.opsForValue().set("user:99", new User("zm", 32));

		User o = (User)redisTemplate.opsForValue().get("user:99");

		System.out.println("User类的一个对象："+o);
	}
	@Test
	void contextLoads() {
	}

}
