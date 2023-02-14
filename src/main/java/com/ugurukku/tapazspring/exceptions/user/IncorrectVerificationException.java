package com.ugurukku.tapazspring.exceptions.user;

public class IncorrectVerificationException extends RuntimeException {
    public IncorrectVerificationException(String message) {
        super(message);
    }
}
