package com.test.userauthservice.common.repository;

import com.test.userauthservice.common.entity.Roles;
import com.test.userauthservice.common.utils.ENUMS.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoles extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleType role);
}
