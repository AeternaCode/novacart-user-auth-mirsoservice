package com.test.userauthservice.user.repository;

import com.test.userauthservice.user.entity.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {
    @EntityGraph(attributePaths = "role")
    Optional<Users> findByEmailAndDeletedAtIsNull(String email);
    Optional<Users> findByIdAndDeletedAtIsNull(Long id);
    Optional<Users> findByIdAndDeletedAtIsNotNull(Long id);
    Boolean existsByEmail(String email);
    Boolean existsByPhoneNumber(String phoneNumber);
}
