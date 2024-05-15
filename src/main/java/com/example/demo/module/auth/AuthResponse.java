package com.example.demo.module.auth;

public record AuthResponse (
      String token,
      Long expiresIn
) {
}
