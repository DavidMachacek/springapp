package com.david.demo.user;

import javax.validation.Validator;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.david.demo.security.PasswordEncoderConfiguration;

@Configuration
@Import(PasswordEncoderConfiguration.class)
public class UserConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator) {
        return new UserServiceImpl(userRepository, passwordEncoder, validator);
    }

    @Bean
    public UserApiController userApiController(UserService userService) {
        return new UserApiController(userService);
    }

    @Bean
    public javax.validation.Validator validator() {
        return new LocalValidatorFactoryBean();
    }
}
