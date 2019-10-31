package com.springboott.ttdemo.interceptor;

import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * 登录状态拦截器
 *
 * @author Sam
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //OPTIONS请求不拦截（用户登录发送POST请求前会先发OPTIONS请求，如果拦截了会登录不成功）
//        if (Objects.equals("OPTIONS", request.getMethod())) {
//            return true;
//        }
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("userInfo");
        if (user == null) {
            System.out.println("没有权限请先登陆");
            //未登陆，返回登陆界面
            request.setAttribute("msg","没有权限请先登陆");
            request.getRequestDispatcher("/go/login").forward(request,response);
            return false;
        } else {
            //已登陆，放行请求
            return true;
        }
    }
}
