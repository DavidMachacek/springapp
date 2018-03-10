package com.david.demo;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface CustomerService {

    void setUp();
    List<Customer> getByName(String lastName);
    List<Customer> getByAgeDesc(String lastName);
    List<Customer> getByAgeAsc(String lastName);
    void deleteByLastName(String lastName);

}
