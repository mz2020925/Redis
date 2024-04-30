package com.example;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class Redis07ApplicationTests {
	@Autowired
	private RedisTemplate redisTemplate;  // 使用redisTemplate要注意一个序列化出现\xAC这种字符的问题，而使用Jedis没有这种问题
	// 这里直接写 "RedisTemplate redisTemplate" 就会出现\xAC这种字符的问题，改成 "RedisTemplate<String, String> redisTemplate" 就不会出现这个问题
	// 下一模块讲到 "RedisTemplate<String, Object> redisTemplate" 也不会出现这种问题

	@Test
	void testString(){
		//写入一条数据
		redisTemplate.opsForValue().set("poem", "三千弱水");
		// 读取一条数据
		Object age = redisTemplate.opsForValue().get("poem");
		System.out.println("poem："+age);
	}

	@Test
	void contextLoads() {
	}
}