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

/**
 * Implement UserService methods.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private ApplicationProperties properties;

    /**
     * Constructor with all arguments of the JwtTokenServiceImpl class.
     *
     * @param userRepository for interaction with DB
     * @param properties     contains properties useful for this class
     */
    public UserServiceImpl(UserRepository userRepository, ApplicationProperties properties) {
        this.userRepository = userRepository;
        this.properties = properties;
    }

    /**
     * Save a new user.
     *
     * @param authorization JWT (Bearer
     *                      eyJhbGciOiJIUzJ9.eyJzdIiwicm9s.01eHo-zZhRn_xOp)
     * @param userRequest   contains the fields necessary to create a user
     * @return UserResponse response with data from the created user
     */
    @Override
    @Transactional
    public UserResponse saveUser(String authorization, UserRequest userRequest) {
        if (Utils.isInvalidString(properties.getEmailRegex(), userRequest.getEmail())) {
            throw new DataValidationException(Constants.INVALID_EMAIL_MESSAGE,
                    HttpStatus.BAD_REQUEST);
        }
        if (Utils.isInvalidString(properties.getPasswordRegex(), userRequest.getPassword())) {
            throw new DataValidationException(Constants.INVALID_PASSWORD_MESSAGE,
                    HttpStatus.BAD_REQUEST);
        }
        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            throw new DataValidationException(Constants.EMAIL_CONFLICT_MESSAGE,
                    HttpStatus.CONFLICT);
        }
        return mapUserToUserResponse(
                userRepository.save(mapUserRequestToUser(authorization, userRequest)));
    }

    /**
     * Converts a UserRequest object to a User object.
     *
     * @param authorization JWT (Bearer
     *                      eyJhbGciOiJIUzJ9.eyJzdIiwicm9s.01eHo-zZhRn_xOp)
     * @param userRequest   contains the fields necessary to create a user
     * @return User user to be created
     */
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

    /**
     * Converts a User object to a UserResponse object.
     *
     * @param user created user
     * @return UserResponse response with data from the created user
     */
    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .created(Utils.convertDateToFormattedString(user.getCreated(),
                        Constants.FORMAT_YYYY_MM_DD))
                .modified(Utils.convertDateToFormattedString(user.getModified(),
                        Constants.FORMAT_YYYY_MM_DD))
                .lastLogin(Utils.convertDateToFormattedString(user.getLastLogin(),
                        Constants.FORMAT_YYYY_MM_DD))
                .token(user.getToken())
                .isActive(user.getIsActive())
                .build();
    }

    /**
     * Update user when generating a session.
     *
     * @param user  user to be updated
     * @param token JWT
     * @return User updated user
     */
    @Override
    @Transactional
    public User updateUserSession(User user, String token) {
        user.setLastLogin(new Date());
        user.setToken(Constants.PREFIX_BEARER.concat(token));
        return userRepository.save(user);
    }

}
