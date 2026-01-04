package com.flightontime.api.infra;

public class ValidatorException extends RuntimeException {
  public ValidatorException(String message) {
    super(message);
  }
}
