package com.example.week4tr1;

import io.swagger.v3.oas.integration.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Week4Tr1Application {

    public static void main(String[] args) {
        SpringApplication.run(Week4Tr1Application.class, args);
    }

    @Bean
    public SwaggerConfiguration getSwaggerConfiguration() {
        return new SwaggerConfiguration();
    }

}
