package com.test.userauthservice.user.service;

import com.test.userauthservice.user.dto.ApiResponse;
import com.test.userauthservice.user.dto.request.address.CreateAddressRequestDTO;
import com.test.userauthservice.user.dto.request.address.UpdateAddressRequestDTO;
import com.test.userauthservice.user.dto.response.address.GetAddressResponseDTO;

import java.util.List;

public interface IAddresses {
    ApiResponse<GetAddressResponseDTO> createAddress(Long userId, CreateAddressRequestDTO request);
    ApiResponse<List<GetAddressResponseDTO>> getAllAddresses(Long userId);
    ApiResponse<GetAddressResponseDTO> getAddressById(Long userId, Long addressId);
    ApiResponse<GetAddressResponseDTO> updateAddress(Long userId,Long addressId, UpdateAddressRequestDTO request);
    ApiResponse<Long> deleteAddress(Long userId, Long addressId);
}
