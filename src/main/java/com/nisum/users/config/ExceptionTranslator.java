package com.nisum.users.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nisum.users.dto.ErrorResponse;
import com.nisum.users.exception.AuthenticationException;
import com.nisum.users.exception.DataValidationException;

@ControllerAdvice
public class ExceptionTranslator {

  @ExceptionHandler(DataValidationException.class)
  public ResponseEntity<ErrorResponse> handleDataValidationException(DataValidationException e) {
    return new ResponseEntity<ErrorResponse>(generateErrorResponse(e.getMessage()), e.getStatus());
  }
  
  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
    return new ResponseEntity<ErrorResponse>(generateErrorResponse(e.getMessage()), e.getStatus());
  }
  
  private ErrorResponse generateErrorResponse(String message) {
    return ErrorResponse.builder()
        .message(message)
        .build();
  }
  
}
