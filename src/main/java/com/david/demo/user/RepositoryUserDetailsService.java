package com.david.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class RepositoryUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Autowired
    public RepositoryUserDetailsService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserSocialDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username);

        if (userEntity == null) {
            throw new UsernameNotFoundException("No userEntity found with username: " + username);
        }

        UserSocialDetails principal = UserSocialDetails.getBuilder()
                .firstName(userEntity.getFirstName())
                .id(userEntity.getId())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword())
                .role(userEntity.getRole())
                .userOrigin(userEntity.getUserOrigin())
                .username(userEntity.getEmail())
                .build();

        return principal;
    }
}
