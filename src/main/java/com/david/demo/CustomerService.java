package com.david.demo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
        setUp();
    }

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    public void setUp() {
        repository.deleteAll();
        repository.save(new Customer("Jack", "Bauer", 20));
        repository.save(new Customer("Chloe", "O'Brian", 15));
        repository.save(new Customer("Kim", "Bauer", 28));
        repository.save(new Customer("David", "Palmer", 19));
        repository.save(new Customer("Michelle", "Dessler", 16));
        repository.save(new Customer("Peter", "Bauer", 34));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : repository.findAll()) {
            log.info(customer.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        repository.findById(1L)
                .ifPresent(customer -> {
                    log.info("Customer found with findById(1L):");
                    log.info("--------------------------------");
                    log.info(customer.toString());
                    log.info("");
                });

        // fetch customers by last name
        log.info("Customer found with findByLastName('Bauer'):");
        log.info("--------------------------------------------");
        repository.findByLastName("Bauer").forEach(bauer -> {
            log.info(bauer.toString());
        });
        // for (Customer bauer : repository.findByLastName("Bauer")) {
        // 	log.info(bauer.toString());
        // }
        log.info("");
    };

    public List<Customer> getByName(String lastName) {
        List<Customer> customers = repository.findByLastName(lastName);
        customers.forEach(customer -> System.out.println("FOUND: " + customer));
        return customers;
    }

    public List<Customer> getByAgeDesc(String lastName) {
        List<Customer> customers = repository.findByLastNameOrderByAgeDesc(lastName);
        customers.forEach(customer -> System.out.println("FOUND: " + customer));
        return customers;
    }

    public List<Customer> getByAgeAsc(String lastName) {
        List<Customer> customers = repository.findByLastNameOrderByAgeAsc(lastName);
        customers.forEach(customer -> System.out.println("FOUND: " + customer));
        return customers;
    }

}
