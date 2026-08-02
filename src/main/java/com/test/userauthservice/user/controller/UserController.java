package com.test.userauthservice.user.controller;

import com.test.userauthservice.auth.dto.response.UserSummaryResponse;
import com.test.userauthservice.common.dto.ApiResponse;
import com.test.userauthservice.user.dto.request.user.SearchUserRequestDTO;
import com.test.userauthservice.user.dto.request.user.UpdateUserRequestDTO;
import com.test.userauthservice.common.dto.PageResponse;
import com.test.userauthservice.user.dto.response.user.GetDeletedUserResponseDTO;
import com.test.userauthservice.user.dto.response.user.GetUserResponseDTO;
import com.test.userauthservice.user.service.impl.UserServiceImpl;
import com.test.userauthservice.common.utils.ENUMS.SortDirection;
import com.test.userauthservice.common.utils.ENUMS.UserSortField;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Validated
@Tag(
        name = "User Management",
        description = "APIs for managing user accounts"
)
public class UserController {

    private final UserServiceImpl usersService;

    @Operation(
            summary = "Get all users",
            description = "Returns a paginated list of users with sorting support"
    )
    @GetMapping("/get-all-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PageResponse<GetUserResponseDTO>>> getAllUsers(
            @ModelAttribute SearchUserRequestDTO searchUserRequestDTO,
            @RequestParam(defaultValue = "0") @PositiveOrZero(message = "Page number cannot be negative") int pageNumber,
            @RequestParam(defaultValue = "10") @Positive(message = "Size must be greater than 0") int size,
            @RequestParam(defaultValue = "ID") UserSortField sortBy,
            @RequestParam(defaultValue = "ASC") SortDirection sortDirection
    ){
        return ResponseEntity.ok(usersService.getAllUsers(searchUserRequestDTO,pageNumber,size,sortBy,sortDirection));
    }

    @Operation(
            summary = "Get user by ID",
            description = "Returns a single user using its unique identifier"
    )
    @GetMapping("/get-user-by-id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<GetUserResponseDTO>> getUserById(
            @PathVariable @Positive(message = "User ID must be greater than 0") Long userId
    ){
        return ResponseEntity.ok(usersService.getUserById(userId));
    }

    @Operation(
            summary = "Delete a user",
            description = "Permanently deletes a user using user ID"
    )
    @DeleteMapping("/remove-user/{userId}/permanent")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Long>> removeUserById(
            @PathVariable @Positive(message = "User ID must be greater than 0") Long userId
    ) {
        return ResponseEntity.ok(usersService.removeUserById(userId));
    }

    @Operation(
            summary = "Soft delete a user",
            description = "Soft deletes a user using user ID"
    )
    @DeleteMapping("/remove-user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Long>> softRemoveUserById(
            @PathVariable @Positive(message = "User ID must be greater than 0") Long userId
    ) {
        return ResponseEntity.ok(usersService.softRemoveUserById(userId));
    }

    @Operation(
            summary = "Restore a user",
            description = "Restores a soft deleted user using user ID"
    )
    @PatchMapping("/restore-user/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Long>> restoreUserById(
            @PathVariable @Positive(message = "User ID must be greater than 0") Long userId
    ) {
        return ResponseEntity.ok(usersService.restoreUserById(userId));
    }

    @Operation(
            summary = "Get all deleted users",
            description = "Returns a paginated list of deleted users with sorting support"
    )
    @GetMapping("/get-deleted-users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<PageResponse<GetDeletedUserResponseDTO>>> getDeletedUsers(
            @ModelAttribute SearchUserRequestDTO searchUserRequestDTO,
            @RequestParam(defaultValue = "0") @PositiveOrZero(message = "Page number cannot be negative") int pageNumber,
            @RequestParam(defaultValue = "10") @Positive(message = "Size must be greater than 0") int size,
            @RequestParam(defaultValue = "ID") UserSortField sortBy,
            @RequestParam(defaultValue = "ASC") SortDirection sortDirection
    ) {
        return ResponseEntity.ok(usersService.getDeletedUsers(searchUserRequestDTO,pageNumber, size, sortBy, sortDirection));
    }

    @Operation(
            summary = "Get deleted user by ID",
            description = "Returns a single deleted user using its unique identifier"
    )
    @GetMapping("/get-deleted-user-by-id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<GetDeletedUserResponseDTO>> getDeletedUserById(
            @PathVariable @Positive(message = "User ID must be greater than 0") Long userId
    ) {
        return ResponseEntity.ok(usersService.getDeletedUserById(userId));
    }

    @Operation(
            summary = "Update user",
            description = "Updates an existing user using the user ID"
    )
    @PatchMapping("/update-user-by-id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<GetUserResponseDTO>> updateUser(
            @PathVariable @Positive(message = "User ID must be greater than 0") Long userId,
            @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO
    ) {
        return ResponseEntity.ok(usersService.updateUser(userId, updateUserRequestDTO));
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserSummaryResponse>> getCurrentUser() {
        return ResponseEntity.ok(usersService.getCurrentUser());
    }

    @PatchMapping("/me")
    public ResponseEntity<ApiResponse<GetUserResponseDTO>> updateCurrentUser(@Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        return ResponseEntity.ok(usersService.UpdateCurrentUser(updateUserRequestDTO));
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Long>> deleteUser() {
        return ResponseEntity.ok(usersService.deleteUser());
    }
}
