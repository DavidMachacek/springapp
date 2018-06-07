package com.david.demo.customer;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * JPA Entity object
 */
@Entity
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Long groupId;

    public Customer() {}

    public Customer(String firstName, String lastName, int age, Long groupId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.groupId = groupId;
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
    public int getAge() {
        return age;
    }

    /**
     * Property setter
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Property getter
     */
    public Long getGroupId() {
        return groupId;
    }

    /**
     * Property setter
     */
    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s', age='%s']",
                id, firstName, lastName, String.valueOf(age));
    }

}