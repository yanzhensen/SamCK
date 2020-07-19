package com.springboott.ttdemo.interceptor;

import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 登录状态拦截器
 *
 * @author Sam
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //OPTIONS请求不拦截（用户登录发送POST请求前会先发OPTIONS请求，如果拦截了会登录不成功）
//        System.out.println("请求路径：" + request.getRequestURI() + " 请求方式： " + request.getMethod());
//        if(true){
//            return true;
//        }
        if (request.getRequestURI().indexOf("/thread") > 0) {
            return true;
        }
        Object userInfo = redisTemplate.opsForValue().get("userInfo");
        if (Objects.isNull(userInfo)) {
            System.out.println("没有权限请先登陆");
            //未登陆，返回登陆界面
            request.setAttribute("msg", "没有权限请先登陆");
            request.getSession().removeAttribute("userInfo");
            request.getRequestDispatcher("/go/login").forward(request, response);
            return false;
        } else {
            //验证有效时间
//            Long expire = redisTemplate.boundHashOps("userInfo").getExpire();
//            System.out.println("LoginInterceptor redis有效时间：" + expire + "S");
            //延时过期时间
            redisTemplate.expire("userInfo", 60, TimeUnit.SECONDS);
            //已登陆，放行请求
            return true;
        }
    }
}
