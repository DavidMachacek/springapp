package com.david.demo.customer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * Purpose of this class is just to use Mockito framework, it doesn't really test anything..
 */
public class CustomerServiceMockitoTest {

    private CustomerService service;
    private List<Customer> customers;

    @Before
    public void setUp() {
        service = mock(CustomerService.class);
        customers = Collections.singletonList(new Customer("testFirst", "testSurname", 31, 2L));
        when(service.getAll()).thenReturn(customers);
    }

    @Test
    public void test() {
        assertEquals(customers, service.getAll());
    }

}
