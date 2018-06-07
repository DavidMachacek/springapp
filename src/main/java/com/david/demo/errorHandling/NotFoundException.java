package com.david.demo.errorHandling;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private String element;

    public NotFoundException(String element) {
        this.element = element;
    }

    @Override
    public String getMessage() {
        return element + " not found!";
    }

    /**
     * Property getter
     */
    public String getElement() {
        return element;
    }
}
