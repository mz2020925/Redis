package com.example;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;

public class Main {
    public static void main(String[] args) {
        try(JedisCluster cluster = new JedisCluster(new HostAndPort("39.101.74.139", 6379))){
            System.out.println("集群实例数量："+cluster.getClusterNodes().size());
            cluster.set("a", "yyds");
            System.out.println(cluster.get("a"));
        }
    }
}
