package com.test.userauthservice.auth.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "Authentication response returned after successful login or registration")
public record AuthenticationResponse(

        @Schema(
                description = "JWT access token used to access protected APIs",
                example = "eyJhbGciOiJIUzI1NiJ9..."
        )
        String accessToken,

        @Schema(
                description = "Refresh token used to obtain a new access token",
                example = "0d3b4f9d-7b82-4ef7-a4a3-3b6b6d5c9d8e"
        )
        String refreshToken,

        @Schema(
                description = "Authentication scheme",
                example = "Bearer"
        )
        String tokenType,

        @Schema(
                description = "Access token expiration time (UTC)"
        )
        Instant accessTokenExpiresAt,

        @Schema(
                description = "Refresh token expiration time (UTC)"
        )
        Instant refreshTokenExpiresAt,

        @Schema(
                description = "Authenticated user information"
        )
        UserSummaryResponse user

) {
}
