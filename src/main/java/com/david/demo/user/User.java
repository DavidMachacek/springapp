package com.david.demo.user;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

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

    public User() {}

    public User(String firstName, String lastName, String email, List<Role> role, String username, String password) {
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
