package com.david.demo.customer;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerMapperTest {

    private final CustomerMapper mapper = CustomerMapper.INSTANCE;

    @Test
    public void customerToCustomerTO() {
        Customer customer = new Customer("firstName", "lastName", 20, 5L);
        CustomerTO customerTO = mapper.customerToCustomerTO(customer);
        assertEquals(customerTO.getFirstName(), "firstName");
        assertEquals(customerTO.getLastName(), "lastName");
        assertEquals(customerTO.getAge(), 20);
    }

    @Test
    public void customerTOToCustomer() {
        CustomerTO customerTO = new CustomerTO();
        customerTO.setFirstName("firstName");
        customerTO.setLastName("lastName");
        customerTO.setAge(20);
        Customer customer = mapper.customerTOToCustomer(customerTO);
        assertEquals(customer.getFirstName(), "firstName");
        assertEquals(customer.getLastName(), "lastName");
        assertEquals(customer.getAge(), 20);
    }
}