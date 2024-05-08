package com.example.demo.exception.common;

public class TransactionException extends RuntimeException {
    public TransactionException() {
        super();
    }

    public TransactionException(String message) {
        super(message);
    }
}
