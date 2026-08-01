package com.test.userauthservice.common.utils.ENUMS;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "Available user sorting fields")
@Getter
public enum UserSortField {
    ID("id"),
    FIRST_NAME("firstName"),
    LAST_NAME("lastName"),
    EMAIL("email"),
    PHONE_NUMBER("phoneNumber"),
    STATUS("status"),
    CREATED_AT("createdAt"),
    UPDATED_AT("updatedAt");

    private final String userSortValue;

    UserSortField(String userSortValue) {
        this.userSortValue = userSortValue;
    }
}
