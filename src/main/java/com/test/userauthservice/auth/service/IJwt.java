package com.test.userauthservice.auth.service;

import com.test.userauthservice.user.entity.Users;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

public interface IJwt {
    String generateAccessToken(Users user);

    String generateRefreshToken(Users user);

    String extractUsername(String token);

    boolean isTokenValid(String token, UserDetails user);

    Instant getTokenExpiry(String token);
}
