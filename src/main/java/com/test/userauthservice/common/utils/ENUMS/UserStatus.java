package com.test.userauthservice.common.utils.ENUMS;

import lombok.Getter;

@Getter
public enum UserStatus {

    ACTIVE("ACTIVE"),
    INACTIVE("INACTIVE"),
    BLOCKED("BLOCKED");

    private final String value;

    UserStatus(String value) {
        this.value = value;
    }
}