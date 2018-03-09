package com.david.demo;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }



    @GetMapping(value = "/customer/{lastName}" )
    public List<Customer> getCustomers(@PathVariable("lastName")String lastName) {

        return service.getByName(lastName);
    }

    @GetMapping(value = "/customer/{lastName}/asc" )
    public List<Customer> getCustomersAsc(@PathVariable("lastName")String lastName) {

        return service.getByAgeAsc(lastName);
    }

    @GetMapping(value = "/customer/{lastName}/desc" )
    public List<Customer> getCustomersDesc(@PathVariable("lastName")String lastName) {

        return service.getByAgeDesc(lastName);
    }
}
