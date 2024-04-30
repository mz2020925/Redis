package com.example;

import com.example.pojo.Student;
import com.example.service.RedisService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@SpringBootTest
class Redis03ApplicationTests {
	@Resource
	RedisService redisService;

	@Test
	void testRedisService(){
		redisService.test();
	}


	@Test
	void contextLoads() {
		// //注意Student需要实现序列化接口才能存入Redis
		// template.opsForValue().set("student", new Student());
		// System.out.println(template.opsForValue().get("student"));
	}





}
