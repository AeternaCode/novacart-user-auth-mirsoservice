package com.test.userauthservice.auth.repository;

import com.test.userauthservice.auth.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken,Long> {
    Optional<RefreshToken> findByTokenAndDeletedAtIsNullAndRevokedIsFalse(String token);
}
