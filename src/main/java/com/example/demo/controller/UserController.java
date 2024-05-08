package com.example.demo.controller;

import com.example.demo.service.AuthService;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.request.LoginBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody LoginBody body) {
        return authService.loginSession(body.username(), body.password());
    }

    @PostMapping("/register")
    public String register(@RequestBody String body) {
        //TODO: process POST request
        return "Register";
    }
    
    @PostMapping("/logout")
    public String logout(@RequestBody String logout) {
        //TODO: process POST request
        
        return logout;
    }

    @GetMapping("/{userId}")
    public String getMeByUserId(@RequestParam("userId") String userId) {
        return "Returning user profile by Id";
    }
    
    @PutMapping("/{userId}")
    public String updateUserByUserId(@PathVariable String userId, @RequestBody String body) {
        //TODO: process PUT request
        
        return body;
    }
}
