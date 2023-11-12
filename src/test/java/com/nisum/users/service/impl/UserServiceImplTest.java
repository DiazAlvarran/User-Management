package com.nisum.users.service.impl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.nisum.users.config.ApplicationProperties;
import com.nisum.users.dto.PhoneRequest;
import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.exception.DataValidationException;
import com.nisum.users.models.User;
import com.nisum.users.service.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

  @Spy
  ApplicationProperties properties;
  
  @Mock
  UserRepository userRepository;
  
  @InjectMocks
  UserServiceImpl userServiceImpl;
  
  String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA";
  
  UserRequest userRequest;
  User user;
  String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA";
  
  @BeforeEach
  void setUp() {
    userRequest = UserRequest.builder()
        .name("Juan Rodriguez")
        .email("juanrodriguez1234@nisum.cl")
        .password("Juan1234.")
        .phones(List.of(PhoneRequest.builder()
            .number("123456789")
            .cityCode("1")
            .countryCode("57")
            .build()))
        .build();
    
    user = User.builder()
        .id("709b3333-5b7e-4de2-97e1-8143f8a487e3")
        .created(new Date())
        .modified(null)
        .lastLogin(new Date())
        .token("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA")
        .isActive(true)
        .build();
    
    properties.setEmailRegex("^[a-zA-Z0-9]+[\\w\\-\\.]*@[a-zA-Z0-9]+[\\w\\-]*[\\.]cl$");
    properties.setPasswordRegex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&\\.\\-\\_])[A-Za-z\\d@$!%?&\\.\\-\\_]{8,20}$");
  }
  
  @Test
  @DisplayName("Save user when is successful")
  void saveUserWhenIsSuccessful() {
    when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.empty());
    when(userRepository.save(any(User.class))).thenReturn(user);
    
    UserResponse userResponse = userServiceImpl.saveUser(authorization, userRequest);
    assertNotNull(userResponse);
  }
  
  @Test
  @DisplayName("Return exception when email is invalid")
  void returnExceptionWhenEmailIsInvalid() {
    userRequest.setEmail("martingarcia@_.pe");
    
    assertThrows(DataValidationException.class, () -> userServiceImpl.saveUser(authorization, userRequest));
  }
  
  @Test
  @DisplayName("Return exception when password is invalid")
  void returnExceptionWhenPasswordIsInvalid() {
    userRequest.setPassword("martin1234");
    
    assertThrows(DataValidationException.class, () -> userServiceImpl.saveUser(authorization, userRequest));
  }
  
  @Test
  @DisplayName("Return exception when email already exists")
  void returnExceptionWhenEmailAlreadyExists() {
    when(userRepository.findUserByEmail(anyString())).thenReturn(Optional.of(new User()));
    
    assertThrows(DataValidationException.class, () -> userServiceImpl.saveUser(authorization, userRequest));
  }
  
  @Test
  @DisplayName("Update user when is successful")
  void updateUserWhenIsSuccessful() {
    when(userRepository.save(any(User.class))).thenReturn(user);
    
    User userResponse = userServiceImpl.updateUserSession(user, token);
    assertNotNull(userResponse);
  }
  
}
