package com.david.demo.events;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class EventEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private String id;

    private String message;

    /**
     * Property getter
     */
    public String getMessage() {
        return message;
    }

    /**
     * Property setter
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
