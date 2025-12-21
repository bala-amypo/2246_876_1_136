package com.example.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI loanApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Loan Eligibility & Risk Checker API")
                        .description("Spring Boot REST API for Loan Eligibility and Risk Assessment")
                        .version("1.0"));
    }
}
