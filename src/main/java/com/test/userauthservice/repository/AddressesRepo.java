package com.test.userauthservice.repository;

import com.test.userauthservice.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AddressesRepo extends JpaRepository<Address,Long>, JpaSpecificationExecutor<Address> {

}
