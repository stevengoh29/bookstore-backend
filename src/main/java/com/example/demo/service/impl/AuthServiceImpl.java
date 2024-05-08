package com.example.demo.service.impl;

import com.example.demo.exception.security.AuthenticationException;
import com.example.demo.service.AuthService;
import com.example.demo.service.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl  implements AuthService {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtService jwtService;

    @Override
    public String createJwtToken() {
        return "";
    }

    @Override
    public String loginSession(String username, String password) {
        Authentication authentication = userDetailsService.getUserByLoginCredential(username, password);
        if(authentication == null) throw new AuthenticationException("Username or password is incorrect");

        String token = jwtService.createJwtToken(authentication);
        System.out.println(token);

        return token;
    }
}
