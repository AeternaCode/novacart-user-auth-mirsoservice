package com.test.userauthservice.dto.request.address;

import com.test.userauthservice.utils.ENUMS.AddressType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for updating an existing address")
public record UpdateAddressRequestDTO(

        @Schema(
                description = "Type of address",
                example = "HOME"
        )
        AddressType addressType,

        @Schema(
                description = "Primary address line",
                example = "221B Baker Street"
        )
        @Size(max = 255, message = "Address line 1 cannot exceed 255 characters")
        String addressLine1,

        @Schema(
                description = "Secondary address line (Apartment, Floor, Landmark, etc.)",
                example = "Apartment 12"
        )
        @Size(max = 255, message = "Address line 2 cannot exceed 255 characters")
        String addressLine2,

        @Schema(
                description = "City name",
                example = "Delhi"
        )
        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @Schema(
                description = "State or Province",
                example = "Delhi"
        )
        @Size(max = 100, message = "State cannot exceed 100 characters")
        String state,

        @Schema(
                description = "Country name",
                example = "India"
        )
        @Size(max = 100, message = "Country cannot exceed 100 characters")
        String country,

        @Schema(
                description = "Postal or ZIP code",
                example = "110001"
        )
        @Pattern(
                regexp = "^[A-Za-z0-9\\- ]{4,20}$",
                message = "Invalid postal code"
        )
        String postalCode,

        @Schema(
                description = "Marks this address as the default address",
                example = "true"
        )
        Boolean isDefault
) {
}