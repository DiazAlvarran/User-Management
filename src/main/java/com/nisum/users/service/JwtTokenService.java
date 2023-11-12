package com.nisum.users.service;

import org.springframework.security.core.Authentication;

import com.nisum.users.models.User;

/**
 * Defines methods for JWT management.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
public interface JwtTokenService {

    /**
     * Generates a new token from a user.
     *
     * @param user authenticated user
     * @return String JWT
     */
    String generateToken(User user);

    /**
     * Validate the user JWT.
     *
     * @param token JWT
     * @return Authentication user authentication
     */
    Authentication getAuthentication(String token);

}
