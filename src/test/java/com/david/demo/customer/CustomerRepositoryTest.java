package com.david.demo.customer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
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

    @Before
    public void setUp() {
        customerRepository.deleteAll();
        customerRepository.save(new Customer("Jack", "Bauer", 20, 1L));
    }

    @Test
    public void findByLastName() {
        assertBauer(customerRepository.findByLastName("Bauer"));
    }

    @Test
    public void findAll() {
        assertBauer(customerRepository.findAll());
    }

    @Test
    public void delete() {
        customerRepository.deleteByLastName("Bauer");
        List<Customer> all = customerRepository.findAll();
        assertTrue(all.isEmpty());
    }

    @Test
    public void update() {
        Customer bauer = customerRepository.findByLastName("Bauer").get(0);
        bauer.setLastName("NotBauer");
        customerRepository.save(bauer);
        List<Customer> notBauer = customerRepository.findByLastName("NotBauer");
        assertTrue(notBauer.size() == 1);
        assertEquals(notBauer.get(0).getFirstName(), "Jack");
        assertEquals(notBauer.get(0).getLastName(), "NotBauer");
        assertEquals(notBauer.get(0).getAge(), 20);
    }

    private void assertBauer(List<Customer> bauerList) {
        assertTrue(bauerList.size() == 1);
        assertEquals(bauerList.get(0).getFirstName(), "Jack");
        assertEquals(bauerList.get(0).getLastName(), "Bauer");
        assertEquals(bauerList.get(0).getAge(), 20);

    }
}