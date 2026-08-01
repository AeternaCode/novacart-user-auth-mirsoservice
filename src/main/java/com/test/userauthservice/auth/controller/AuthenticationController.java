package com.test.userauthservice.auth.controller;

import com.test.userauthservice.auth.dto.response.AuthenticationResponse;
import com.test.userauthservice.auth.dto.request.LoginRequest;
import com.test.userauthservice.auth.dto.request.RegisterRequest;
import com.test.userauthservice.auth.dto.response.UserSummaryResponse;
import com.test.userauthservice.auth.service.impl.AuthenticationServiceImpl;
import com.test.userauthservice.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> getCurrentUser() {
        return ResponseEntity.ok(authenticationService.getCurrentUser());
    }

}
