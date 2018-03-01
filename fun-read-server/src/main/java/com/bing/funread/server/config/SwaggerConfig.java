package com.bing.funread.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Description:Swagger配置文件
 * Author: zhangfusheng
 * Date: 2017/11/2 上午10:02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .paths(PathSelectors.regex("/api.*"))
                .build();
    }

    private static ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("趣读诗服务接口")
                .description("趣读诗服务接口文档")
                .termsOfServiceUrl("http://www.funread.com")
                .contact(new Contact("funread", null, ""))
                .license("funread license")
                .licenseUrl("http://www.funread.com")
                .version("1.0")
                .build();
    }

}
