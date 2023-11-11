package com.nisum.users.config;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nisum.users.dto.ErrorResponse;
import com.nisum.users.exception.DataValidationException;

@ControllerAdvice
public class ExceptionTranslator {

  @ExceptionHandler(DataValidationException.class)
  public ResponseEntity<ErrorResponse> handleEmailValidationException(DataValidationException e) {
    return new ResponseEntity<ErrorResponse>(generateErrorResponse(e.getMessage()), e.getStatus());
  }
  
  private ErrorResponse generateErrorResponse(String message) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setMessage(message);
    return errorResponse;
  }
  
}
