package com.springboott.ttdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@MapperScan("com.springboott.ttdemo.dao")
@SpringBootApplication
public class TtdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TtdemoApplication.class, args);
        System.out.println("http://localhost:8089/ttdemo/swagger-ui.html#/");
    }

}

