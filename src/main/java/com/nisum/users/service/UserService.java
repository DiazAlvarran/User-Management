package com.nisum.users.service;

import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.models.User;

/**
 * Defines methods for user management.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
public interface UserService {

    /**
     * Save a new user.
     *
     * @param authorization JWT (Bearer eyJhbGcizJ9.eyJzdIiwicm9s.01eHo-zZhRn_xOp)
     * @param userRequest   contains the fields necessary to create a user
     * @return UserResponse response with data from the created user
     */
    UserResponse saveUser(String authorization, UserRequest userRequest);

    /**
     * Update user when generating a session.
     *
     * @param user  user to be updated
     * @param token JWT
     * @return User updated user
     */
    User updateUserSession(User user, String token);

}
