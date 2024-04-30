package com.example;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Arrays;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        //这里我们直接使用JedisSentinelPool来获取Master节点
        //需要把三个哨兵的地址都填入
        try (JedisSentinelPool pool = new JedisSentinelPool("masterName",
                new HashSet<>(Arrays.asList("39.101.74.139:20001", "39.101.74.139:20002", "39.101.74.139:20003")))) {
            Jedis jedis = pool.getResource();   //直接询问并得到Jedis对象，这就是连接的Master节点
            jedis.set("test_sentinel", "999");    //直接写入即可，实际上就是向Master节点写入

            Jedis jedis2 = pool.getResource();   //再次获取
            System.out.println(jedis2.get("test"));   //读取操作
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
