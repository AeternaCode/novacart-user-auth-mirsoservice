package com.test.userauthservice.controller;

import com.test.userauthservice.service.IAddresses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user/{id}/address")
@RequiredArgsConstructor
public class AddressController {
    private final IAddresses addressesService;

    @GetMapping
    public ResponseEntity<?> getAllAddresses(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<?> createAddress(
            @PathVariable Long userId,
            @RequestBody Object request) {

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{addressId}")
    public ResponseEntity<?> getAddressById(
            @PathVariable Long userId,
            @PathVariable Long addressId) {

        return ResponseEntity.ok().build();
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<?> updateAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId,
            @RequestBody Object request) {

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<?> deleteAddress(
            @PathVariable Long userId,
            @PathVariable Long addressId) {

        return ResponseEntity.noContent().build();
    }
}
