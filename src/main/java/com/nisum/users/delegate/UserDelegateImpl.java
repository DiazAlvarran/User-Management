package com.nisum.users.delegate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nisum.users.api.UserApiDelegate;
import com.nisum.users.dto.UserRequest;
import com.nisum.users.dto.UserResponse;
import com.nisum.users.service.UserService;

/**
 * Class that implements UserApiDelegate for user management.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
@Service
public class UserDelegateImpl implements UserApiDelegate {

    private final UserService userService;

    /**
     * Constructor with all arguments of the UserDelegateImpl class.
     *
     * @param userService implements user management
     */
    public UserDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    /**
     * Save a new user.
     *
     * @param authorization JWT (Bearer eyJhbGcizJ9.eyJzdIiwicm9s.01eHo-zZhRn_xOp)
     * @param userRequest   contains the fields necessary to create a user
     * @return ResponseEntity response with data from the created user
     */
    @Override
    public ResponseEntity<UserResponse> saveUser(String authorization, UserRequest userRequest) {
        return new ResponseEntity<UserResponse>(userService.saveUser(authorization, userRequest),
                HttpStatus.CREATED);
    }

}
