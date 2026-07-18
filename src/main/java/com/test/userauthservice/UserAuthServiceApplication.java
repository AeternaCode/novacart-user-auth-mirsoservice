package com.test.userauthservice;

import com.test.userauthservice.common.config.PaginationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PaginationProperties.class)
public class UserAuthServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserAuthServiceApplication.class, args);
    }

}
