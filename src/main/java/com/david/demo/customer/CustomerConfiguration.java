package com.david.demo.customer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    CustomerService customerService(CustomerRepository customerRepository,
                                    CustomerGroupRepository customerGroupRepository) {
        return new CustomerServiceImpl(customerRepository, customerGroupRepository);
    }
}
