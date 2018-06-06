package com.david.demo.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.david.demo.PersistenceConfiguration;
import com.david.demo.events.EventConfiguration;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {PersistenceConfiguration.class})
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void findByLastName() {
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Jack", "Bauer", 20, 1L));
        List<Customer> bauerList = customerRepository.findByLastName("Bauer");
        assertTrue(bauerList.size() == 1);
        assertEquals(bauerList.get(0).getFirstName(), "Jack");
        assertEquals(bauerList.get(0).getLastName(), "Bauer");
        assertEquals(bauerList.get(0).getAge(), 20);
    }
}