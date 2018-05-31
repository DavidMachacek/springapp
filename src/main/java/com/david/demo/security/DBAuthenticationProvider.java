package com.david.demo.security;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.david.demo.user.Role;
import com.david.demo.user.User;
import com.david.demo.user.UserRepository;

public class DBAuthenticationProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(DBAuthenticationProvider.class);

    public DBAuthenticationProvider(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String username = authentication.getName();
        Object credentials = authentication.getCredentials();
        if (!(credentials instanceof String)) {
            return null;
        }
        String password = credentials.toString();

        Optional<User> userOptional = userRepository.findAll().stream()
                .filter(u -> match(u, username, password))
                .findFirst();

        if (!userOptional.isPresent()) {
            throw new BadCredentialsException("Authentication failed for " + username);
        }

        List<GrantedAuthority> grantedAuthorities = userOptional.get().getRole().stream().map(Role::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private boolean match(User user, String username, String password) {
        System.out.println(user.getUsername() + " " + username + " " + user.getPassword() + " " + password);
        logger.debug("Matching user of name {} - {} and password {} - {}", user.getUsername(), username, user.getPassword(), password);
        return user.getUsername().equals(username) && passwordEncoder.matches(password, user.getPassword());
    }


}