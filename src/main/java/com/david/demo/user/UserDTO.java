package com.david.demo.user;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
