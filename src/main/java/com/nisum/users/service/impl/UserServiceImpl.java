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
  public UserResponse saveUser(UserRequest userRequest) {
    if (Utils.isInvalidString(properties.getEmailRegex(), userRequest.getEmail())) {
      throw new DataValidationException(Constants.INVALID_EMAIL_MESSAGE, HttpStatus.BAD_REQUEST);
    }
    if (Utils.isInvalidString(properties.getPasswordRegex(), userRequest.getPassword())) {
      throw new DataValidationException(Constants.INVALID_PASSWORD_MESSAGE, HttpStatus.BAD_REQUEST);
    }
    if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
      throw new DataValidationException(Constants.EMAIL_CONFLICT_MESSAGE, HttpStatus.CONFLICT);
    }
    return mapUserToUserResponse(userRepository.save(mapUserRequestToUser(userRequest)));
  }
  
  private User mapUserRequestToUser(UserRequest userRequest) {
    Date now = new Date();
    return User.builder()
        .id(Utils.getNewUuid())
        .name(userRequest.getName())
        .email(userRequest.getEmail())
        .password(userRequest.getPassword())
        .created(now)
        .lastLogin(now)
        .token("token")
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
    UserResponse userResponse = new UserResponse();
    userResponse.setId(user.getId());
    userResponse.setCreated(Utils.convertDateToFormattedString(user.getCreated(), Constants.FORMAT_YYYY_MM_DD));
    userResponse.setModified(Utils.convertDateToFormattedString(user.getModified(), Constants.FORMAT_YYYY_MM_DD));
    userResponse.setLastLogin(Utils.convertDateToFormattedString(user.getLastLogin(), Constants.FORMAT_YYYY_MM_DD));
    userResponse.setToken(user.getToken());
    userResponse.setIsActive(user.getIsActive());
    return userResponse;
  }
  
}
