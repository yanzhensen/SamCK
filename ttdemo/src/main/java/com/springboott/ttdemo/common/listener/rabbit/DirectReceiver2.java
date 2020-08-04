package com.springboott.ttdemo.common.listener.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "direct")
public class DirectReceiver2 {

    private static Logger log = LoggerFactory.getLogger(DirectReceiver2.class);

    @RabbitHandler
    public void process(String msg) {
        try {
            //模拟业务处理
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("消费者2  : " + msg);
    }

}
