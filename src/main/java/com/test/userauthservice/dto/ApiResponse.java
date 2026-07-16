package com.test.userauthservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "Standard API response wrapper")
@Builder
public record ApiResponse<T>(

        @Schema(
                description = "Indicates whether the API request was successful",
                example = "true"
        )
        boolean success,

        @Schema(
                description = "Human-readable response message",
                example = "User fetched successfully"
        )
        String message,

        @Schema(
                description = "Response payload containing the requested data"
        )
        T data
) {
}