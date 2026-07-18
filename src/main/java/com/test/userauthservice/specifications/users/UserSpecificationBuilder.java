package com.test.userauthservice.specifications.users;

import com.test.userauthservice.dto.request.user.SearchUserRequestDTO;
import com.test.userauthservice.entity.Users;
import org.springframework.data.jpa.domain.Specification;

public final class UserSpecificationBuilder {
    private UserSpecificationBuilder() {}

    public static Specification<Users> buildActive(SearchUserRequestDTO searchUserRequestDTO) {
        return applyCommonFilters(
                Specification.where(UserSpecification.isNotDeleted()),
                searchUserRequestDTO
        );
    }

    public static Specification<Users> buildDeleted(SearchUserRequestDTO searchUserRequestDTO) {
        return applyCommonFilters(
                Specification.where(UserSpecification.isDeleted()),
                searchUserRequestDTO
        );
    }

    private static Specification<Users> applyCommonFilters(Specification<Users> specification, SearchUserRequestDTO searchUserRequestDTO) {
        if(searchUserRequestDTO.keyword() != null && !searchUserRequestDTO.keyword().isBlank()){
            specification = specification.and(
                    UserSpecification.hasKeyword(searchUserRequestDTO.keyword())
            );
        }

        if(searchUserRequestDTO.phoneNumber() != null && !searchUserRequestDTO.phoneNumber().isBlank()){
            specification = specification.and(
                    UserSpecification.hasPhoneNumber(searchUserRequestDTO.phoneNumber())
            );
        }

        if(searchUserRequestDTO.status() != null){
            specification = specification.and(
                    UserSpecification.hasStatus(searchUserRequestDTO.status())
            );
        }

        if(searchUserRequestDTO.roleId() != null){
            specification = specification.and(
                    UserSpecification.hasRole(searchUserRequestDTO.roleId())
            );
        }

        if(searchUserRequestDTO.emailVerified() != null){
            specification = specification.and(
                    UserSpecification.hasEmailVerified(searchUserRequestDTO.emailVerified())
            );
        }

        if(searchUserRequestDTO.phoneVerified() != null){
            specification = specification.and(
                    UserSpecification.hasPhoneVerified(searchUserRequestDTO.phoneVerified())
            );
        }
        return specification;
    }
}
