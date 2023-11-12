package com.nisum.users.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nisum.users.dto.AuthRequest;
import com.nisum.users.exception.AuthenticationException;
import com.nisum.users.models.User;
import com.nisum.users.service.AuthService;
import com.nisum.users.service.repository.UserRepository;
import com.nisum.users.util.Constants;

@Service
public class AuthServiceImpl implements AuthService {

  private UserRepository userRepository;
  
  public AuthServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }
  
  @Override
  public User getUser(AuthRequest authRequest) {
    return userRepository.findUserByEmail(authRequest.getEmail())
    .filter(user -> user.getIsActive())
    .filter(user -> authRequest.getPassword().equals(user.getPassword()))
    .orElseThrow(() -> new AuthenticationException(Constants.INVALID_AUTHENTICATION_MESSAGE , HttpStatus.UNAUTHORIZED));
  }

}
