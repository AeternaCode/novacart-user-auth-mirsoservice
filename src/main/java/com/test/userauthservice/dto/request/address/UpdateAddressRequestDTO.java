package com.test.userauthservice.dto.request.address;

import com.test.userauthservice.utils.ENUMS.AddressType;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UpdateAddressRequestDTO(

        AddressType addressType,

        @Size(max = 255, message = "Address line 1 cannot exceed 255 characters")
        String addressLine1,

        @Size(max = 255, message = "Address line 2 cannot exceed 255 characters")
        String addressLine2,

        @Size(max = 100, message = "City cannot exceed 100 characters")
        String city,

        @Size(max = 100, message = "State cannot exceed 100 characters")
        String state,

        @Size(max = 100, message = "Country cannot exceed 100 characters")
        String country,

        @Pattern(
                regexp = "^[A-Za-z0-9\\- ]{4,20}$",
                message = "Invalid postal code"
        )
        String postalCode,

        Boolean isDefault
) {
}
