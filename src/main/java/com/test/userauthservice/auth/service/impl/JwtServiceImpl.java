package com.test.userauthservice.auth.service.impl;

import com.test.userauthservice.auth.service.IJwt;
import com.test.userauthservice.common.config.JwtProperties;
import com.test.userauthservice.common.utils.ENUMS.TokenType;
import com.test.userauthservice.user.entity.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Slf4j
public class JwtServiceImpl implements IJwt {

    private final JwtProperties jwtProperties;

    @Override
    public String generateAccessToken(Users user) {
        return generateToken(user, jwtProperties.getAccessTokenExpiration(), TokenType.ACCESS);
    }

    @Override
    public String generateRefreshToken(Users user) {
        return generateToken(user, jwtProperties.getRefreshTokenExpiration(), TokenType.REFRESH);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isAccessTokenValid(String token) {
        return extractTokenType(token) == TokenType.ACCESS &&
                !isTokenExpired(token);
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        return extractTokenType(token) == TokenType.REFRESH &&
                !isTokenExpired(token);
    }

    @Override
    public TokenType extractTokenType(String token) {
        String tokenType = extractClaim(
                token,
                claims -> claims.get("tokenType", String.class)
        );

        return TokenType.valueOf(tokenType);
    }

    private String generateToken(Users user, long expiration, TokenType tokenType) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getId());
        claims.put("role", user.getRole().getName().name());
        claims.put("tokenType", tokenType.name());

        return Jwts.builder()
                .claims(claims)
                .subject(user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] key = Decoders.BASE64.decode(jwtProperties.getSecret());
        return Keys.hmacShaKeyFor(key);
    }

    private <T> T extractClaim(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // if 10:00 created and now 10:20 then it returns true
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token)
                .getExpiration()
                .before(new Date());
    }
    @Override
    public Instant getTokenExpiry(String token) {
        return extractClaim(token, Claims::getExpiration).toInstant();
    }

}
