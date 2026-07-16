package com.test.userauthservice.utils;

import com.test.userauthservice.entity.Address;
import com.test.userauthservice.entity.Users;
import com.test.userauthservice.error_handling.custom_exception.ResourceNotFoundException;
import com.test.userauthservice.repository.AddressesRepo;
import com.test.userauthservice.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class VerifyResource {

    private final UserRepo userRepo;
    private final AddressesRepo addressesRepo;

    public Users verifyOrGetUserById(Long id) {
        log.info("Verifying user with id={}", id);
        return userRepo.findByIdAndDeletedAtIsNull(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No User Found with the given id: " + id,
                        "USER_NOT_FOUND"
                ));
    }

    public Users verifyOrGetDeletedUserById(Long id) {
        log.info("Verifying deleted user with id={}", id);
        return userRepo.findByIdAndDeletedAtIsNotNull(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No deleted User Found with the given id: " + id,
                        "USER_NOT_FOUND"
                ));
    }

    public Address verifyOrGetAddressByIdAndUserId(Long addressId, Long userId) {
        log.info("Verifying address with id={} for user id={}", addressId, userId);
        return addressesRepo.findByIdAndUserId(addressId, userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No Address Found with the given id: " + addressId,
                        "ADDRESS_NOT_FOUND"
                ));
    }
}

