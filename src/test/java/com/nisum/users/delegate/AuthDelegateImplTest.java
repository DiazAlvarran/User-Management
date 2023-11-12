package com.nisum.users.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.users.dto.AuthRequest;
import com.nisum.users.dto.JwtResponse;
import com.nisum.users.models.User;
import com.nisum.users.service.AuthService;
import com.nisum.users.service.JwtTokenService;
import com.nisum.users.service.UserService;

@ExtendWith(MockitoExtension.class)
public class AuthDelegateImplTest {
  
  @Mock
  AuthService authService;
  @Mock
  JwtTokenService jwtTokenService;
  @Mock
  UserService userService;
  
  @InjectMocks
  AuthDelegateImpl authDelegateImpl;
  
  AuthRequest authRequest;
  String jwt = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA";
  
  @BeforeEach
  void setUp() {
    authRequest = AuthRequest.builder()
        .email("martingarcia@espn.cl")
        .password("martinG_1234")
        .build();
  }
  
  @Test
  @DisplayName("Execute authenticate when is successful")
  void executeAuthenticateWhenIsSuccessful() {
    when(authService.getUser(any(AuthRequest.class))).thenReturn(new User());
    when(jwtTokenService.generateToken(any(User.class))).thenReturn(jwt);
    when(userService.updateUserSession(any(User.class), anyString())).thenReturn(new User());
    
    ResponseEntity<JwtResponse> response = authDelegateImpl.authenticate(authRequest);
    
    assertEquals(jwt, response.getBody().getJwt());
    assertEquals(HttpStatus.OK, response.getStatusCode());
  }

}
