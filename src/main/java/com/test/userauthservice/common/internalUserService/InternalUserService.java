package com.test.userauthservice.common.internalUserService;

import com.test.userauthservice.user.entity.Users;

public interface InternalUserService {
    Users createUserForAuthentication(Users user);
}
