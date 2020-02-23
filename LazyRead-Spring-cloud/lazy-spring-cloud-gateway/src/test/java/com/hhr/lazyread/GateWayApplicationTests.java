package com.hhr.lazyread;

//import com.hhr.lazyread.util.RedisUtil;
import com.hhr.lazyread.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class GateWayApplicationTests {

    @Autowired
    RedisUtil redisUtil;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    void contextLoads() {
//        System.err.println(redisUtil.get("name"));

//        redisUtil.set("222dadasdadas1","harry");

//        System.err.println(redisUtil.get("4312dfasdcasdax34124dcad4123"));

//        System.err.println(redisTemplate.opsForValue().get("4312dfasdcasdax34124dcad4123"));

//        redisUtil.set("name","harry");

        System.err.println(redisUtil.get("12312exdxdase1234"));
    }

}
