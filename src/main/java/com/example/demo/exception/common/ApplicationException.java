package com.example.demo.exception.common;

public class ApplicationException extends RuntimeException {
    public ApplicationException () {
        super();
    }

    public ApplicationException(String message) {
        super(message);
    }
}
