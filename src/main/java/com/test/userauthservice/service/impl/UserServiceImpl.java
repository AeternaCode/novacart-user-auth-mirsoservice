package com.test.userauthservice.service.impl;

import com.test.userauthservice.dto.ApiResponse;
import com.test.userauthservice.dto.request.user.UpdateUserRequestDTO;
import com.test.userauthservice.dto.response.PageResponse;
import com.test.userauthservice.dto.response.user.GetDeletedUserResponseDTO;
import com.test.userauthservice.dto.response.user.GetUserResponseDTO;
import com.test.userauthservice.repository.UserRepo;
import com.test.userauthservice.service.IUsers;
import com.test.userauthservice.utils.ENUMS.SortDirection;
import com.test.userauthservice.utils.ENUMS.UserSortField;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUsers {
    private final UserRepo usersRepo;

    @Override
    public ApiResponse<PageResponse<GetUserResponseDTO>> getAllUsers(int pageNumber, int size, UserSortField sortBy, SortDirection sortDirection) {
        return null;
    }

    @Override
    public ApiResponse<GetUserResponseDTO> getUserById(Long userId) {
        return null;
    }

    @Override
    public ApiResponse<Long> removeUserById(Long userId) {
        return null;
    }

    @Override
    public ApiResponse<Long> softRemoveUserById(Long userId) {
        return null;
    }

    @Override
    public ApiResponse<Long> restoreUserById(Long userId) {
        return null;
    }

    @Override
    public ApiResponse<PageResponse<GetDeletedUserResponseDTO>> getDeletedUsers(int pageNumber, int size, UserSortField sortBy, SortDirection sortDirection) {
        return null;
    }

    @Override
    public ApiResponse<GetDeletedUserResponseDTO> getDeletedUserById(Long userId) {
        return null;
    }

    @Override
    public ApiResponse<GetUserResponseDTO> updateUser(Long userId, UpdateUserRequestDTO updateUserRequestDTO) {
        return null;
    }
}
