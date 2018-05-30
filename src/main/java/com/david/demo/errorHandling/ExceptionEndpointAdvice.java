package com.david.demo.errorHandling;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionEndpointAdvice {

    private final Converter<ConstraintViolation<?>, ErrorTO> converter;

    public ExceptionEndpointAdvice(Converter converter) {
        this.converter = converter;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorListTO handleAnyException(ConstraintViolationException exceptions) {
        return new ErrorListTO(exceptions.getConstraintViolations().stream().map(converter::convert).collect(Collectors.toList()));
    }
}
