package com.test.userauthservice.common.utils;

import com.test.userauthservice.auth.security.CustomUserDetails;
import com.test.userauthservice.user.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SecurityUtils {

    public Users getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.debug("Authentication object is {}", authentication);
        CustomUserDetails customUserDetails = authentication !=null ?(CustomUserDetails) authentication.getPrincipal() : null;
        log.debug("CustomUserDetails object is {}", customUserDetails);
        return customUserDetails != null ? customUserDetails.getUser() : null;
    }
}
