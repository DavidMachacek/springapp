package com.david.demo.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.david.demo.logs.LogThis;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerGroupRepository customerGroupRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerGroupRepository customerGroupRepository) {
        this.customerRepository = customerRepository;
        this.customerGroupRepository = customerGroupRepository;
        setUp();
    }

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public void setUp() {
        CustomerGroup customerGroup = customerGroupRepository.save(new CustomerGroup("super"));

        customerRepository.deleteAll();
        Customer customer1 = new Customer("Jack", "Bauer", 20, customerGroup.getId());
        customerRepository.save(customer1);
        Customer customer2 = customerRepository.save(new Customer("Chloe", "O'Brian", 15, customerGroup.getId()));
        customerRepository.save(new Customer("Kim", "Bauer", 28, customerGroup.getId()));
        customerRepository.save(new Customer("David", "Palmer", 19, customerGroup.getId()));
        customerRepository.save(new Customer("Michelle", "Dessler", 16, customerGroup.getId()));
        customerRepository.save(new Customer("Peter", "Bauer", 34, customerGroup.getId()));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            log.info(customer.toString());
        }
        log.info("");

        // fetch an individual customer by ID
        customerRepository.findById(1L)
                .ifPresent(customer -> {
                    log.info("Customer found with findById(1L):");
                    log.info("--------------------------------");
                    log.info(customer.toString());
                    log.info("");
                });

        // fetch customers by last name
        log.info("Customer found with findByLastName('Bauer'):");
        log.info("--------------------------------------------");
        customerRepository.findByLastName("Bauer").forEach(bauer -> {
            log.info(bauer.toString());
        });
        // for (Customer bauer : customerRepository.findByLastName("Bauer")) {
        // 	log.info(bauer.toString());
        // }
        log.info("");
        //customerGroupRepository.save(new CustomerGroup("Super", Arrays.asList(customer1, customer2)));
    }


    @Cacheable(value = "customer-cache", key = "#lastName")
    public List<Customer> getByName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        customers.forEach(customer -> System.out.println("FOUND: " + customer));
        return customers;
    }

    @LogThis
    @Cacheable(value = "customer-cache", key = "#lastName")
    public List<Customer> getByAgeDesc(String lastName) {
        List<Customer> customers = customerRepository.findByLastNameOrderByAgeDesc(lastName);
        customers.forEach(customer -> System.out.println("FOUND: " + customer));
        return customers;
    }

    @LogThis
    @Cacheable(value = "customer-cache", key = "#lastName")
    public List<Customer> getByAgeAsc(String lastName) {
        List<Customer> customers = customerRepository.findByLastNameOrderByAgeAsc(lastName);
        customers.forEach(customer -> System.out.println("FOUND: " + customer));
        return customers;
    }

    @LogThis
    @Cacheable(value = "customer-cache", key = "#groupName")
    public List<Customer> getGroup(String groupName) {
        CustomerGroup customerGroup = customerGroupRepository.findCustomerGroupByGroupName(groupName);
        List<Customer> customers = customerRepository.findByGroupId(customerGroup.getId());
        customers.forEach(customer -> System.out.println("FOUND GROUP: " + customer));
        return customers;
    }

    @CacheEvict(value = "customer-cache", key = "#lastName")
    public void deleteByLastName(String lastName) {
        customerRepository.deleteByLastName(lastName);
    }

}
