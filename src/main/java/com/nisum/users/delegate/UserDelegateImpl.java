package com.nisum.users.delegate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nisum.users.api.UserApiDelegate;
import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.service.UserService;

@Service
public class UserDelegateImpl implements UserApiDelegate {

  private final UserService userService;
  
  public UserDelegateImpl(UserService userService) {
    this.userService = userService;
  }
  
  @Override
  public ResponseEntity<UserResponse> saveUser(UserRequest userRequest) {
    return new ResponseEntity<UserResponse>(userService.saveUser(userRequest), HttpStatus.CREATED);
  }
  
}
