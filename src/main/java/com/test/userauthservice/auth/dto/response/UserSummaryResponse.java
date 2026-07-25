package com.test.userauthservice.auth.dto.response;

import com.test.userauthservice.common.utils.ENUMS.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Authenticated user summary")
public record UserSummaryResponse(

        @Schema(
                description = "Unique identifier of the user",
                example = "1"
        )
        Long id,

        @Schema(
                description = "User's first name",
                example = "John"
        )
        String firstName,

        @Schema(
                description = "User's last name",
                example = "Doe"
        )
        String lastName,

        @Schema(
                description = "Registered email address",
                example = "john.doe@example.com"
        )
        String email,

        @Schema(
                description = "Registered mobile number",
                example = "9876543210"
        )
        String phoneNumber,

        @Schema(
                description = "Current account status",
                example = "ACTIVE"
        )
        UserStatus status
) {
}
