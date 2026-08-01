package com.test.userauthservice.auth.service.impl;

import com.test.userauthservice.auth.entity.RefreshToken;
import com.test.userauthservice.auth.repository.RefreshTokenRepository;
import com.test.userauthservice.auth.service.IRefreshToken;
import com.test.userauthservice.common.exception.custom_exception.ResourceNotFoundException;
import com.test.userauthservice.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements IRefreshToken {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtServiceImpl jwtService;

    @Override
    public RefreshToken createRefreshToken(Users user) {
        String refreshToken = jwtService.generateRefreshToken(user);
        RefreshToken refreshTokenEntity = RefreshToken.builder()
                .token(refreshToken)
                .user(user)
                .expiresAt(jwtService.getTokenExpiry(refreshToken).atZone(ZoneId.systemDefault()).toLocalDateTime())
                .revoked(false)
                .build();
        return refreshTokenRepository.save(refreshTokenEntity);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByTokenAndDeletedAtIsNullAndRevokedIsFalse(token)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Refresh token is invalid.",
                        "REFRESH_TOKEN_NOT_FOUND")
                );

        return refreshToken;
    }

    @Override
    public void revokeRefreshToken(String token) {
        RefreshToken refreshToken = verifyRefreshToken(token);
        refreshToken.setRevoked(true);
        refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken rotateRefreshToken(RefreshToken oldRefreshToken) {
           revokeRefreshToken(oldRefreshToken.getToken());
            return createRefreshToken(oldRefreshToken.getUser());
    }


}
