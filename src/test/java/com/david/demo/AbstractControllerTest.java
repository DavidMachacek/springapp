package com.david.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.david.demo.customer.CustomerApiController;
import com.david.demo.customer.CustomerConfiguration;
import com.david.demo.customer.CustomerRepository;
import com.david.demo.customer.CustomerService;
import com.david.demo.errorHandling.ExceptionConfiguration;
import com.david.demo.security.AuthorizationGlobalConfig;
import com.david.demo.user.UserConfiguration;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AbstractControllerTest.Config.class)
@WebAppConfiguration
public abstract class AbstractControllerTest {

    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void baseSetUp() {
        objectMapper.setSerializationInclusion(JsonInclude.Include.ALWAYS);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(wac)
                .alwaysDo(log())
                .build();
    }

    @Configuration
    @Import({
            CustomerConfiguration.class,
            ExceptionConfiguration.class,
            UserConfiguration.class,
            PersistenceConfiguration.class,
            AuthorizationGlobalConfig.class
    })
    @EnableWebMvc
    public static class Config {
        @Bean
        public CustomerApiController customerApiController(CustomerService customerService) {
            return new CustomerApiController(customerService);
        }
    }
}
