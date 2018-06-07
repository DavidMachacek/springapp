package com.david.demo.customer;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.david.demo.AbstractControllerTest;
import com.david.demo.errorHandling.ErrorCodes;

public class CustomerApiControllerTest extends AbstractControllerTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void getCustomers() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{lastName}", "Bauer"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customers"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void notFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/customer/{lastName}", "NotExists"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0].code").value(ErrorCodes.ERR_005_NOT_FOUND.getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0].attribute").value("Customer NotExists"))
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0].message").value("Customer NotExists not found!"))
                .andExpect(MockMvcResultMatchers.jsonPath("errors[0].severity").value("ERROR"));
    }

    @Test
    public void createCustomer() throws Exception {
        CustomerTO customerTO = new CustomerTO("TestFirstName", "TestSurname", 20);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/customer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        assertEquals(customerRepository.findByLastName("TestSurname").get(0).getFirstName(), "TestFirstName");
    }
}