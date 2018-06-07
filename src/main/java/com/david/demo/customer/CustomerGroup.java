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
public class CustomerGroup  implements Serializable {

    public CustomerGroup() {
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String groupName;

    public CustomerGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }


    public Long getId() {
        return id;
    }
}
