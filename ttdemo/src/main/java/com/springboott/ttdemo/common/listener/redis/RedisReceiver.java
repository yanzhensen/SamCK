package com.springboott.ttdemo.common.listener.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * 注册订阅者  这里不实现implements MessageListener接口
 */
@Component
public class RedisReceiver {

    private static final Logger log = LoggerFactory.getLogger(RedisReceiver.class);
    private CountDownLatch latch;

    @Autowired
    public RedisReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage1(String jsonMsg) {
        try {
            log.info("[消费REDIS消息队列DingYue1数据成功. {}]", jsonMsg);
        } catch (Exception e) {
            log.error("[消费REDIS消息队列DingYue1数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }

    /**
     * 队列消息接收方法
     *
     * @param jsonMsg
     */
    public void receiveMessage2(String jsonMsg) {
        try {
            /**
             *  此处执行自己代码逻辑 例如 插入 删除操作数据库等
             */
            log.info("[消费REDIS消息队列DingYue2数据成功. {}]", jsonMsg);
        } catch (Exception e) {
            log.error("[消费REDIS消息队列DingYue2数据失败，失败信息:{}]", e.getMessage());
        }
        latch.countDown();
    }
}
