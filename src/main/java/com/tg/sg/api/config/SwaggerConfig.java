
package com.tg.sg.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.tg.sg.api.controller"))                  
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());
    }
    
    private ApiInfo metaInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Toki games API", // title
                "Documentation of Project REST API", // sub-title
                "1.0", // api version
                "", // term of service
                new Contact("Syed irfan", "", "syedirfan33@ymail.com"), // author
                "", // License Type
                "www.tokigames.com");
        return apiInfo;
    }
 
}
