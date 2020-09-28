package com.springboott.ttdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboott.ttdemo.common.controller.SuperController;
import com.springboott.ttdemo.common.enums.ErrorCodeEnum;
import com.springboott.ttdemo.common.exception.ApiAssert;
import com.springboott.ttdemo.common.response.ApiResponses;
import com.springboott.ttdemo.service.TestService;
import com.springboott.ttdemo.util.RedisUtils;
import com.springboott.ttdemo.util.RestTemplateUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.locks.ReentrantLock;

@Api(tags = "公共测试")
@RestController
@RequestMapping("/threadTest")
public class PublicController extends SuperController {

    @Autowired
    private RestTemplateUtils restTemplateUtils;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private TestService testService;

    @ApiOperation(value = "restTemplate请求异常接收")
    @GetMapping("/send")
    public ApiResponses<JSONObject> userList() {
        String objText = "";
        objText = restTemplateUtils.get("http://localhost:8089/ttdemo/threadTest/result", null);
        System.out.println("objText = " + objText);
        return success(JSONObject.parseObject(objText));
    }

    @ApiOperation(value = "restTemplate调用接口")
    @GetMapping("/result")
    public ApiResponses<JSONObject> result() {
        JSONObject obj = new JSONObject();
        obj.put("name", "李四");
        obj.put("age", 20);
        ApiAssert.failure(ErrorCodeEnum.UNAUTHORIZED.convert("sladjaklsdasdasd"));
        return success(obj);
    }


    @ApiOperation(value = "压力测试")
    @GetMapping("/pow")
    public ApiResponses<Void> pow() {
        for (int i = 0; i < 50; i++) {
            testService.pow();
        }
        return success();
    }

    private static ReentrantLock lock = new ReentrantLock(true);

    @ApiOperation(value = "模拟并发事务")
    @GetMapping("/boom")
    public ApiResponses<Void> boom(String co) {
//        boolean b = redisUtils.setIfAbsent("TT_" + co, co);
//        if (b) {
//            testService.buyGoods();
//            redisUtils.delete("TT_" + co);
//        }
        Long orderId = redisTemplate.opsForValue().increment("orderId");

        System.out.println("orderId = " + orderId);
        return success();
    }


    @ApiOperation(value = "订阅模式：发布")
    @GetMapping("/pubMsg")
    public ApiResponses<Void> pubMsg(String msg) {
        redisTemplate.convertAndSend("DingYue1", msg);
        redisTemplate.convertAndSend("DingYue2", msg);
        return success();
    }

}
