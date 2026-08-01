package com.test.userauthservice.auth.service;

import com.test.userauthservice.auth.entity.RefreshToken;
import com.test.userauthservice.user.entity.Users;

public interface IRefreshToken {
    RefreshToken createRefreshToken(Users user);

    RefreshToken verifyRefreshToken(String token);

    void revokeRefreshToken(String token);
}
