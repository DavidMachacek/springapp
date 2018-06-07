package com.david.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        AuthorizationServerConfig.class,
        PasswordEncoderConfiguration.class,
        ResourceServerConfig.class
})
public class AuthorizationGlobalConfig {
}
