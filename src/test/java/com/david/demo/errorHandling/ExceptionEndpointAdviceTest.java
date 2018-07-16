package com.david.demo.errorHandling;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.david.demo.AbstractControllerTest;
import com.david.demo.user.UserDTO;

public class ExceptionEndpointAdviceTest extends AbstractControllerTest {

    //TODO not finished
    @Ignore
    @Test
    public void test() throws Exception {
        UserDTO user = new UserDTO();
        user.setFirstName("FirstName");
        user.setLastName("LastName");
        user.setMatchingPassword("password");
        user.setEmail("emailWrongFormat");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isNoContent());
    }

}
