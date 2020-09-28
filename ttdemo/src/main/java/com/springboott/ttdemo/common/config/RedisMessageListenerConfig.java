package com.springboott.ttdemo.common.config;

import com.springboott.ttdemo.common.listener.redis.RedisReceiver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@Configuration
public class RedisMessageListenerConfig {
    /**
     * 创建连接工厂
     *
     * @param connectionFactory
     * @param listenerAdapter1
     * @param listenerAdapter2
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
                                                   MessageListenerAdapter listenerAdapter1,
                                                   MessageListenerAdapter listenerAdapter2) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //接受消息的key
        container.addMessageListener(listenerAdapter1, new PatternTopic("DingYue1"));
        container.addMessageListener(listenerAdapter2, new PatternTopic("DingYue2"));
        return container;
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter1(RedisReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage1");
    }

    /**
     * 绑定消息监听者和接收监听的方法
     *
     * @param receiver
     * @return
     */
    @Bean
    public MessageListenerAdapter listenerAdapter2(RedisReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage2");
    }

    /**
     * 计数器，用来控制线程
     *
     * @return
     */
    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);//指定了计数的次数 1

    }
}
