package com.test.userauthservice.auth.service.impl;

import com.test.userauthservice.auth.dto.request.LoginRequest;
import com.test.userauthservice.auth.dto.request.RegisterRequest;
import com.test.userauthservice.auth.dto.response.AuthenticationResponse;
import com.test.userauthservice.auth.mapper.AuthenticationMapper;
import com.test.userauthservice.auth.service.IAuthentication;
import com.test.userauthservice.common.dto.ApiResponse;
import com.test.userauthservice.common.exception.custom_exception.PasswordMismatchException;
import com.test.userauthservice.common.exception.custom_exception.ResourceNotFoundException;
import com.test.userauthservice.common.internalUserService.impl.InternalUserServiceImpl;
import com.test.userauthservice.common.repository.IRoles;
import com.test.userauthservice.common.utils.ENUMS.RoleType;
import com.test.userauthservice.common.utils.ENUMS.UserStatus;
import com.test.userauthservice.common.entity.Roles;
import com.test.userauthservice.user.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthentication {

    private final AuthenticationManager authenticationManager;
    private final InternalUserServiceImpl internalUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final IRoles roleRepository;

    @Override
    public ApiResponse<AuthenticationResponse> register(RegisterRequest registerRequest) {
        if (!registerRequest.password().equals(registerRequest.confirmPassword())) {
            throw new PasswordMismatchException("Password and confirm password do not match.", "PASSWORD_MISMATCH");
        }
        String encodedPassword = passwordEncoder.encode(registerRequest.password());
        Users user = AuthenticationMapper.toUserEntity(registerRequest, encodedPassword);
        // We are doing this here because later we might introduce Google sign in  Their email is already verified If we use a mapper to set all
        // these then we have to change mapper accordingly But if the service decide we can just extract this method and call accordingly whether
        // it is a Google sign in email password or anything else
        user.setStatus(UserStatus.ACTIVE);
        user.setEmailVerified(false);
        user.setPhoneVerified(false);
        Roles role = roleRepository.findByName(RoleType.ROLE_USER)
                .orElseThrow(() -> new ResourceNotFoundException("Role Not Found", "ROLE_USER_NOT_FOUND"));
        user.setRole(role);

        Users savedUser = internalUserService.createUserForAuthentication(user);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(new AuthenticationResponse(
                        "Token",
                        "token",
                        "token",
                        Instant.now(),
                        Instant.now(),
                        AuthenticationMapper.toUserSummary(savedUser)
                ))
                .message("User registered successfully.")
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<AuthenticationResponse> login(LoginRequest loginRequest) {
        return null;
    }
}
