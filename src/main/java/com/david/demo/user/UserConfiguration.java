package com.david.demo.user;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.david.demo.security.PasswordEncoderConfiguration;
import com.david.demo.social.LoginController;

@Configuration
@Import(PasswordEncoderConfiguration.class)
public class UserConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder, Validator validator, @Qualifier("loggedUser") UserDTO userDTO) {
        return new UserServiceImpl(userRepository, passwordEncoder, validator, userDTO);
    }

    @Bean
    public UserApiController userApiController(UserService userService) {
        return new UserApiController(userService);
    }

    @Bean
    public javax.validation.Validator validator() {
        return new LocalValidatorFactoryBean();
    }

    @Bean
    public Facebook facebook() {
        return new FacebookTemplate("EAAIfWkDAs9ABAMyASVk2Q0utM6zQXxuBmy1OXQcwrvnnlNSnn9wsZBudo7daDUbiNvCXpXDPy7SJxVZBI4yMvlaQ9DtlgFnnEfT5Lk1kNmbpv0zDePQ3N8ZAky3FozaO7CxW9ZC4HZBNFKRUcV1vksVlKyJrdKX0ZD");
    }
}
