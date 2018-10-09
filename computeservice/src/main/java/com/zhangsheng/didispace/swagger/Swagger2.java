package com.zhangsheng.didispace.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author zhangsheng
 * @version 1.0.0
 * @date 17/10/21 下午12:02
 * @blog 
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zhangsheng.didispace.webSwagger"))//扫描的包下面的controller
                .paths(PathSelectors.any())
                .build();
    }
//面板的显示界面的内容的显示
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("张胜构建的接口调试的界面")
                .description("更多Spring Boot请关注我")
                .termsOfServiceUrl("")
                .contact("zhangsheng")
                .version("1.0")
                .build();
    }

}
