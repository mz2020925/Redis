package com.example.service;

import com.example.pojo.Student;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Service
public class RedisService {
    @Resource
    RedisTemplate<Object, Object> template;

    @PostConstruct
    public void init(){
        template.setEnableTransactionSupport(true);   //需要开启事务

        //设置把对象序列化成json格式存入Redis,如果不写这句代码，默认是以jdk序列化的方式存储--以这种方式的话Student类要implements Serializable这个接口
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
    }

    @Transactional    //需要添加此注解
    public void test(){
        template.multi();
        template.opsForValue().set("student", new Student());
        template.exec();
    }
}
