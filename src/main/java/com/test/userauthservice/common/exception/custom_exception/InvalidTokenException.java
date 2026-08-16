package com.test.userauthservice.common.exception.custom_exception;

import lombok.Getter;

@Getter
public class InvalidTokenException extends RuntimeException {

  private final String errorMsg;

  public InvalidTokenException(String message, String errorMsg) {
    super(message);
    this.errorMsg = errorMsg;
  }
}
