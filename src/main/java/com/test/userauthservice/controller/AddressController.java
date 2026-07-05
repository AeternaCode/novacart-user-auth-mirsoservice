package com.test.userauthservice.controller;

import com.test.userauthservice.service.impl.AddressesServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.test.userauthservice.dto.ApiResponse;
import com.test.userauthservice.dto.request.address.CreateAddressRequestDTO;
import com.test.userauthservice.dto.request.address.UpdateAddressRequestDTO;
import com.test.userauthservice.dto.response.address.GetAddressResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/{userId}/address")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "Address Management",
        description = "APIs for managing user addresses"
)

public class AddressController {
    private final AddressesServiceImpl addressesService;

    @Operation(
            summary = "Create address",
            description = "Creates a new address for a user"
    )
    @PostMapping
    public ResponseEntity<ApiResponse<GetAddressResponseDTO>> createAddress(
            @PathVariable
            @Positive(message = "User ID must be greater than 0")
            Long userId,

            @Valid
            @RequestBody CreateAddressRequestDTO request
    ) {
        return ResponseEntity.ok(addressesService.createAddress(userId, request));
    }

    @Operation(
            summary = "Get all addresses",
            description = "Returns all addresses belonging to a user"
    )
    @GetMapping
    public ResponseEntity<ApiResponse<List<GetAddressResponseDTO>>> getAllAddresses(
            @PathVariable
            @Positive(message = "User ID must be greater than 0")
            Long userId
    ) {
        return ResponseEntity.ok(addressesService.getAllAddresses(userId));
    }

    @Operation(
            summary = "Get address by ID",
            description = "Returns a specific address belonging to a user"
    )
    @GetMapping("/{addressId}")
    public ResponseEntity<ApiResponse<GetAddressResponseDTO>> getAddressById(
            @PathVariable
            @Positive(message = "User ID must be greater than 0")
            Long userId,

            @PathVariable
            @Positive(message = "Address ID must be greater than 0")
            Long addressId
    ) {
        return ResponseEntity.ok(addressesService.getAddressById(userId, addressId));
    }

    @Operation(
            summary = "Update address",
            description = "Updates an existing address"
    )
    @PutMapping("/{addressId}")
    public ResponseEntity<ApiResponse<GetAddressResponseDTO>> updateAddress(
            @PathVariable
            @Positive(message = "User ID must be greater than 0")
            Long userId,

            @PathVariable
            @Positive(message = "Address ID must be greater than 0")
            Long addressId,

            @Valid
            @RequestBody UpdateAddressRequestDTO request
    ) {
        return ResponseEntity.ok(addressesService.updateAddress(userId, addressId, request));
    }

    @Operation(
            summary = "Delete address",
            description = "Permanently deletes an address"
    )
    @DeleteMapping("/{addressId}")
    public ResponseEntity<ApiResponse<Long>> deleteAddress(
            @PathVariable
            @Positive(message = "User ID must be greater than 0")
            Long userId,

            @PathVariable
            @Positive(message = "Address ID must be greater than 0")
            Long addressId
    ) {
        return ResponseEntity.ok(addressesService.deleteAddress(userId, addressId));
    }
}
