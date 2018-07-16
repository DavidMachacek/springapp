package com.david.demo.user;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.david.demo.validation.PasswordMatches;
import com.david.demo.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordMatches
public class UserDTO {
    private Long id;
    @JsonProperty
    @NotNull
    @NotEmpty
    private String firstName;
    @JsonProperty
    @NotNull
    @NotEmpty
    private String lastName;
    @JsonProperty
    @NotNull
    @NotEmpty
    private String password;
    @JsonProperty
    private String matchingPassword;
    @JsonProperty
    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    private List<Role> role;
    private String username;
    private UserOrigin userOrigin;
    private Collection<? extends GrantedAuthority> authorities;

    private String gender;

    public UserDTO() {
    }

    @JsonCreator
    public UserDTO(@JsonProperty("id") Long id,
                   @JsonProperty("firstName") @NotNull @NotEmpty String firstName,
                   @JsonProperty("lastName") @NotNull @NotEmpty String lastName,
                   @JsonProperty("password") @NotNull @NotEmpty String password,
                   @JsonProperty("matchingPassword") String matchingPassword,
                   @JsonProperty("email") @NotNull @NotEmpty String email,
                   @JsonProperty("role") List<Role> role,
                   @JsonProperty("username") String username,
                   @JsonProperty("userOrigin") UserOrigin userOrigin,
                   @JsonProperty("authorities") Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.matchingPassword = matchingPassword;
        this.email = email;
        this.role = role;
        this.username = username;
        this.userOrigin = userOrigin;
        this.authorities = authorities;
    }

    /**
     * Property getter
     */
    public Long getId() {
        return id;
    }

    /**
     * Property setter
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Property getter
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Property setter
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Property getter
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Property setter
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Property getter
     */
    public String getPassword() {
        return password;
    }

    /**
     * Property setter
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Property getter
     */
    public String getMatchingPassword() {
        return matchingPassword;
    }

    /**
     * Property setter
     */
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    /**
     * Property getter
     */
    public String getEmail() {
        return email;
    }

    /**
     * Property setter
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Property getter
     */
    public List<Role> getRole() {
        return role;
    }

    /**
     * Property setter
     */
    public void setRole(List<Role> role) {
        this.role = role;
    }

    /**
     * Property getter
     */
    public String getUsername() {
        return username;
    }

    /**
     * Property setter
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Property getter
     */
    public UserOrigin getUserOrigin() {
        return userOrigin;
    }

    /**
     * Property setter
     */
    public void setUserOrigin(UserOrigin userOrigin) {
        this.userOrigin = userOrigin;
    }

    /**
     * Property getter
     */
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /**
     * Property setter
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Property getter
     */
    public String getGender() {
        return gender;
    }

    /**
     * Property setter
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("password", password)
                .add("matchingPassword", matchingPassword)
                .add("email", email)
                .add("role", role)
                .add("username", username)
                .add("userOrigin", userOrigin)
                .add("authorities", authorities)
                .toString();
    }
}
