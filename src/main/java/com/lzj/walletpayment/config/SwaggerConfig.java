package com.lzj.walletpayment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: lzj
 * @Date: 2023/3/22-03-22-15:52
 * @Description: com.lzj.walletpayment.config
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    // 配置Swagger的Docket的Bean实例
    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Wallet")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lzj.walletpayment.controller"))  // 根据指定路径扫描
                .build();
    }

    // 配置Swagger的信息
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("钱包的API文档")
                .description("实现了钱包的接口")
                .version("1.0")
                .build();
    }

}
