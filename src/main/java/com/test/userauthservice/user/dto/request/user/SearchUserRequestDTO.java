package com.test.userauthservice.user.dto.request.user;

import com.test.userauthservice.common.utils.ENUMS.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request payload for searching and filtering users")
public record SearchUserRequestDTO(

        @Schema(
                description = "Search keyword for first name, last name, or email",
                example = "John"
        )
        String keyword,

        @Schema(
                description = "Filter by phone number",
                example = "9876543210"
        )
        String phoneNumber,

        @Schema(
                description = "Filter by user account status",
                example = "ACTIVE"
        )
        UserStatus status,

        @Schema(
                description = "Filter by role ID",
                example = "2"
        )
        Long roleId,

        @Schema(
                description = "Filter users based on email verification status",
                example = "true"
        )
        Boolean emailVerified,

        @Schema(
                description = "Filter users based on phone verification status",
                example = "false"
        )
        Boolean phoneVerified
) {
}