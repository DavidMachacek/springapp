package com.david.demo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameOrderByAgeAsc(String lastName);

    List<Customer> findByLastNameOrderByAgeDesc(String lastName);
}