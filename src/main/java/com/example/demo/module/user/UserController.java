package com.example.demo.module.user;

import com.example.demo.util.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/user")
public class UserController {
    @Autowired
    private ResponseTemplate<?> responseTemplate;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.getUsers();
        HttpStatus httpStatusOk = HttpStatus.OK;
        return new ResponseEntity<>(responseTemplate.build(httpStatusOk.value(), "List of users fetched", users), httpStatusOk);
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserByUserId(@PathVariable("userId") UUID userId) {
        User user = userService.getUserById(userId);
        HttpStatus httpStatusOk = HttpStatus.OK;
        return new ResponseEntity<>(responseTemplate.build(httpStatusOk.value(), "User is fetched", user), httpStatusOk);
    }

    @PostMapping()
    public ResponseEntity<?> addUser (@RequestBody UserRequest userRequest) {
        return null;
    }
}
