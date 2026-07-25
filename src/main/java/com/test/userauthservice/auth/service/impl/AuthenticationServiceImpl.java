package com.test.userauthservice.auth.service.impl;

import com.test.userauthservice.auth.dto.request.LoginRequest;
import com.test.userauthservice.auth.dto.request.RegisterRequest;
import com.test.userauthservice.auth.dto.response.AuthenticationResponse;
import com.test.userauthservice.auth.service.IAuthentication;
import com.test.userauthservice.common.dto.ApiResponse;
import com.test.userauthservice.user.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthentication {

    private final AuthenticationManager authenticationManager;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;

    @Override
    public ApiResponse<AuthenticationResponse> register(RegisterRequest registerRequest) {
        return null;
    }

    @Override
    public ApiResponse<AuthenticationResponse> login(LoginRequest loginRequest) {
        return null;
    }
}
