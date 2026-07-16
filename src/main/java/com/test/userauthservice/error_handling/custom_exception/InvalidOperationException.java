package com.test.userauthservice.error_handling.custom_exception;

import lombok.Getter;

@Getter
public class InvalidOperationException extends RuntimeException {
    private final String errorMsg;

    public InvalidOperationException(String message, String errorMsg) {
        super(message);
        this.errorMsg = errorMsg;
    }
}
