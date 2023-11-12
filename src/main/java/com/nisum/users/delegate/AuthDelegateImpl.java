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
import com.nisum.users.service.UserService;

/**
 * Class that implements AuthApiDelegate for authentication
 * 
 * @author Jorge Diaz
 * @version 1.0
 */
@Service
public class AuthDelegateImpl implements AuthApiDelegate {

  private AuthService authService;
  private JwtTokenService jwtTokenService;
  private UserService userService;
  
  /**
   * constructor with all arguments of the AuthDelegateImpl class
   * 
   * @param authService implements user authentication
   * @param jwtTokenService implements jwt token generation and validation
   * @param userService implements user management
   */
  public AuthDelegateImpl(AuthService authService, JwtTokenService jwtTokenService, UserService userService) {
    this.authService = authService;
    this.jwtTokenService = jwtTokenService;
    this.userService = userService;
  }

  /**
   * implements user authentication
   * 
   * @param authRequest contains user credentials
   * @return ResponseEntity<JwtResponse> JWT generated in session
   */
  @Override
  public ResponseEntity<JwtResponse> authenticate(AuthRequest authRequest) {
    User user = authService.getUser(authRequest);
    String jwt = jwtTokenService.generateToken(user);
    userService.updateUserSession(user, jwt);
    JwtResponse response = JwtResponse.builder()
        .jwt(jwt)
        .build();
    return new ResponseEntity<JwtResponse>(response, HttpStatus.OK);
  }

}
