package com.example.demo.exception.common;

public class RequestException extends RuntimeException {
    public RequestException() {
        super();
    }

    public RequestException(String message) {
        super(message);
    }
}
