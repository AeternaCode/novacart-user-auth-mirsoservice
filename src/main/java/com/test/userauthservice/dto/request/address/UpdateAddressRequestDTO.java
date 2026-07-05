package com.test.userauthservice.dto.request.address;

import com.test.userauthservice.utils.ENUMS.AddressType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateAddressRequestDTO(
        @NotNull(message = "Address type is required")
        AddressType addressType,

        @NotBlank(message = "Address line 1 is required")
        @Size(max = 255, message = "Address line 1 cannot exceed 255 characters")
        String addressLine1,

        @Size(max = 255, message = "Address line 2 cannot exceed 255 characters")
        String addressLine2,

        @NotBlank(message = "City is required")
        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @NotBlank(message = "State is required")
        @Size(max = 100, message = "State cannot exceed 100 characters")
        String state,

        @NotBlank(message = "Country is required")
        @Size(max = 100, message = "Country cannot exceed 100 characters")
        String country,

        @NotBlank(message = "Postal code is required")
        @Pattern(
                regexp = "^[A-Za-z0-9\\- ]{4,20}$",
                message = "Invalid postal code"
        )
        String postalCode,

        @NotNull(message = "Default address flag is required")
        Boolean isDefault
) {
}
