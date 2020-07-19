package com.springboott.ttdemo.common.config;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.springboott.ttdemo.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * <p>
 * Spring Boot 配置
 * </p>
 *
 * @author Sam
 */
@Configuration
public class WebMvcAutoConfiguration implements WebMvcConfigurer {
    /**
     * 允许跨域的origin
     */
    private static final String[] origins = {
            "http://localhost",
            "http://localhost:8080",
    };

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //将需要跨域访问的域名或IP添加到origins中
        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Bean
    public LoginInterceptor loginIntercepter() {
        //新建登录拦截器  得自己创建
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginIntercepter())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/go/login",
                        "/user/login",
                        "/doc.html",
                        "/swagger-ui.html",
                        "/csrf",
                        "/configuration/ui",
                        "/swagger-resources/**",
                        "/configuration/security",
                        "/v2/api-docs",
                        "/error",
                        "/webjars/**",
                        "/static/**",
                        "/**/favicon.ico"
                );
    }

    @Bean
    @ConditionalOnMissingBean(RequestContextListener.class)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Bean
    public FormContentFilter formContentFilter() {
        //配置http put请求传参无效
        return new FormContentFilter();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        return restTemplate;
    }

    //静态资源拦截
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Value("${datasource.driver}")
    private String DRIVER;
    @Value("${datasource.url}")
    private String URL;
    @Value("${datasource.username}")
    private String USERNAME;
    @Value("${datasource.password}")
    private String PASSWORD;
    @Bean
    public JdbcDataSourceProvider dynamicDataSourceProvider() {
        String sql = "SELECT * FROM datasource";
        return new JdbcDataSourceProvider(DRIVER, URL, USERNAME, PASSWORD, sql);
    }

    @Bean
    public Converter<String, LocalDate> localDateConverter() {
        return new Converter<String, LocalDate>() {
            @Override
            public LocalDate convert(String source) {
                if (StringUtils.isNotEmpty(source)) {
                    return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                }
                return null;
            }
        };
    }

    @Bean
    public Converter<String, LocalTime> localTimeConverter() {
        return new Converter<String, LocalTime>() {
            @Override
            public LocalTime convert(String source) {
                if (StringUtils.isNotEmpty(source)) {
                    return LocalTime.parse(source, DateTimeFormatter.ofPattern("HH:mm:ss"));
                }
                return null;
            }
        };
    }

    @Bean
    public Converter<String, LocalDateTime> localDateTimeConverter() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (StringUtils.isNotEmpty(source)) {
                    return LocalDateTime.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                }
                return null;
            }
        };
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        //redis
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        return container;
    }
}
