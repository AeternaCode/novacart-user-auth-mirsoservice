package com.test.userauthservice.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

@Schema(description = "Product details returned to the client with Pagination Detail")
@Builder
public record PageResponse<T>(
         List<T> content,

         int pageNumber,

         int pageSize,

         long totalElements,

         int totalPages,

         int numberOfElements,

         boolean first,

         boolean last
) {
}
