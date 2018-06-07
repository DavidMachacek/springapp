package com.david.demo.customer;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

/**
 * Spring data repository accessing Customer table - DAO
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    List<Customer> findByLastNameOrderByAgeAsc(String lastName);

    List<Customer> findByLastNameOrderByAgeDesc(String lastName);

    List<Customer> findByGroupId(Long groupId);

    List<Customer> findAll();

    void deleteByLastName(String lastName);
}