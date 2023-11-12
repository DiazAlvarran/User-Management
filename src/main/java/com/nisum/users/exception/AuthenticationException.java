package com.nisum.users.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

/**
 * Class for custom exception when failed to authenticate successfully.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
@Getter
public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final HttpStatus status;

    /**
     * Constructor to assign a custom message and status.
     *
     * @param message custom exception message
     * @param status  error status according to HttpStatus class
     */
    public AuthenticationException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

}
