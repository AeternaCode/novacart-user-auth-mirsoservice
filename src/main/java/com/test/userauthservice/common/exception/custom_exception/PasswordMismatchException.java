package com.test.userauthservice.common.exception.custom_exception;

import lombok.Getter;

@Getter
public class PasswordMismatchException extends RuntimeException {
    private final String errorMsg;

    public PasswordMismatchException(String message, String errorMsg) {
        super(message);
        this.errorMsg = errorMsg;
    }
}
