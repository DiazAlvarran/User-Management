package com.nisum.users.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class DataValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private HttpStatus status;

  public DataValidationException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
  
}
