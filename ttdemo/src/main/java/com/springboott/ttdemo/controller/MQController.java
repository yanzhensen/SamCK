package com.springboott.ttdemo.controller;

import com.baomidou.mybatisplus.annotation.TableName;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.util.LocalDateTimeUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.Annotation;

@RestController
@RequestMapping("/thread")
public class MQController {

    private static Logger log = LoggerFactory.getLogger(MQController.class);
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @ApiOperation(value = "生产者生产")
    @PostMapping("/send")
    public void send() {
        for (int i = 0; i < 10; i++) {
            //获取类注解
//            TableName sc = AnnotationUtils.findAnnotation(User.class, TableName.class);
//            AnnotationUtils.getValue(sc,"value");
            //用于单生产者-》多消费者测试
            String sendMsg = "direct " + LocalDateTimeUtils.getCurrentTimes();
            //发送存放指定队列
            rabbitTemplate.convertAndSend("direct", sendMsg);
            log.info("生产者： " + sendMsg);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
