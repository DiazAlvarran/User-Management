package com.nisum.users.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nisum.users.config.ApplicationProperties;
import com.nisum.users.models.User;

@ExtendWith(MockitoExtension.class)
public class JwtTokenServiceImplTest {
  
  @Spy
  ApplicationProperties properties;
  
  @InjectMocks
  JwtTokenServiceImpl jwtTokenServiceImpl;

  User user;
  
  @BeforeEach
  void setUp() {
    user = User.builder()
        .name("Jorge Diaz")
        .build();
    
    properties.setExpireJwt(300000);
    properties.setSecretJwt("9aeb8f4e-5c43-4764-a802-cfbc9be49db5");
  }
  
  @Test
  @DisplayName("Generate token when is successful")
  void generateTokenWhenIsSuccessful() {
    String jwt = jwtTokenServiceImpl.generateToken(user);
    
    assertNotNull(jwt);
    assertTrue(!jwt.isEmpty());
  }
  
  @Test
  @DisplayName("Get authentication when is successful")
  void GetAuthenticationWhenIsSuccessful() {
    assertNotNull(jwtTokenServiceImpl.getAuthentication(jwtTokenServiceImpl.generateToken(user)));
  }

}
