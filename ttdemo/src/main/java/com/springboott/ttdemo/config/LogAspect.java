package com.springboott.ttdemo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class LogAspect {
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private final String POINT_CUT = "execution(public * com.springboott.ttdemo.controller.UserController.*(..))";

    @Before(value = POINT_CUT)
    public void doBeforeAdvice(){
        logger.info("前置通知执行了!");
        logger.info("begin "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    @Pointcut(POINT_CUT)
    public void pointCut(){
    }
    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
        logger.info("后置通知执行了!");
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("run "+System.currentTimeMillis());
            }
        },1000);*/
        logger.info("end "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
          *  异常通知 记录操作报错日志
          * @param joinPoint
          * @param e
          */
    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
                public  void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        logger.info("进入日志切面异常通知!!");
        logger.info("异常所在类:" + className);
        logger.info("异常所用方法:" + methodName);
        logger.info("异常信息:" + e.getMessage());
    }

}
