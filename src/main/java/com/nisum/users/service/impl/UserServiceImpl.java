package com.nisum.users.service.impl;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nisum.users.config.ApplicationProperties;
import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.exception.DataValidationException;
import com.nisum.users.models.Phone;
import com.nisum.users.models.User;
import com.nisum.users.service.UserService;
import com.nisum.users.service.repository.UserRepository;
import com.nisum.users.util.Constants;
import com.nisum.users.util.Utils;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private ApplicationProperties properties;
  
  public UserServiceImpl(UserRepository userRepository, ApplicationProperties properties) {
    this.userRepository = userRepository;
    this.properties = properties;
  }
  
  @Override
  @Transactional
  public UserResponse saveUser(String authorization, UserRequest userRequest) {
    if (Utils.isInvalidString(properties.getEmailRegex(), userRequest.getEmail())) {
      throw new DataValidationException(Constants.INVALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
    }
    if (Utils.isInvalidString(properties.getPasswordRegex(), userRequest.getPassword())) {
      throw new DataValidationException(Constants.INVALID_PASSWORD_MESSAGE, HttpStatus.BAD_REQUEST);
    }
    if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
      throw new DataValidationException(Constants.EMAIL_CONFLICT_MESSAGE, HttpStatus.CONFLICT);
    }
    return mapUserToUserResponse(userRepository.save(mapUserRequestToUser(authorization, userRequest)));
  }
  
  private User mapUserRequestToUser(String authorization, UserRequest userRequest) {
    Date now = new Date();
    return User.builder()
        .id(Utils.getNewUuid())
        .name(userRequest.getName())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .created(now)
        .lastLogin(now)
        .token(authorization)
        .isActive(Boolean.TRUE)
        .phones(userRequest.getPhones().stream()
            .map(phoneRequest -> Phone.builder()
                .id(Utils.getNewUuid())
                .number(phoneRequest.getNumber())
                .cityCode(phoneRequest.getCityCode())
                .countryCode(phoneRequest.getCountryCode())
                .created(now)
                .build())
            .toList())
        .build();
  }
  
  private UserResponse mapUserToUserResponse(User user) {
    return UserResponse.builder()
    .id(user.getId())
    .created(Utils.convertDateToFormattedString(user.getCreated(), Constants.FORMAT_YYYY_MM_DD))
    .modified(Utils.convertDateToFormattedString(user.getModified(), Constants.FORMAT_YYYY_MM_DD))
    .lastLogin(Utils.convertDateToFormattedString(user.getLastLogin(), Constants.FORMAT_YYYY_MM_DD))
    .token(user.getToken())
    .isActive(user.getIsActive())
    .build();
  }

  @Override
  public User updateUserSession(User user, String token) {
    user.setLastLogin(new Date());
    user.setToken(Constants.PREFIX_BEARER.concat(token));
    return userRepository.save(user);
  }
  
}
