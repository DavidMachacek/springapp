package com.david.demo.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.david.demo.security.PasswordEncoderConfiguration;

@Configuration
@Import(PasswordEncoderConfiguration.class)
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
