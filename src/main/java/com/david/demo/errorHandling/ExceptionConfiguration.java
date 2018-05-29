package com.david.demo.errorHandling;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.converter.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@Configuration
public class ExceptionConfiguration {

    @Bean
    public Converter constraintConverter() {
        return new ConstraintViolationConverter();
    }

    @Bean
    public ExceptionEndpointAdvice exceptionEndpointAdvice(@Qualifier("constraintConverter") Converter constraintConverter) {
        return new ExceptionEndpointAdvice(constraintConverter);
    }

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
