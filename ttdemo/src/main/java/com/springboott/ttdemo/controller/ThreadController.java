package com.springboott.ttdemo.controller;

import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 多线程测试 前端控制器
 * </p>
 *
 * @author Sam
 * @since 2019-09-04
 */
@Api(tags = "多线程测试类")
@RestController
@RequestMapping("/thread")
public class ThreadController {

    @Autowired
    private UserService userService;

    public static Boolean RUN = Boolean.TRUE;

    @ApiOperation(value = "启动线程")
    @PostMapping(value = "/start")
    public void start() {
        try {
            RUN = Boolean.TRUE;
            Thread t1 = new Thread();
            for (int i = 0; i < 50; i++) {
                if (!RUN) {
                    t1.interrupt();//终止线程
                    break;//跳出循环
                }
                User user = userService.getById(i);
                System.out.println("userNo" + i + " = " + user);
                Thread.sleep(1000);
            }
            t1.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @ApiOperation(value = "停止线程")
    @PostMapping(value = "/stop")
    public void stop() {
        RUN = Boolean.FALSE;
    }


}
