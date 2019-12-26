package com.springboott.ttdemo.config;

import com.springboott.ttdemo.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.FormContentFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 extends WebMvcConfigurationSupport {

    /**
     * 允许跨域的origin
     */
    /*private static final String[] origins = {
            "http://localhost",
            "http://localhost:8080",
            "http://172.18.88.80",
            "http://172.18.88.127",
            "http://172.18.88.133:8080",
            "http://www.fangzhizun.com"
    };
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //将需要跨域访问的域名或IP添加到origins中
        registry.addMapping("/**")
                .allowedOrigins(origins)
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }*/
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
//                .apis(RequestHandlerSelectors.basePackage("com.springboott.ttdemo"))
                .paths(PathSelectors.any())
                .build();
    }

    //http://localhost:8089/ttdemo/swagger-ui.html#/
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("《SamSwaggerDemo演示》")//标题
                .description("这是基于用户管理的一个后台系统")//描述
                .termsOfServiceUrl("https://github.com/yanzhensen/SamCK")//（不可见）条款地址，公司内部使用的话不需要配
                .contact(new Contact("Sam", "http://localhost:8081/ttdemo/go/login", "932396823@qq.com"))//作者信息
                .version("1.0.1")//版本号
                .build();
    }

    //静态资源拦截
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Bean
    public FormContentFilter formContentFilter() {
        //配置http put请求传参无效
        return new FormContentFilter();
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
}
