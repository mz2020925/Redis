package com.example;

import com.example.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.ToString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class Redis09ApplicationTests {

	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	private static final ObjectMapper mapper = new ObjectMapper();

	@Test
	void testString(){
		stringRedisTemplate.opsForValue().set("astring", "avalue");
		String s = stringRedisTemplate.opsForValue().get("astring");
		System.out.println("一个字符串: " + s);
	}

	@Test
	void testSaveUser() throws JsonProcessingException {
		// 创建对象
		User user = new User("zmm", 45);
		// 手动序列化
		String jsonString = mapper.writeValueAsString(user);
		// 存储数据
		stringRedisTemplate.opsForValue().set("user:0", jsonString);

		// 读取数据
		String jsonUser = stringRedisTemplate.opsForValue().get("user:0");
		// 手动反序列化
		User user1 = mapper.readValue(jsonUser, User.class);
		System.out.println("user: "+user1);
	}

	@Test
	void testHash(){
		stringRedisTemplate.opsForHash().put("user:1","name","zmz");
		stringRedisTemplate.opsForHash().put("user:2","name","zmz");

		Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:1");
		System.out.println("entries=" + entries);
	}

	@Test
	void contextLoads() {
	}

}
