package com.test.userauthservice.dto.response.user;

import com.test.userauthservice.utils.ENUMS.UserStatus;

import java.time.LocalDateTime;

public record GetUserResponseDTO(
        Long id,

        String firstName,

        String lastName,

        String email,

        String phoneNumber,

        UserStatus status,

        Boolean emailVerified,

        Boolean phoneVerified,

        String profileImageUrl,

        Long roleId,

        String roleName,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
