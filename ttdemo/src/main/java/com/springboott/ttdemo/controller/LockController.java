package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.config.LogAspect;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicReference;

@Api(tags = "多线程lock")
@RestController
@RequestMapping("/threadLock")
public class LockController {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    private RestTemplate restTemplate;

    @ApiOperation(value = "启动线程")
    @PostMapping(value = "/start")
    public String start() {
        AtomicReference<String> result = new AtomicReference<>("error");
        try {
            Thread t1 = new Thread(() -> {
                LinkedMultiValueMap<String, String> map = new LinkedMultiValueMap<>();
                map.add("name", "张三");
                map.add("age", "50");
                String postForObject = restTemplate.postForObject("http://127.0.0.1:8089/ttdemo/threadLock/wayA", map, String.class);
                System.out.println("postForObject = " + postForObject);
                result.set("success");
            });
            t1.start();
            t1.join();//t1执行完后再执行主程序
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.get();
    }

    @ApiOperation(value = "线程A方法")
    @PostMapping(value = "/wayA")
    public String way(String name, String age) {
        try {
            logger.info("######执行A方法######");
            Thread.sleep(5000);
            logger.info("######结束A方法######");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return name + " " + age;
    }
}
