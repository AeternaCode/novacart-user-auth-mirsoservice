package com.test.userauthservice.service.impl;

import com.test.userauthservice.repository.UserRepo;
import com.test.userauthservice.service.IUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUsers {
    private final UserRepo usersRepo;
}
