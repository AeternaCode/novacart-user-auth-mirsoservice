package com.test.userauthservice.user.dto.request.address;

import com.test.userauthservice.common.utils.ENUMS.AddressType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(description = "Request payload for creating a new address")
public record CreateAddressRequestDTO(

        @Schema(
                description = "Type of address",
                example = "HOME",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotNull(message = "Address type is required")
        AddressType addressType,

        @Schema(
                description = "Primary address line",
                example = "221B Baker Street",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Address line 1 is required")
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
                example = "Delhi",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "City is required")
        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @Schema(
                description = "State or Province",
                example = "Delhi",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "State is required")
        @Size(max = 100, message = "State cannot exceed 100 characters")
        String state,

        @Schema(
                description = "Country name",
                example = "India",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Country is required")
        @Size(max = 100, message = "Country cannot exceed 100 characters")
        String country,

        @Schema(
                description = "Postal or ZIP code",
                example = "110001",
                requiredMode = Schema.RequiredMode.REQUIRED
        )
        @NotBlank(message = "Postal code is required")
        @Pattern(
                regexp = "^[A-Za-z0-9\\- ]{4,20}$",
                message = "Invalid postal code"
        )
        String postalCode
) {
}