package com.nisum.users.service;

import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;

public interface UserService {
  
  UserResponse saveUser(UserRequest userRequest);

}
