package com.example.demo.service.impl;

import com.example.demo.model.app.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> selectAllUser() {
        return List.of();
    }

    @Override
    public Optional<User> selectUserById(UUID userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> selectUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public String insertNewUser(User user) {
        System.out.println("Inserting");
        System.out.println(user.getUsername());
        var data = userRepository.save(user);
        System.out.println(data.getUsername());
        return "Okay";
    }

    @Override
    public String updateUser(User updatedUser) {
        return "";
    }

    @Override
    public String deleteUserById(UUID userId) {
        return "";
    }

    @Override
    public boolean existsUserWithUsername(String email) {
        return false;
    }

    @Override
    public boolean existsUserById(UUID userId) {
        return false;
    }
}
