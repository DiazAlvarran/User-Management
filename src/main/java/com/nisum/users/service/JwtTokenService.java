package com.nisum.users.service;

import org.springframework.security.core.Authentication;

import com.nisum.users.models.User;

public interface JwtTokenService {

  String generateToken(User user);
  
  Authentication getAuthentication(String token);
  
}
