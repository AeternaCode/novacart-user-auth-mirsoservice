package com.test.userauthservice.service.impl;

import com.test.userauthservice.dto.ApiResponse;
import com.test.userauthservice.dto.request.address.CreateAddressRequestDTO;
import com.test.userauthservice.dto.request.address.UpdateAddressRequestDTO;
import com.test.userauthservice.dto.response.address.GetAddressResponseDTO;
import com.test.userauthservice.repository.AddressesRepo;
import com.test.userauthservice.service.IAddresses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressesServiceImpl implements IAddresses {
    private final AddressesRepo addressesRepo;

    @Override
    public ApiResponse<GetAddressResponseDTO> createAddress(Long userId, CreateAddressRequestDTO request) {
        return null;
    }

    @Override
    public ApiResponse<List<GetAddressResponseDTO>> getAllAddresses(Long userId) {
        return null;
    }

    @Override
    public ApiResponse<GetAddressResponseDTO> getAddressById(Long userId, Long addressId) {
        return null;
    }

    @Override
    public ApiResponse<GetAddressResponseDTO> updateAddress(Long userId, Long addressId, UpdateAddressRequestDTO request) {
        return null;
    }

    @Override
    public ApiResponse<Long> deleteAddress(Long userId, Long addressId) {
        return null;
    }
}
