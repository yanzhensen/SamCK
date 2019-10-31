package com.springboott.ttdemo.interceptor;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboott.ttdemo.po.User;
import com.springboott.ttdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String username = (String) request.getSession().getAttribute("username");
        if (username != null && !"".equals(username)) {
            QueryWrapper<User> queryWrapper = new QueryWrapper();
            queryWrapper.eq("username",username);
            UserDetails userDetails = userService.getOne(queryWrapper);
            if (userDetails != null && userDetails.isEnabled()) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails,
                                null, userDetails.getAuthorities());
                // 把当前登陆用户放到上下文中
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                        request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                // 用户不合法，清除上下文
                SecurityContextHolder.clearContext();
            }
        }
        filterChain.doFilter(request, response);
    }

}
