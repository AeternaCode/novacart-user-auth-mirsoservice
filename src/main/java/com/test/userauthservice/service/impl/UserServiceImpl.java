package com.test.userauthservice.service.impl;

import com.test.userauthservice.config.PaginationProperties;
import com.test.userauthservice.dto.ApiResponse;
import com.test.userauthservice.dto.request.user.SearchUserRequestDTO;
import com.test.userauthservice.dto.request.user.UpdateUserRequestDTO;
import com.test.userauthservice.dto.response.PageResponse;
import com.test.userauthservice.dto.response.user.GetDeletedUserResponseDTO;
import com.test.userauthservice.dto.response.user.GetUserResponseDTO;
import com.test.userauthservice.entity.Users;
import com.test.userauthservice.mapper.UserMapper;
import com.test.userauthservice.repository.UserRepo;
import com.test.userauthservice.service.IUsers;
import com.test.userauthservice.specifications.Users.UserSpecificationBuilder;
import com.test.userauthservice.utils.ENUMS.SortDirection;
import com.test.userauthservice.utils.ENUMS.UserSortField;
import com.test.userauthservice.utils.VerifyResource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUsers {
    private final UserRepo usersRepo;
    private final VerifyResource verifyResource;
    private final PaginationProperties paginationProperties;

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<PageResponse<GetUserResponseDTO>> getAllUsers(SearchUserRequestDTO searchUserRequestDTO, int pageNumber, int size, UserSortField sortBy, SortDirection sortDirection) {
        // Creating Sort
        Sort sort = sortDirection == SortDirection.DESC ?
                Sort.by(sortBy.getUserSortValue()).descending()
                :
                Sort.by(sortBy.getUserSortValue()).ascending();

        int pageSize =  Math.min(size, paginationProperties.maxPageSize());
        if(size < paginationProperties.defaultPageSize()) pageSize = paginationProperties.defaultPageSize();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Fetch only non-deleted users
        Specification<Users> spec = UserSpecificationBuilder.buildActive(searchUserRequestDTO);

        Page<Users> usersPage = usersRepo.findAll(spec, pageable);
        List<Users> users = usersPage.getContent();

        List<GetUserResponseDTO> listDto = UserMapper.toGetUserResponseDTO(users);

        log.info("List of Users : {}", listDto);
        return ApiResponse.<PageResponse<GetUserResponseDTO>>builder()
                .success(true)
                .message("Users fetched successfully")
                .data(UserMapper.toPageResponse(listDto, usersPage))
                .build();
    }


    @Override
    @Transactional(readOnly = true)
    public ApiResponse<GetUserResponseDTO> getUserById(Long userId) {
        Users user = verifyResource.verifyOrGetUserById(userId);
        log.info("Successfully Get user with id : {}", user.getId());
        return ApiResponse.<GetUserResponseDTO>builder()
                .success(true)
                .message("User fetched successfully with id : " + user.getId())
                .data(UserMapper.toGetUserResponseDTO(user))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Long> removeUserById(Long userId) {
        Users user = verifyResource.verifyOrGetUserById(userId);
        log.info("Deleting user with id {}", userId);
        usersRepo.deleteById(userId);
        return ApiResponse.<Long>builder()
                .success(true)
                .message("User removed successfully with id : " + user.getId())
                .data(null)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Long> softRemoveUserById(Long userId) {
        Users user = verifyResource.verifyOrGetUserById(userId);
        log.info("Soft Deleting user with id {}", userId);
        user.setDeletedAt(LocalDateTime.now());
        usersRepo.save(user);
        return ApiResponse.<Long>builder()
                .success(true)
                .message("User removed successfully with id : " + user.getId())
                .data(null)
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<Long> restoreUserById(Long userId) {
        Users user = verifyResource.verifyOrGetDeletedUserById(userId);
        log.info("Restore user with id {}", userId);
        user.setDeletedAt(null);
        usersRepo.save(user);
        return ApiResponse.<Long>builder()
                .success(true)
                .message("User restored successfully with id : " + user.getId())
                .data(null)
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<PageResponse<GetDeletedUserResponseDTO>> getDeletedUsers(SearchUserRequestDTO searchUserRequestDTO,int pageNumber, int size, UserSortField sortBy, SortDirection sortDirection) {
        // Creating Sort
        Sort sort = sortDirection == SortDirection.DESC ?
                Sort.by(sortBy.getUserSortValue()).descending()
                :
                Sort.by(sortBy.getUserSortValue()).ascending();

        int pageSize =  Math.min(size, paginationProperties.maxPageSize());
        if(size < paginationProperties.defaultPageSize()) pageSize = paginationProperties.defaultPageSize();

        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);

        // Fetch only non-deleted users
        Specification<Users> spec = UserSpecificationBuilder.buildDeleted(searchUserRequestDTO);

        Page<Users> usersPage = usersRepo.findAll(spec, pageable);
        List<Users> users = usersPage.getContent();

        List<GetDeletedUserResponseDTO> listDto = UserMapper.toGetDeletedUserResponseDTO(users);

        log.info("List of deleted Users : {}", listDto);
        return ApiResponse.<PageResponse<GetDeletedUserResponseDTO>>builder()
                .success(true)
                .message("Deleted users fetched successfully")
                .data(UserMapper.toPageResponse(listDto, usersPage))
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<GetDeletedUserResponseDTO> getDeletedUserById(Long userId) {
        Users user = verifyResource.verifyOrGetDeletedUserById(userId);
        log.info("Successfully Get deleted user with id : {}", user.getId());
        return ApiResponse.<GetDeletedUserResponseDTO>builder()
                .success(true)
                .message("Deleted user fetched successfully with id : " + user.getId())
                .data(UserMapper.toGetDeletedUserResponseDTO(user))
                .build();
    }

    @Override
    @Transactional
    public ApiResponse<GetUserResponseDTO> updateUser(Long userId, UpdateUserRequestDTO updateUserRequestDTO) {
        Users user = verifyResource.verifyOrGetUserById(userId);
        log.info("Updating user with id {}", userId);

        user = UserMapper.updateUserFromDTO(updateUserRequestDTO, user);

        usersRepo.save(user);
        log.info("User updated successfully. UserId={}", user.getId());
        return ApiResponse.<GetUserResponseDTO>builder()
                .success(true)
                .message("User update successfully with id : " + user.getId())
                .data(UserMapper.toGetUserResponseDTO(user))
                .build();
    }
}
