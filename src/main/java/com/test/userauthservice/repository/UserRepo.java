package com.test.userauthservice.repository;

import com.test.userauthservice.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepo extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

}
