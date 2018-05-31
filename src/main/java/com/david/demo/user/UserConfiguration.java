package com.david.demo.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Bean
    public UserApiController userApiController(UserService userService) {
        return new UserApiController(userService);
    }

}
