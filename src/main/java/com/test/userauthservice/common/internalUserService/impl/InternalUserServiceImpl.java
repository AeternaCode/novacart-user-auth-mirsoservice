package com.test.userauthservice.common.internalUserService.impl;

import com.test.userauthservice.common.exception.custom_exception.DuplicateResourceException;
import com.test.userauthservice.common.internalUserService.InternalUserService;
import com.test.userauthservice.user.entity.Users;
import com.test.userauthservice.user.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InternalUserServiceImpl implements InternalUserService {

    private final UserRepo userRepo;

    @Override
    @Transactional
    public Users createUserForAuthentication(Users user) {
        if(userRepo.existsByEmail(user.getEmail())) {
            throw new DuplicateResourceException(
                    "User with email " + user.getEmail() + " already exists.",
                    "USER_ALREADY_EXISTS"
            );
        }
        if (userRepo.existsByPhoneNumber(user.getPhoneNumber())) {
            throw new DuplicateResourceException(
                    "User with phone number " + user.getPhoneNumber() + " already exists.",
                    "USER_ALREADY_EXISTS"
            );
        }
        return userRepo.save(user);
    }
}
