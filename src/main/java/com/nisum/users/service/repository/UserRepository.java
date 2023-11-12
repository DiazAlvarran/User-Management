package com.nisum.users.service.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nisum.users.models.User;

/**
 * Implement methods for user query and persistence.
 *
 * @author Jorge Diaz
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

    /**
     * Find user by email.
     *
     * @param email user email
     * @return Optional query result in database
     */
    Optional<User> findUserByEmail(String email);

}
