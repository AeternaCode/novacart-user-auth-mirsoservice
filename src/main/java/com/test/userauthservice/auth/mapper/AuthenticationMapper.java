package com.test.userauthservice.auth.mapper;

import com.test.userauthservice.auth.dto.request.RegisterRequest;
import com.test.userauthservice.auth.dto.response.UserSummaryResponse;
import com.test.userauthservice.common.utils.ENUMS.UserStatus;
import com.test.userauthservice.user.entity.Users;

public class AuthenticationMapper {

    public static Users toUserEntity(RegisterRequest registerRequest, String encodedPassword){
        return Users.builder()
                .email(registerRequest.email())
                .password(encodedPassword)
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .phoneNumber(registerRequest.phoneNumber())
                .build();
    }

    public static UserSummaryResponse toUserSummary(Users user){
        return new UserSummaryResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                UserStatus.ACTIVE
        );
    }
}
