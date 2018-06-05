package com.david.demo.events;

public class ApplicationEvent {
    private String message;

    public ApplicationEvent(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

