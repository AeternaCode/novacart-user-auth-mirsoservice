package com.test.userauthservice.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "User registration request details")
public record RegisterRequest(

        @Schema(
                description = "User's first name",
                example = "John",
                maxLength = 100
        )
        @NotBlank(message = "First name is required")
        @Size(max = 100, message = "First name cannot exceed 100 characters")
        String firstName,

        @Schema(
                description = "User's last name",
                example = "Doe",
                maxLength = 100
        )
        @NotBlank(message = "Last name is required")
        @Size(max = 100, message = "Last name cannot exceed 100 characters")
        String lastName,

        @Schema(
                description = "User's email address",
                example = "john.doe@example.com"
        )
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        String email,

        @Schema(
                description = "User's 10-digit Indian mobile number",
                example = "9876543210"
        )
        @NotBlank(message = "Phone number is required")
        @Pattern(
                regexp = "^[6-9]\\d{9}$",
                message = "Phone number must be a valid 10-digit Indian mobile number"
        )
        String phoneNumber,

        @Schema(
                description = "Password must contain at least 8 characters, including one uppercase letter, one lowercase letter, one digit, and one special character",
                example = "Password@123"
        )
        @NotBlank(message = "Password is required")
        @Size(
                min = 8,
                max = 100,
                message = "Password must be between 8 and 100 characters"
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^()_+\\-=\\[\\]{};':\"\\\\|,.<>/?]).{8,100}$",
                message = "Password must contain at least one uppercase letter, one lowercase letter, one digit, and one special character"
        )
        String password,

        @Schema(
                description = "Confirm password. Must exactly match the password.",
                example = "Password@123"
        )
        @NotBlank(message = "Confirm password is required")
        String confirmPassword
) {
}
