package com.david.demo.user;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.david.demo.validation.PasswordMatches;
import com.david.demo.validation.ValidEmail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
@PasswordMatches
public class UserDTO {
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
}
