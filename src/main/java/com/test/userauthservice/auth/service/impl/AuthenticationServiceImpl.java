package com.test.userauthservice.auth.service.impl;

import com.test.userauthservice.auth.dto.request.LoginRequest;
import com.test.userauthservice.auth.dto.request.RefreshTokenRequest;
import com.test.userauthservice.auth.dto.request.RegisterRequest;
import com.test.userauthservice.auth.dto.response.AuthenticationResponse;
import com.test.userauthservice.auth.dto.response.UserSummaryResponse;
import com.test.userauthservice.auth.entity.RefreshToken;
import com.test.userauthservice.auth.mapper.AuthenticationMapper;
import com.test.userauthservice.auth.security.CustomUserDetails;
import com.test.userauthservice.auth.service.IAuthentication;
import com.test.userauthservice.common.dto.ApiResponse;
import com.test.userauthservice.common.exception.custom_exception.InvalidTokenException;
import com.test.userauthservice.common.exception.custom_exception.PasswordMismatchException;
import com.test.userauthservice.common.exception.custom_exception.ResourceNotFoundException;
import com.test.userauthservice.common.internalUserService.impl.InternalUserServiceImpl;
import com.test.userauthservice.common.repository.IRoles;
import com.test.userauthservice.common.utils.ENUMS.RoleType;
import com.test.userauthservice.common.utils.ENUMS.UserStatus;
import com.test.userauthservice.common.entity.Roles;
import com.test.userauthservice.user.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements IAuthentication {

    private final AuthenticationManager authenticationManager;
    private final InternalUserServiceImpl internalUserService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final IRoles roleRepository;
    private final RefreshTokenServiceImpl refreshTokenService;
    private static final String TOKEN_TYPE = "Bearer";

    @Override
    public ApiResponse<AuthenticationResponse> register(RegisterRequest registerRequest) {
        log.info("Register request: {}", registerRequest);
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

        String accessToken = jwtService.generateAccessToken(savedUser);

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(savedUser);

        return ApiResponse.<AuthenticationResponse>builder()
                .data(buildAuthenticationResponse(savedUser, accessToken, refreshToken.getToken()))
                .message("User registered successfully.")
                .success(true)
                .build();
    }

    @Override
    public ApiResponse<AuthenticationResponse> login(LoginRequest loginRequest) {
        Authentication authentication =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.email(),
                        loginRequest.password()
                ));
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Users user = customUserDetails != null ? customUserDetails.getUser() : null;
        String accessToken = jwtService.generateAccessToken(user);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        AuthenticationResponse response = buildAuthenticationResponse(user, accessToken, refreshToken.getToken());
        return ApiResponse.<AuthenticationResponse>builder()
                .data(response)
                .message("Login successful.")
                .success(true)
                .build();
    }

    private AuthenticationResponse buildAuthenticationResponse(Users savedUser, String accessToken, String refreshToken) {
        return new AuthenticationResponse(
                accessToken,
                refreshToken,
                TOKEN_TYPE,
                jwtService.getTokenExpiry(accessToken),
                jwtService.getTokenExpiry(refreshToken),
                AuthenticationMapper.toUserSummary(savedUser)
        );
    }

    @Override
    public ApiResponse<UserSummaryResponse> getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        CustomUserDetails customUserDetails = authentication !=null ?(CustomUserDetails) authentication.getPrincipal() : null;

        Users user = customUserDetails != null ? customUserDetails.getUser() : null;

        return ApiResponse.<UserSummaryResponse>builder()
                .success(true)
                .message("Current user fetched successfully.")
                .data(AuthenticationMapper.toUserSummary(user))
                .build();
    }

    @Override
    public ApiResponse<AuthenticationResponse> refreshToken(RefreshTokenRequest request) {
        // 0. Verify token signature
        if(!jwtService.isRefreshTokenValid(request.refreshToken())){
            throw new InvalidTokenException("Invalid refresh token","REFRESH_TOKEN_INVALID");
        }

        // 1. Verify refresh token
        RefreshToken oldRefreshToken = refreshTokenService.verifyRefreshToken(request.refreshToken());

        // 2. Rotate refresh token
        RefreshToken newRefreshToken = refreshTokenService.rotateRefreshToken(oldRefreshToken);

        // 3. Get user
        Users user = newRefreshToken.getUser();

        // 4. Generate new access token
        String accessToken = jwtService.generateAccessToken(user);

        // 5. Build response
        AuthenticationResponse response = buildAuthenticationResponse(
                        user,
                        accessToken,
                        newRefreshToken.getToken()
                );

        return ApiResponse.<AuthenticationResponse>builder()
                .success(true)
                .message("Access token refreshed successfully.")
                .data(response)
                .build();
    }
}
