package com.nisum.users.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * class for custom exception when data validation fails
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
@Getter
public class DataValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;
  private HttpStatus status;

  /**
   * constructor to assign a custom message and status
   * 
   * @param message custom exception message
   * @param status error status according to HttpStatus class
   */
  public DataValidationException(String message, HttpStatus status) {
    super(message);
    this.status = status;
  }
  
}
