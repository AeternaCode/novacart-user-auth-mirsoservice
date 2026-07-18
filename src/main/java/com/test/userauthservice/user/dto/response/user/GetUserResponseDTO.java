package com.test.userauthservice.user.dto.response.user;

import com.test.userauthservice.common.utils.ENUMS.UserStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "User details returned to the client")
public record GetUserResponseDTO(

        @Schema(
                description = "Unique user identifier",
                example = "1"
        )
        Long id,

        @Schema(
                description = "User's first name",
                example = "Naman"
        )
        String firstName,

        @Schema(
                description = "User's last name",
                example = "Parashar"
        )
        String lastName,

        @Schema(
                description = "User's email address",
                example = "naman@gmail.com"
        )
        String email,

        @Schema(
                description = "User's registered mobile number",
                example = "9876543210"
        )
        String phoneNumber,

        @Schema(
                description = "Current status of the user account",
                example = "ACTIVE"
        )
        UserStatus status,

        @Schema(
                description = "Whether the user's email is verified",
                example = "true"
        )
        Boolean emailVerified,

        @Schema(
                description = "Whether the user's phone number is verified",
                example = "false"
        )
        Boolean phoneVerified,

        @Schema(
                description = "Profile image URL",
                example = "https://example.com/images/profile.jpg"
        )
        String profileImageUrl,

        @Schema(
                description = "Assigned role ID",
                example = "2"
        )
        Long roleId,

        @Schema(
                description = "Assigned role name",
                example = "ROLE_USER"
        )
        String roleName,

        @Schema(
                description = "Timestamp when the user account was created",
                example = "2026-07-01T10:15:30"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "Timestamp when the user account was last updated",
                example = "2026-07-10T18:45:12"
        )
        LocalDateTime updatedAt
) {
}