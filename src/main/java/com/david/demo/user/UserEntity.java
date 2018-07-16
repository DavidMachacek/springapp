package com.david.demo.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.google.common.base.Objects;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Role> role;

    private String username;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "userOrigin", length = 20)
    private UserOrigin userOrigin;
    public UserEntity() {}

    public UserEntity(String firstName, String lastName, String email, List<Role> role, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.username = username;
        this.password = password;
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
    public UserOrigin getUserOrigin() {
        return userOrigin;
    }

    /**
     * Property setter
     */
    public void setUserOrigin(UserOrigin userOrigin) {
        this.userOrigin = userOrigin;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("email", email)
                .add("role", role)
                .add("username", username)
                .add("password", password)
                .add("userOrigin", userOrigin)
                .toString();
    }
}
