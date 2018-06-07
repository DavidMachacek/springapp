package com.david.demo.errorHandling;

import static com.david.demo.errorHandling.ErrorCodes.ERR_003_NOT_AUTHORIZED;
import static com.david.demo.errorHandling.ErrorCodes.ERR_004_ALREADY_EXISTS;
import static com.david.demo.errorHandling.ErrorCodes.ERR_005_NOT_FOUND;

import java.util.Collections;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Allows intercepting chosen exception and return transfer object with details instead
 */
@RestControllerAdvice
public class ExceptionEndpointAdvice {


    private static final Logger logger = LoggerFactory.getLogger(ExceptionEndpointAdvice.class);
    private final Converter<ConstraintViolation<?>, ErrorTO> converter;

    public ExceptionEndpointAdvice(Converter converter) {
        this.converter = converter;
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorListTO handleAnyException(ConstraintViolationException exceptions) {
        logger.debug("ConstraintViolationException caught");
        return new ErrorListTO(exceptions.getConstraintViolations().stream().map(converter::convert).collect(Collectors.toList()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public ErrorListTO handleAnyException(BadCredentialsException exceptions) {
        logger.debug("BadCredentialsException caught");
        return new ErrorListTO(Collections.singletonList(new ErrorTO(ERR_003_NOT_AUTHORIZED.getValue(), exceptions.getMessage(), "access_token")));
    }

    @ExceptionHandler(EmailExistsException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorListTO handleAnyException(EmailExistsException exception) {
        logger.debug("EmailExistsException caught");
        return new ErrorListTO(Collections.singletonList(new ErrorTO(ERR_004_ALREADY_EXISTS.getValue(), exception.getMessage(), "email")));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ErrorListTO handleAnyException(NotFoundException exception) {
        logger.debug("NotFoundException caught");
        return new ErrorListTO(Collections.singletonList(new ErrorTO(ERR_005_NOT_FOUND.getValue(), exception.getMessage(), exception.getElement())));
    }
}
