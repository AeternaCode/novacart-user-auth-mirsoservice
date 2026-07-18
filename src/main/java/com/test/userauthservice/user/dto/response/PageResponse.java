package com.test.userauthservice.user.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "Generic paginated response returned to the client")
@Builder
public record PageResponse<T>(

        @Schema(description = "List of records in the current page")
        List<T> content,

        @Schema(description = "Current page number (0-based)", example = "0")
        int pageNumber,

        @Schema(description = "Maximum number of records per page", example = "10")
        int pageSize,

        @Schema(description = "Total number of records available", example = "125")
        long totalElements,

        @Schema(description = "Total number of available pages", example = "13")
        int totalPages,

        @Schema(description = "Number of records returned in the current page", example = "10")
        int numberOfElements,

        @Schema(description = "Indicates whether this is the first page", example = "true")
        boolean first,

        @Schema(description = "Indicates whether this is the last page", example = "false")
        boolean last
) {
}