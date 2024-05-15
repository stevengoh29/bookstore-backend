package com.example.demo.module.user;

public record UserRequest (
    String username,
    String password,
    String fullName
) {
}
