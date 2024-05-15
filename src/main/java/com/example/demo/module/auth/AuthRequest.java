package com.example.demo.module.auth;

public record AuthRequest (
        String username,
        String password
) {
}
