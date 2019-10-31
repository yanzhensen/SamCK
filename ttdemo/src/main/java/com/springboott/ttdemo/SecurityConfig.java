package com.springboott.ttdemo;

import com.springboott.ttdemo.interceptor.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AuthFilter authFilter;

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.antMatcher("/api/**")
                .addFilterBefore(authFilter, LogoutFilter.class)
                .authorizeRequests()
                //指定路径下 放权
//                .antMatchers("/api/notneedlogin/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated();
    }
}
