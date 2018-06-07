package com.david.demo.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Customer domain configuration
 */
@Configuration
public class CustomerConfiguration {

    @Bean
    public CustomerService customerService(CustomerRepository customerRepository,
                                    CustomerGroupRepository customerGroupRepository) {
        return new CustomerServiceImpl(customerRepository, customerGroupRepository);
    }

}
