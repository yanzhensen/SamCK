package com.springboott.ttdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.util.RedisUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TtdemoApplicationTests {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void contextLoads() {
        User u1 = new User();
        u1.setUsername("张三");
        u1.setAge(20);
        RedisUtils.set("u2", JSON.toJSONString(u1));
        String s = RedisUtils.get("u2");
        User user = JSONObject.parseObject(s, User.class);
        System.out.println(user);
    }

}
