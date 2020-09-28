package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.util.LocalDateTimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "MQ测试")
@RestController
@RequestMapping("/threadMQ")
public class MQController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "生产者生产")
    @PostMapping("/send")
    public void send() {
        for (int i = 0; i < 10; i++) {
            //用于单生产者-》多消费者测试
            String sendMsg = "direct " + LocalDateTimeUtils.getCurrentTimes();
            //发送存放指定队列
            rabbitTemplate.convertAndSend("direct", sendMsg);
        }
    }
}
