package com.david.demo.errorHandling;

public class EmailExistsException extends RuntimeException {

    private String message;

    public EmailExistsException(String message) {
        super(message);
    }
}
