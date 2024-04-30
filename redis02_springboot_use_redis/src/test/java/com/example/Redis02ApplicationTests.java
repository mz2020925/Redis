package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

@SpringBootTest
class Redis02ApplicationTests {
	@Resource
	StringRedisTemplate template;

	@Test
	void contextLoads(){
		template.opsForValue().set("springboot", "hello springboot");
		template.opsForValue().get("springboot");

		template.delete("springboot");    //删除键springboot
		System.out.println(template.hasKey("springboot"));   //判断是否包含键
	}

}
