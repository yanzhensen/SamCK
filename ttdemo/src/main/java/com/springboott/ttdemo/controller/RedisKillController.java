package com.springboott.ttdemo.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.springboott.ttdemo.config.response.Result;
import com.springboott.ttdemo.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/thread")
public class RedisKillController {

    //限制流量 每秒生成10个令牌 一开始就会初始化10个
    RateLimiter rateLimiter = RateLimiter.create(10);
    @Autowired
    private RedisUtils redisUtils;

    @PostMapping("/kill")
    public Result<String> aaa() {
        //获取令牌 返回等待时间
        double acquire = rateLimiter.acquire();
//        System.out.println("等待时间" + acquire);
        //1秒内没取得令牌 则请求失败
        if (!rateLimiter.tryAcquire(1, TimeUnit.SECONDS)) {
            System.out.println("短期无法获取令牌，真不幸");
            return new Result<>(1, "失败", "短期无法获取令牌，真不幸");
        }
        //其他地方设置 redis秒杀时间 10秒
        String absent = redisUtils.get("killTime");
        if (Objects.isNull(absent)) {
            System.out.println("秒杀活动已结束，欢迎下次活动");
            return new Result<>(1, "成功", "其他人正在秒杀，无法进入");
        }
        try {
            int goods = Integer.parseInt(redisUtils.get("goods"));
            int realGoods = goods - 1;
            if (goods > 0) {
                //模拟业务逻辑处理时间 50毫秒
                Thread.sleep(50);
                redisUtils.incrBy("goods", -1);
                System.out.println("你已经成功秒杀，商品剩余：" + realGoods);
                return new Result<>(1, "成功", "你已经成功秒杀，商品剩余：" + realGoods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("商品已售罄，欢迎下次活动");
        return new Result<>(1, "成功", "商品已售罄，欢迎下次活动");
    }
}
