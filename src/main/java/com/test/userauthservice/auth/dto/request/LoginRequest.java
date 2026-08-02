package com.test.userauthservice.auth.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Login request details")
public record LoginRequest(

        @Schema(
                description = "Registered email address of the user",
                example = "john.doe@example.com"
        )
        @NotBlank(message = "Email is required")
        @Email(message = "Please provide a valid email address")
        String email,

        @Schema(
                description = "Account password",
                example = "Password@123"
        )
        @NotBlank(message = "Password is required")
        String password

) {
}
