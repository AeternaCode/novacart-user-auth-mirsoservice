package com.test.userauthservice.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    private String secret;

    private long accessTokenExpiration;

    private long refreshTokenExpiration;
}
