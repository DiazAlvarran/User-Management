package com.nisum.users.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nisum.users.dto.ErrorResponse;
import com.nisum.users.exception.AuthenticationException;
import com.nisum.users.exception.DataValidationException;

/**
 * Class for handling exceptions and giving custom responses
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
@ControllerAdvice
public class ExceptionTranslator {

  /**
   * handles exception of type DataValidationException
   * 
   * @param e data validation exception
   * @return ResponseEntity<ErrorResponse> custom error response
   */
  @ExceptionHandler(DataValidationException.class)
  public ResponseEntity<ErrorResponse> handleDataValidationException(DataValidationException e) {
    return new ResponseEntity<ErrorResponse>(generateErrorResponse(e.getMessage()), e.getStatus());
  }
  
  /**
   * handles exception of type AuthenticationException
   * 
   * @param e authentication exception
   * @return ResponseEntity<ErrorResponse> custom error response
   */
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
    return new ResponseEntity<ErrorResponse>(generateErrorResponse(e.getMessage()), e.getStatus());
  }
  
  /**
   * Generate the Error class with the custom message
   * 
   * @param message data validation exception
   * @return ErrorResponse error with custom message
   */
  private ErrorResponse generateErrorResponse(String message) {
    return ErrorResponse.builder()
        .message(message)
        .build();
  }
  
}
