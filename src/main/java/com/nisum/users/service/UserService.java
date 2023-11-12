package com.nisum.users.service;

import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.models.User;

public interface UserService {
  
  UserResponse saveUser(String Authorization, UserRequest userRequest);
  
  User updateUserSession(User user, String token);

}
