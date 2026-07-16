package com.test.userauthservice.error_handling.custom_exception;

import lombok.Getter;

@Getter
public class DuplicateResourceException extends RuntimeException {
    private final String errorMsg;

    public DuplicateResourceException(String message, String errorMsg) {
        super(message);
        this.errorMsg = errorMsg;
    }
}
