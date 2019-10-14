package com.springboott.ttdemo.test;

import com.springboott.ttdemo.TtdemoApplication;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.ProductService;
import com.springboott.ttdemo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@RunWith(SpringRunner.class)
@EnableCaching
@SpringBootTest(classes = TtdemoApplication.class)
public class RedisText {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    private User user;

    //添加字符串
    @Test
    public void setSet() {
        this.redisTemplate.opsForValue().set("张三", "大学生");
    }

    //获取字符串
    @Test
    public void getSet() {
        String str = (String) this.redisTemplate.opsForValue().get("张三");
        System.out.println("你好" + str);
    }

    //添加对象
    @Test
    public void setUser() {
//        this.redisTemplate.opsForValue().set("user",new User(1,"张三",25));
        this.redisTemplate.opsForValue().set("userList", userService.list());
    }

    //获取对象
    @Test
    public void getUser() {
//        System.out.println((User)this.redisTemplate.opsForValue().get("user"));
        System.out.println(this.redisTemplate.opsForValue().get("userList"));
        System.out.println(this.redisTemplate.getClientList());
        ;
    }

    /**
     * 初始化产品库存
     */
    @Test
    public void synchronizedProduct() {
        redisTemplate.opsForZSet().add("rush", "小米6", 500);
        redisTemplate.opsForZSet().add("rush", "小米8", 500);
        redisTemplate.opsForZSet().add("rush", "小米9", 500);
        redisTemplate.opsForZSet().add("rush", "小米mix3", 0);
    }

    //获取对象
    @Test
    public void getProduct() {
        Set<Object> set = this.redisTemplate.opsForZSet().rangeByScore("rush", 0, 1000);
        System.out.println("set = " + set);
        Long aLong = this.redisTemplate.opsForZSet().zCard("rush");
        System.out.println("aLong = " + aLong);
        Map<String, Object> rushMap = new LinkedHashMap<>();
        for (Object productName : set) {
            //取出相关的库存
            Double storeValue = redisTemplate.opsForZSet().score("rush", productName);
            rushMap.put(productName.toString(), storeValue);
        }
        System.out.println("rushMap = " + rushMap);
    }

    @Test
    public void sellProduct() {
        String productName = "小米6";
        //先获取库存
        Double storeValue = redisTemplate.opsForZSet().score("rush", productName);
        boolean isSaleOut = false;
        if (storeValue == null || storeValue == 0) {
            //标记字段设置为false
            isSaleOut = false;
        } else {
            //库存减一
            Double levelStoreValue = redisTemplate.opsForZSet().incrementScore("rush", productName, -1);
            //存放进一个List中
            redisTemplate.opsForList().rightPush(productName + ":sale", 1);
            //抢购成功标记
            isSaleOut = true;
        }
        System.out.println("isSaleOut = " + isSaleOut);

    }
}
