package com.david.demo.errorHandling;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class ConstraintException extends RuntimeException {
    private final Set<ConstraintViolation<?>> constraintViolations;

    public ConstraintException(String message, Set<? extends ConstraintViolation<?>> constraintViolations) {
        super(message);
        if (constraintViolations == null) {
            this.constraintViolations = null;
        } else {
            this.constraintViolations = new HashSet(constraintViolations);
        }

    }

    public ConstraintException(Set<? extends ConstraintViolation<?>> constraintViolations) {
        this(constraintViolations != null ? toString(constraintViolations) : null, constraintViolations);
    }

    public Set<ConstraintViolation<?>> getConstraintViolations() {
        return this.constraintViolations;
    }

    private static String toString(Set<? extends ConstraintViolation<?>> constraintViolations) {
        return (String)constraintViolations.stream().map((cv) -> {
            return cv == null ? "null" : cv.getPropertyPath() + ": " + cv.getMessage();
        }).collect(Collectors.joining(", "));
    }
}
