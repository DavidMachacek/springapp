package com.david.demo.customer;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.david.demo.logs.LogThis;

public class CustomerServiceImpl implements CustomerService {

    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerRepository customerRepository;
    private final CustomerGroupRepository customerGroupRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerGroupRepository customerGroupRepository) {
        this.customerRepository = customerRepository;
        this.customerGroupRepository = customerGroupRepository;
        setUp();
    }

    public void setUp() {
        CustomerGroup customerGroup = customerGroupRepository.save(new CustomerGroup("super"));
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Jack", "Bauer", 20, customerGroup.getId()));
        customerRepository.save(new Customer("Chloe", "O'Brian", 15, customerGroup.getId()));
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
    }


    @Cacheable(value = "customer-cache", key = "#lastName")
    public List<Customer> getByName(String lastName) {
        List<Customer> customers = customerRepository.findByLastName(lastName);
        customers.forEach(customer -> log.debug("action: {}, found - id: {}, name: {},  ", "getByName", customer.getId(), customer.getFirstName()));
        return customers;
    }

    @LogThis
    @Cacheable(value = "customer-cache", key = "#lastName")
    public List<Customer> getByAgeDesc(String lastName) {
        List<Customer> customers = customerRepository.findByLastNameOrderByAgeDesc(lastName);
        customers.forEach(customer -> log.debug("action: {}, found - id: {}, name: {},  ", "getByAgeDesc", customer.getId(), customer.getFirstName()));
        return customers;
    }

    @LogThis
    @Cacheable(value = "customer-cache", key = "#lastName")
    public List<Customer> getByAgeAsc(String lastName) {
        List<Customer> customers = customerRepository.findByLastNameOrderByAgeAsc(lastName);
        customers.forEach(customer -> log.debug("action: {}, found - id: {}, name: {},  ", "getByAgeAsc", customer.getId(), customer.getFirstName()));
        return customers;
    }

    @LogThis
    @Cacheable(value = "customer-cache", key = "#groupName")
    public List<Customer> getGroup(String groupName) {
        CustomerGroup customerGroup = customerGroupRepository.findCustomerGroupByGroupName(groupName);
        List<Customer> customers = customerRepository.findByGroupId(customerGroup.getId());
        customers.forEach(customer -> log.debug("action: {}, found - id: {}, name: {},  ", "getGroup", customer.getId(), customer.getFirstName()));
        return customers;
    }

    //@LogThis
    //@Cacheable(value = "customer-cache")
    public List<Customer> getAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer addNew(Customer newCustomer) {
        return customerRepository.save(newCustomer);
    }

    @CacheEvict(value = "customer-cache", key = "#lastName")
    public void deleteByLastName(String lastName) {
        customerRepository.deleteByLastName(lastName);
        log.debug("action: {}, name: {}", "deleteByLastName", lastName);
    }

}
