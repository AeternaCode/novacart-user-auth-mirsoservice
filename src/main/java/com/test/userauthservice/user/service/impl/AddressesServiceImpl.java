package com.test.userauthservice.user.service.impl;

import com.test.userauthservice.user.dto.ApiResponse;
import com.test.userauthservice.user.dto.request.address.CreateAddressRequestDTO;
import com.test.userauthservice.user.dto.request.address.UpdateAddressRequestDTO;
import com.test.userauthservice.user.dto.response.address.GetAddressResponseDTO;
import com.test.userauthservice.user.entity.Address;
import com.test.userauthservice.user.entity.Users;
import com.test.userauthservice.user.mapper.AddressMapper;
import com.test.userauthservice.user.repository.AddressesRepo;
import com.test.userauthservice.user.service.IAddresses;
import com.test.userauthservice.common.utils.VerifyResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressesServiceImpl implements IAddresses {
    private final AddressesRepo addressesRepo;
    private final VerifyResource verifyResource;

    @Override
    @Transactional
    public ApiResponse<GetAddressResponseDTO> createAddress(Long userId, CreateAddressRequestDTO request) {
        Users user = verifyResource.verifyOrGetUserById(userId);
        log.info("Creating address for user: {}", userId);

        Address address = AddressMapper.toAddressEntity(request, user);
        addressesRepo.save(address);
        log.info("Address created successfully. AddressId={}", address.getId());

        return ApiResponse.<GetAddressResponseDTO>builder()
                .success(true)
                .message("Address created successfully with id : " + address.getId())
                .data(AddressMapper.toGetAddressResponseDTO(address))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<GetAddressResponseDTO>> getAllAddresses(Long userId) {
        verifyResource.verifyOrGetUserById(userId);
        log.info("Fetching all addresses for user: {}", userId);

        List<Address> addresses = addressesRepo.findAllByUserId(userId);
        List<GetAddressResponseDTO> addressDTOs = AddressMapper.toGetAddressResponseDTOList(addresses);

        log.info("Successfully fetched {} addresses for user: {}", addresses.size(), userId);
        return ApiResponse.<List<GetAddressResponseDTO>>builder()
                .success(true)
                .message("Addresses fetched successfully")
                .data(addressDTOs)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<GetAddressResponseDTO> getAddressById(Long userId, Long addressId) {
        verifyResource.verifyOrGetUserById(userId);
        Address address = verifyResource.verifyOrGetAddressByIdAndUserId(addressId, userId);

        log.info("Successfully Get address with id : {}", address.getId());
        return ApiResponse.<GetAddressResponseDTO>builder()
                .success(true)
                .message("Address fetched successfully with id : " + address.getId())
                .data(AddressMapper.toGetAddressResponseDTO(address))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<GetAddressResponseDTO> updateAddress(Long userId, Long addressId, UpdateAddressRequestDTO request) {
        verifyResource.verifyOrGetUserById(userId);
        Address address = verifyResource.verifyOrGetAddressByIdAndUserId(addressId, userId);

        log.info("Updating address with id {}", addressId);
        address = AddressMapper.updateAddressFromDTO(request, address);
        addressesRepo.save(address);

        log.info("Address updated successfully. AddressId={}", address.getId());
        return ApiResponse.<GetAddressResponseDTO>builder()
                .success(true)
                .message("Address updated successfully with id : " + address.getId())
                .data(AddressMapper.toGetAddressResponseDTO(address))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Long> deleteAddress(Long userId, Long addressId) {
        verifyResource.verifyOrGetUserById(userId);
        Address address = verifyResource.verifyOrGetAddressByIdAndUserId(addressId, userId);

        log.info("Deleting address with id {}", addressId);
        addressesRepo.deleteById(addressId);

        return ApiResponse.<Long>builder()
                .success(true)
                .message("Address removed successfully with id : " + address.getId())
                .data(null)
                .build();
    }
}
