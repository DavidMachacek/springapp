package com.david.demo.customer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerTO {
    @JsonProperty
    private String firstName;
    @JsonProperty
    private String lastName;
    @JsonProperty
    private int age;

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
}
