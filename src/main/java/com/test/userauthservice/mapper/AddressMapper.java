package com.test.userauthservice.mapper;

import com.test.userauthservice.dto.request.address.CreateAddressRequestDTO;
import com.test.userauthservice.dto.request.address.UpdateAddressRequestDTO;
import com.test.userauthservice.dto.response.address.GetAddressResponseDTO;
import com.test.userauthservice.entity.Address;
import com.test.userauthservice.entity.Users;

import java.util.List;

public class AddressMapper {

    public static GetAddressResponseDTO toGetAddressResponseDTO(Address address) {
        if (address == null) {
            return null;
        }
        return new GetAddressResponseDTO(
                address.getId(),
                address.getAddressType(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPostalCode(),
                address.getCreatedAt(),
                address.getUpdatedAt()
        );
    }

    public static List<GetAddressResponseDTO> toGetAddressResponseDTOList(List<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::toGetAddressResponseDTO)
                .toList();
    }

    public static Address toAddressEntity(CreateAddressRequestDTO createAddressRequestDTO, Users user) {
        if (createAddressRequestDTO == null || user == null) {
            return null;
        }
        return Address.builder()
                .user(user)
                .addressType(createAddressRequestDTO.addressType())
                .addressLine1(createAddressRequestDTO.addressLine1())
                .addressLine2(createAddressRequestDTO.addressLine2())
                .city(createAddressRequestDTO.city())
                .state(createAddressRequestDTO.state())
                .country(createAddressRequestDTO.country())
                .postalCode(createAddressRequestDTO.postalCode())
                .isDefault(false)
                .build();
    }

    public static Address updateAddressFromDTO(UpdateAddressRequestDTO updateAddressRequestDTO, Address address) {
        if (updateAddressRequestDTO == null || address == null) {
            return address;
        }

        if (updateAddressRequestDTO.addressType() != null) {
            address.setAddressType(updateAddressRequestDTO.addressType());
        }

        if (updateAddressRequestDTO.addressLine1() != null && !updateAddressRequestDTO.addressLine1().isBlank()) {
            address.setAddressLine1(updateAddressRequestDTO.addressLine1());
        }

        if (updateAddressRequestDTO.addressLine2() != null && !updateAddressRequestDTO.addressLine2().isBlank()) {
            address.setAddressLine2(updateAddressRequestDTO.addressLine2());
        }

        if (updateAddressRequestDTO.city() != null && !updateAddressRequestDTO.city().isBlank()) {
            address.setCity(updateAddressRequestDTO.city());
        }

        if (updateAddressRequestDTO.state() != null && !updateAddressRequestDTO.state().isBlank()) {
            address.setState(updateAddressRequestDTO.state());
        }

        if (updateAddressRequestDTO.country() != null && !updateAddressRequestDTO.country().isBlank()) {
            address.setCountry(updateAddressRequestDTO.country());
        }

        if (updateAddressRequestDTO.postalCode() != null && !updateAddressRequestDTO.postalCode().isBlank()) {
            address.setPostalCode(updateAddressRequestDTO.postalCode());
        }

        if (updateAddressRequestDTO.isDefault() != null) {
            address.setIsDefault(updateAddressRequestDTO.isDefault());
        }

        return address;
    }
}
