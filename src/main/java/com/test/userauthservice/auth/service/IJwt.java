package com.test.userauthservice.auth.service;

import com.test.userauthservice.common.utils.ENUMS.TokenType;
import com.test.userauthservice.user.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

public interface IJwt {
    String generateAccessToken(Users user);

    String generateRefreshToken(Users user);

    String extractUsername(String token);

    boolean isAccessTokenValid(String token, UserDetails user);

    boolean isRefreshTokenValid(String token, UserDetails user);

    TokenType extractTokenType(String token);

    Instant getTokenExpiry(String token);
}
