package com.springboott.ttdemo.common.listener.redis;

import com.springboott.ttdemo.service.TestService;
import com.springboott.ttdemo.util.LocalDateTimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    @Autowired
    private TestService testService;

    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }

    /**
     * 针对redis数据失效事件，进行数据处理
     * 需要设置redis的配置文件redis.windows-service.conf 设置notify-keyspace-events "Ex"
     *
     * @param message
     * @param pattern
     */
    @Override
    public void onMessage(Message message, byte[] pattern) {
        // 用户做自己的业务处理即可,注意message.toString()可以获取失效的key
        String expiredKey = message.toString();
//        System.out.println("###### " + LocalDateTimeUtils.getCurrentTimes() + " 监听器：key过期 = " + expiredKey + "（当前线程" + Thread.currentThread().getName() + "）");
        if (StringUtils.startsWith(expiredKey, "kk")) {
            testService.solveExpireK(expiredKey);
        }
        if (StringUtils.startsWith(expiredKey, "bb")) {
            testService.solveExpireB(expiredKey);
        }
    }
}
