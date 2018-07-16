package com.david.demo.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.social.security.SocialUser;

public class UserSocialDetails extends SocialUser {
    private Long id;
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;
    private List<Role> role;
    private String username;
    private UserOrigin userOrigin;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSocialDetails(String username, String password) {
        this(username, password, Collections.singletonList(new SimpleGrantedAuthority("simple")));
    }

    public UserSocialDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }



    public static Builder getBuilder() {
        return new Builder();
    }

    public static class Builder {

        private Long id;

        private String username;

        private String firstName;

        private String lastName;

        private String password;

        private List<Role> role;

        private UserOrigin userOrigin;

        private Set<GrantedAuthority> authorities;

        public Builder() {
            this.authorities = new HashSet<>();
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            if (password == null) {
                password = "SocialUser";
            }

            this.password = password;
            return this;
        }

        public Builder role(List<Role> role) {
            this.role = role;

            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
            this.authorities.add(authority);

            return this;
        }

        public Builder userOrigin(UserOrigin userOrigin) {
            this.userOrigin = userOrigin;
            return this;
        }

        public Builder username(String username) {
            this.username = username;
            return this;
        }

        public UserSocialDetails build() {
            UserSocialDetails user = new UserSocialDetails(username, password, authorities);

            user.id = id;
            user.firstName = firstName;
            user.lastName = lastName;
            user.role = role;
            user.userOrigin = userOrigin;

            return user;
        }
    }
}
