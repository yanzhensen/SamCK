package com.springboott.ttdemo.config;

import com.springboott.ttdemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class Quartz {
    private static Logger log = LoggerFactory.getLogger(Quartz.class);
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UserService userService;

    //表示方法执行完成后5秒
//    @Scheduled(fixedDelay = 5000)
//    public void fixedDelayJob() {
//        User user = userService.getById(1);
//        log.info("fixedDelay 每隔5秒" + LocalDateTimeUtils.getCurrentTime() + " 用户信息" + user.getUsername() + user.getAge());
//    }

    //表示每隔3秒
//    @Scheduled(fixedRate = 3000)
    public void fixedRateJob() {
        //验证有效时间
        Long expire = redisTemplate.boundHashOps("userInfo").getExpire();
        System.out.println("登录有效时间：" + expire + "S");
    }

    //表示每天8时30分0秒执行
//    @Scheduled(cron = "0 0,30 0,8 ? * ? ")
//    public void cronJob() {
//        log.info(LocalDateTimeUtils.getCurrentTime() + " ...>>cron....");
//    }
}
