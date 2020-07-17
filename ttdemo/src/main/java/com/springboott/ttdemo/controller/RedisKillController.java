package com.springboott.ttdemo.controller;

import com.google.common.util.concurrent.RateLimiter;
import com.springboott.ttdemo.config.response.Result;
import com.springboott.ttdemo.util.RedisUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/thread")
public class RedisKillController {

    //限制流量 每秒生成20个令牌
    RateLimiter rateLimiter = RateLimiter.create(20);

    @PostMapping("/kill")
    public Result<String> aaa() {
        //获取令牌
        double acquire = rateLimiter.acquire();
//        System.out.println("等待时间" + acquire);
        //10毫秒没取得令牌 则请求失败
        if (!rateLimiter.tryAcquire(10, TimeUnit.MILLISECONDS)) {
            System.out.println("短期无法获取令牌，真不幸");
            return new Result<>(1, "失败", "短期无法获取令牌，真不幸");
        }
        String user = UUID.randomUUID().toString();
        //上个用户10毫秒没秒杀完则执行失败 删key 进行下一个秒杀
        RedisUtils.expire("lock", 10, TimeUnit.MILLISECONDS);
        //看redis有没有这个key 有证明该线程被占用  这块没看懂 感觉没用
        Boolean absent = RedisUtils.setIfAbsent("lock", user);
        if (!absent) {
            System.out.println("其他人正在秒杀，无法进入");
            return new Result<>(1, "成功", "其他人正在秒杀，无法进入");
        }
        try {
            int goods = Integer.parseInt(RedisUtils.get("goods"));
            int realGoods = goods - 1;
            if (goods > 0) {
                Thread.sleep(20);
                RedisUtils.incrBy("goods", -1);
                System.out.println("你已经成功秒杀，商品剩余：" + realGoods);
                return new Result<>(1, "成功", "你已经成功秒杀，商品剩余：" + realGoods);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (user.equals(RedisUtils.get("lock"))) {
                System.out.println("删除锁" + user);
                RedisUtils.delete("lock");
            }
        }
        System.out.println("商品已售罄，欢迎下次活动");
        return new Result<>(1, "成功", "商品已售罄，欢迎下次活动");
    }
}
