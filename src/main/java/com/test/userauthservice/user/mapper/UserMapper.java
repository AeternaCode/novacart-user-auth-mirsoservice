package com.test.userauthservice.user.mapper;

import com.test.userauthservice.user.dto.request.user.UpdateUserRequestDTO;
import com.test.userauthservice.user.dto.response.PageResponse;
import com.test.userauthservice.user.dto.response.user.GetDeletedUserResponseDTO;
import com.test.userauthservice.user.dto.response.user.GetUserResponseDTO;
import com.test.userauthservice.user.entity.Users;
import org.springframework.data.domain.Page;

import java.util.List;

public class UserMapper {

    private static GetUserResponseDTO mapGetUserResponseDTO(Users user) {
        return  new GetUserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getEmailVerified(),
                user.getPhoneVerified(),
                user.getProfileImageUrl(),
                user.getRole() != null ? user.getRole().getId() : null,
                user.getRole() != null ? user.getRole().getName().name() : null,
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    public static List<GetUserResponseDTO> toGetUserResponseDTO(List<Users> user) {
        if (user == null) {
            return null;
        }

        return user.stream()
                .map(UserMapper::mapGetUserResponseDTO)
                .toList();
    }

    public static GetUserResponseDTO toGetUserResponseDTO(Users user) {
        if (user == null) {
            return null;
        }

        return mapGetUserResponseDTO(user);
    }

    private static GetDeletedUserResponseDTO mapGetDeletedUserResponseDTO(Users user) {
        return new GetDeletedUserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getStatus(),
                user.getEmailVerified(),
                user.getPhoneVerified(),
                user.getProfileImageUrl(),
                user.getRole() != null ? user.getRole().getId() : null,
                user.getRole() != null ? user.getRole().getName().name() : null,
                user.getCreatedAt(),
                user.getUpdatedAt(),
                user.getDeletedAt()
        );
    }
    public static List<GetDeletedUserResponseDTO> toGetDeletedUserResponseDTO(List<Users> user) {
        if (user == null) {
            return null;
        }

        return user.stream()
                .map(UserMapper::mapGetDeletedUserResponseDTO)
                .toList();
    }

    public static GetDeletedUserResponseDTO toGetDeletedUserResponseDTO(Users user) {
        if (user == null) {
            return null;
        }

        return mapGetDeletedUserResponseDTO(user);
    }

    public static Users updateUserFromDTO(UpdateUserRequestDTO updateUserRequestDTO, Users user) {
        if (updateUserRequestDTO == null || user == null) {
            return user;
        }

        if (updateUserRequestDTO.firstName() != null && !updateUserRequestDTO.firstName().isBlank()) {
            user.setFirstName(updateUserRequestDTO.firstName());
        }

        if (updateUserRequestDTO.lastName() != null && !updateUserRequestDTO.lastName().isBlank()) {
            user.setLastName(updateUserRequestDTO.lastName());
        }

        if (updateUserRequestDTO.phoneNumber() != null && !updateUserRequestDTO.phoneNumber().isBlank()) {
            user.setPhoneNumber(updateUserRequestDTO.phoneNumber());
        }

        if (updateUserRequestDTO.profileImageUrl() != null && !updateUserRequestDTO.profileImageUrl().isBlank()) {
            user.setProfileImageUrl(updateUserRequestDTO.profileImageUrl());
        }

        return user;
    }

    public static <T> PageResponse<T> toPageResponse(List<T> content, Page<?> page) {
        return PageResponse.<T>builder()
                .content(content)
                .pageNumber(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .numberOfElements(page.getNumberOfElements())
                .first(page.isFirst())
                .last(page.isLast())
                .build();
    }
}
