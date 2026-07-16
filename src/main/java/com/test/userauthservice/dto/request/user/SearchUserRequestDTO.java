package com.test.userauthservice.dto.request.user;

import com.test.userauthservice.utils.ENUMS.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

public record SearchUserRequestDTO(

        @Schema(description = "Search by first name, last name or email")
        String keyword,

        @Schema(description = "Search by phone number")
        String phoneNumber,

        @Schema(description = "Filter by user status")
        UserStatus status,

        @Schema(description = "Filter by role ID")
        Long roleId,

        @Schema(description = "Filter by email verification status")
        Boolean emailVerified,

        @Schema(description = "Filter by phone verification status")
        Boolean phoneVerified
) {
}
