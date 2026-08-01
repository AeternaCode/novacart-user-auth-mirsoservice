package com.test.userauthservice.auth.service;

import com.test.userauthservice.auth.dto.request.LoginRequest;
import com.test.userauthservice.auth.dto.request.RefreshTokenRequest;
import com.test.userauthservice.auth.dto.request.RegisterRequest;
import com.test.userauthservice.auth.dto.response.AuthenticationResponse;
import com.test.userauthservice.auth.dto.response.UserSummaryResponse;
import com.test.userauthservice.common.dto.ApiResponse;

public interface IAuthentication {
    ApiResponse<AuthenticationResponse> register(RegisterRequest registerRequest);
    ApiResponse<AuthenticationResponse> login(LoginRequest loginRequest);
    ApiResponse<UserSummaryResponse> getCurrentUser();
    ApiResponse<AuthenticationResponse> refreshToken(RefreshTokenRequest request);
}
