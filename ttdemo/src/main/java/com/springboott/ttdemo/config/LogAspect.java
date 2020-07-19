package com.springboott.ttdemo.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Aspect
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private final String POINT_CUT = "execution(public * com.springboott.ttdemo.controller.*Controller.*(..))";
    ThreadLocal<Long> currentTime = new ThreadLocal<>();

    @Before(value = POINT_CUT)
    public void doBeforeAdvice(){
        //logger.info("前置通知执行了!");
        //logger.info("begin "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    @Pointcut(POINT_CUT)
    public void pointCut(){
    }
    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
        //logger.info("后置通知执行了!");
        /*Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                logger.info("run "+System.currentTimeMillis());
            }
        },1000);*/
        //logger.info("end "+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    /**
     * 配置环绕通知,使用在方法logPointcut()上注册的切入点
     *
     * @param joinPoint join point for advice
     */
    @Around(POINT_CUT)
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        //请求域名
        String serverName = request.getServerName();
        //请求方式
        String requestMethod = request.getMethod();
        //请求项目路径
        String uri = request.getRequestURI();
//        logger.info("请求域名：{},请求方式：{} 请求路径：({})", serverName, requestMethod, uri);
        Object result;
//        currentTime.set(System.currentTimeMillis());
        result = joinPoint.proceed();
//        System.out.println("耗时："+ (System.currentTimeMillis() - currentTime.get()));
//        currentTime.remove();
        return result;
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
