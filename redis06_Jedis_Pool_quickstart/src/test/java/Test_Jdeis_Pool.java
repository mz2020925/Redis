import com.example.util.JedisConnectionFacotry;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class Test_Jdeis_Pool {
    private Jedis jedis;

    @BeforeEach
    void setUp() {
        // 1.建立连接
        jedis = JedisConnectionFacotry.getJedis();

        // 2.设置密码
        jedis.auth("Root_1234");
        // 3.选择库
        jedis.select(0);
    }


    @Test
    void testString() {
        // 存入数据
        String result = jedis.set("name", "张明志");
        System.out.println("result = " + result);
        // 获取数据
        String name = jedis.get("name");
        System.out.println("name = " + name);
    }

    @Test
    void testHash() {
        // 插入hash数据
        jedis.hset("user:1", "name", "Jack");
        jedis.hset("user:1", "age", "21");

        // 获取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @AfterEach
    void tearDown() {
        if (jedis != null) {
            jedis.close();
        }
    }
}
