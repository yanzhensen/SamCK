package com.springboott.ttdemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class SwaggerConfig {

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

}
