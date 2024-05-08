package com.example.demo.service;

import com.example.demo.model.app.User;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> selectAllUser ();
    Optional<User> selectUserById (UUID userId);
    Optional<User> selectUserByUsername (String username);
    String insertNewUser (User user);
    String updateUser (User updatedUser);
    String deleteUserById (UUID userId);
    boolean existsUserWithUsername (String email);
    boolean existsUserById (UUID userId);
//    Authentication getUserByLoginCredential (String username, String password);
}
