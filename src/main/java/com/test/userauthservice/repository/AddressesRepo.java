package com.test.userauthservice.repository;

import com.test.userauthservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface AddressesRepo extends JpaRepository<Address, Long>, JpaSpecificationExecutor<Address> {
    Optional<Address> findByIdAndUserId(Long id, Long userId);
    List<Address> findAllByUserId(Long userId);
}
