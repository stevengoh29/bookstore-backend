package com.example.demo.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
    String createJwtToken(Authentication authentication);
}
