package com.nisum.users.service;

import com.nisum.users.dto.AuthRequest;
import com.nisum.users.models.User;

public interface AuthService {
  
  User getUser(AuthRequest authRequest);

}
