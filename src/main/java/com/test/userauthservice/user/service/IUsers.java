package com.test.userauthservice.user.service;

import com.test.userauthservice.user.dto.ApiResponse;
import com.test.userauthservice.user.dto.request.user.SearchUserRequestDTO;
import com.test.userauthservice.user.dto.request.user.UpdateUserRequestDTO;
import com.test.userauthservice.user.dto.response.PageResponse;
import com.test.userauthservice.user.dto.response.user.GetDeletedUserResponseDTO;
import com.test.userauthservice.user.dto.response.user.GetUserResponseDTO;
import com.test.userauthservice.common.utils.ENUMS.SortDirection;
import com.test.userauthservice.common.utils.ENUMS.UserSortField;

public interface IUsers {
    ApiResponse<PageResponse<GetUserResponseDTO>> getAllUsers(SearchUserRequestDTO searchUserRequestDTO, int pageNumber, int size, UserSortField sortBy, SortDirection sortDirection);
    ApiResponse<GetUserResponseDTO> getUserById(Long userId);
    ApiResponse<Long> removeUserById(Long userId);
    ApiResponse<Long> softRemoveUserById(Long userId);
    ApiResponse<Long> restoreUserById(Long userId);
    ApiResponse<PageResponse<GetDeletedUserResponseDTO>> getDeletedUsers(SearchUserRequestDTO searchUserRequestDTO,int pageNumber, int size, UserSortField sortBy, SortDirection sortDirection);
    ApiResponse<GetDeletedUserResponseDTO> getDeletedUserById(Long userId);
    ApiResponse<GetUserResponseDTO> updateUser(Long userId, UpdateUserRequestDTO updateUserRequestDTO);
}
