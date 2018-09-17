package com.iheart.advertiser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableSwagger2
public class AdvertiserApplication {

    @Bean
    public Docket apiDocket() {
        ApiInfo apiInfo = new ApiInfo(
                "Advertiser Api Service",
                "a wonderful advertiser service",
                "1.0.0",
                "termsOfServiceUrl",
                new Contact("mmonahan", "https://github.com/mmonahan", "fake@email.com"),
                "license",
                "licenseUrl",
                Collections.emptyList());

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(regex("/api.*"))
                .build()
                .apiInfo(apiInfo);
    }

    public static void main(String[] args) {
        SpringApplication.run(AdvertiserApplication.class, args);
    }
}
