package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;

@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory){
        // 创建RedisTemplate对象
        RedisTemplate<String, Object> template=new RedisTemplate<>();

        // 设置数据库连接工厂
        template.setConnectionFactory(connectionFactory);

        // 创建JSON序列化对象
        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();

        //设置Key采用String序列化
        template.setKeySerializer(RedisSerializer.string());  // RedisSerializer.string()这个字符串序列化的本质是 getBytes()，只是编码的方式可以是UTF-8，ASCII等等
        template.setHashKeySerializer(RedisSerializer.string());

        //设置Value采用JSON序列化，这样写，存储到redis中的自动序列化对象有一个"@class": "com.example.pojo.User"这很占空间，通常不会这么写，通常会自己手动序列化
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        //返回
        return template;
    }
}
