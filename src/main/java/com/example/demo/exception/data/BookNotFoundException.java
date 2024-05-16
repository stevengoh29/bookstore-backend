package com.example.demo.exception.data;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException() {
        super();
    }

    public BookNotFoundException(String message) {
        super(message);
    }
}