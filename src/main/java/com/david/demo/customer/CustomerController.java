package com.david.demo.customer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.david.demo.logs.LogThis;

@RestController
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}" )
    public List<Customer> getCustomers(@PathVariable("lastName")String lastName) {

        return service.getByName(lastName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/asc" )
    public List<Customer> getCustomersAsc(@PathVariable("lastName")String lastName) {

        return service.getByAgeAsc(lastName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/desc" )
    public List<Customer> getCustomersDesc(@PathVariable("lastName")String lastName) {

        return service.getByAgeDesc(lastName);
    }

    @LogThis
    @GetMapping(value = "/group/{groupName}" )
    public List<Customer> getGroup(@PathVariable("groupName")String groupName) {

        return service.getGroup(groupName);
    }

    @Transactional
    @LogThis
    @GetMapping(value = "/customer/{lastName}/delete" )
    public void deleteCustomer(@PathVariable("lastName")String lastName) {

        service.deleteByLastName(lastName);
    }
}
