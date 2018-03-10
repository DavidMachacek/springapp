package com.david.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfiguration {

    @Bean
    CustomerService service(CustomerRepository repository) {
        return new CustomerServiceImpl(repository);
    }
}
