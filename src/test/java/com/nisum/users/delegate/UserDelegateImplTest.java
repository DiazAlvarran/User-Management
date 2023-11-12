package com.nisum.users.delegate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.service.UserService;

@ExtendWith(MockitoExtension.class)
public class UserDelegateImplTest {

  @Mock
  UserService userService;
  
  @InjectMocks
  UserDelegateImpl userDelegateImpl;
  
  String authorization = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJNYXJ0aW4gR2FyY2lhIiwicm9sZSI6IlVTRVIiLCJleHAiOjE2OTk3NTMzMDksImlhdCI6MTY5OTc1MzAwOX0.01eHoATkfmi_tOmiXGNQI9Qr-zZhRn_xOp_GeextaCvxyU9RwKxYoVH62PIi5HQFKGPZH5qhOjY_OVxv_4EmZA";
  
  @Test
  @DisplayName("Save user when is successful")
  void SaveUserWhenIsSuccessful() {
    when(userService.saveUser(anyString(), any(UserRequest.class))).thenReturn(new UserResponse());
    
    ResponseEntity<UserResponse> response = userDelegateImpl.saveUser(authorization, new UserRequest());
    
    assertNotNull(response.getBody());
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
  }
  
}
