package com.study;

import redis.clients.jedis.Jedis;


public class App {
    public static void main(String[] args) {
        //创建Jedis对象
        Jedis jedis = new Jedis("39.101.74.139", 6379);
        jedis.auth("Root_1234");
        jedis.set("jedis_test", "redis with java");
        System.out.println(jedis.get("jedis_test"));
        //使用之后关闭连接
        jedis.close();

        use_try_catch();  // 使用try-catch省去close()

        use_Hash();  // 测试插入Hash类型的数据

        use_List();  // 测试插入List类型的数据
    }


    public static void use_try_catch() {
        //直接使用try-with-resouse，省去close
        try(Jedis jedis = new Jedis("192.168.10.3", 6379)){
            jedis.auth("Root_1234");
            jedis.set("test", "lbwnb");   //等同于 set test lbwnb 命令
            System.out.println(jedis.get("test"));  //等同于 get test 命令
        }
    }

    public static void use_Hash() {
        try(Jedis jedis = new Jedis("192.168.10.3", 6379)){
            jedis.auth("Root_1234");
            jedis.hset("hhh", "name", "sxc");   //等同于 hset hhh name sxc
            jedis.hset("hhh", "sex", "19");    //等同于 hset hhh age 19
            jedis.hgetAll("hhh").forEach((k, v) -> System.out.println(k+": "+v));
        }
    }

    public static void use_List() {
        try(Jedis jedis = new Jedis("192.168.10.3", 6379)){
            jedis.auth("Root_1234");
            jedis.lpush("mylist", "111", "222", "333");  //等同于 lpush mylist 111 222 333 命令
            jedis.lrange("mylist", 0, -1)
                    .forEach(System.out::println);    //等同于 lrange mylist 0 -1
        }
    }
}
