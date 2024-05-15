package com.example.demo.module.user;

import com.example.demo.util.CrudStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers () {
        return userRepository.findAll();
    }

    public User getUserById (UUID userId) {
        Optional<User> user = userRepository.findById(userId);
        return user.orElse(null);
    }

    public User addUser (UserRequest userRequest) {
        User user = new User(userRequest.username(), userRequest.password(), userRequest.fullName());
        return userRepository.save(user);
    }

    public CrudStatus editUserById () {
        return null;
    }

    public CrudStatus deleteUserById () {
        return null;
    }

    public Optional<User> selectUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
