package com.david.demo.user;

public class EmailExistsException extends Exception {

    String message;

    public EmailExistsException(Throwable s, String message) {
        super(s);
        this.message = message;
    }

    public EmailExistsException(String message) {
        super(message);
    }
}
