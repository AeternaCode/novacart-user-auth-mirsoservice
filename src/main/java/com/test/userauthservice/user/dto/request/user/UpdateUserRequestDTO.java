package com.test.userauthservice.user.dto.request.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for updating an existing user")
public record UpdateUserRequestDTO(

        @Schema(
                description = "User's first name",
                example = "Naman"
        )
        @Size(max = 100, message = "First name cannot exceed 100 characters")
        String firstName,

        @Schema(
                description = "User's last name",
                example = "Parashar"
        )
        @Size(max = 100, message = "Last name cannot exceed 100 characters")
        String lastName,

        @Schema(
                description = "User's 10-digit Indian mobile number",
                example = "9876543210"
        )
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Phone number must be a valid 10-digit Indian mobile number"
        )
        String phoneNumber,

        @Schema(
                description = "Profile image URL",
                example = "https://example.com/images/profile.jpg"
        )
        @Size(max = 500, message = "Profile image URL cannot exceed 500 characters")
        String profileImageUrl
) {
}