package com.test.userauthservice.dto.response.address;

import com.test.userauthservice.utils.ENUMS.AddressType;

import java.time.LocalDateTime;

public record GetAddressResponseDTO(
        Long id,

        AddressType addressType,

        String addressLine1,

        String addressLine2,

        String city,

        String state,

        String country,

        String postalCode,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
