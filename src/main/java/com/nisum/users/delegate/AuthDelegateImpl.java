package com.nisum.users.delegate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nisum.users.api.AuthApiDelegate;
import com.nisum.users.dto.AuthRequest;
import com.nisum.users.dto.JwtResponse;
import com.nisum.users.models.User;
import com.nisum.users.service.AuthService;
import com.nisum.users.service.JwtTokenService;

@Service
public class AuthDelegateImpl implements AuthApiDelegate {

  private AuthService authService;
  private JwtTokenService jwtTokenService;
  
  public AuthDelegateImpl(AuthService authService, JwtTokenService jwtTokenService) {
    this.authService = authService;
    this.jwtTokenService = jwtTokenService;
  }

  @Override
  public ResponseEntity<JwtResponse> authenticate(AuthRequest authRequest) {
    User user = authService.getUser(authRequest);
    JwtResponse response = JwtResponse.builder()
        .jwt(jwtTokenService.generateToken(user))
        .build();
    return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
  }

}
