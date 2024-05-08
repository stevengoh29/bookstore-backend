package com.example.demo.model.request;

public record LoginBody (
        String username,
        String password
) { }