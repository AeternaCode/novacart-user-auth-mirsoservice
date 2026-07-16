package com.test.userauthservice.dto.response.address;

import com.test.userauthservice.utils.ENUMS.AddressType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Address details returned to the client")
public record GetAddressResponseDTO(

        @Schema(
                description = "Unique address identifier",
                example = "1"
        )
        Long id,

        @Schema(
                description = "Type of address",
                example = "HOME"
        )
        AddressType addressType,

        @Schema(
                description = "Primary address line",
                example = "221B Baker Street"
        )
        String addressLine1,

        @Schema(
                description = "Secondary address line",
                example = "Apartment 12"
        )
        String addressLine2,

        @Schema(
                description = "City",
                example = "Delhi"
        )
        String city,

        @Schema(
                description = "State or Province",
                example = "Delhi"
        )
        String state,

        @Schema(
                description = "Country",
                example = "India"
        )
        String country,

        @Schema(
                description = "Postal or ZIP code",
                example = "110001"
        )
        String postalCode,

        @Schema(
                description = "Timestamp when the address was created",
                example = "2026-07-18T10:30:00"
        )
        LocalDateTime createdAt,

        @Schema(
                description = "Timestamp when the address was last updated",
                example = "2026-07-20T15:45:00"
        )
        LocalDateTime updatedAt
) {
}