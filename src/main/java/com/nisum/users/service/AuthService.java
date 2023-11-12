package com.nisum.users.service;

import com.nisum.users.dto.AuthRequest;
import com.nisum.users.models.User;

/**
 * Defines methods for authentication management
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
public interface AuthService {
  
  /**
   * get a user by validating their credentials
   * 
   * @param authRequest contains user credentials
   * @return User authenticated user
   */
  User getUser(AuthRequest authRequest);

}
