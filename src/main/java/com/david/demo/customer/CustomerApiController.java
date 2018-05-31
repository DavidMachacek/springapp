package com.david.demo.customer;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.david.demo.logs.LogThis;

@RequestMapping("/api")
@RestController
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}" )
    public List<Customer> getCustomers(@PathVariable("lastName")String lastName) {

        return customerService.getByName(lastName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/asc" )
    public List<Customer> getCustomersAsc(@PathVariable("lastName")String lastName) {

        return customerService.getByAgeAsc(lastName);
    }

    @LogThis
    @GetMapping(value = "/customer/{lastName}/desc" )
    public List<Customer> getCustomersDesc(@PathVariable("lastName")String lastName) {

        return customerService.getByAgeDesc(lastName);
    }

    @LogThis
    @GetMapping(value = "/group/{groupName}" )
    public List<Customer> getGroup(@PathVariable("groupName")String groupName) {

        return customerService.getGroup(groupName);
    }

    @Transactional
    @LogThis
    @GetMapping(value = "/customer/{lastName}/delete" )
    public void deleteCustomer(@PathVariable("lastName")String lastName) {

        customerService.deleteByLastName(lastName);
    }

}
