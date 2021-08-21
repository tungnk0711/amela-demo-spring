package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.impl.CustomerService;
import com.codegym.service.ICustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitJupiterWebConfig;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

@WebAppConfiguration
@SpringJUnitJupiterWebConfig(CustomerControllerTestConfig.class)
public class CustomerControllerTest {

    private ICustomerService customerService = Mockito.mock(CustomerService.class);

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void find_All_Test() throws Exception{
        Customer first = new Customer(1L,"Nguyen","Tung");
        Customer second = new Customer(2L,"Hoang","Nam");
        when(customerService.findAll()).thenReturn(Arrays.asList(first, second));

        mockMvc.perform(get("/customer"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/list"))
                .andExpect(forwardedUrl("/customer/list"))
                //.andExpect(model().attribute("customers", hasSize(2)))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Nguyen")),
                                hasProperty("lastName", is("Tung"))
                        )
                )))
                .andExpect(model().attribute("customers", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("firstName", is("Hoang")),
                                hasProperty("lastName", is("Nam"))
                        )
                )));
        verify(customerService, times(1)).findAll();
        verifyNoMoreInteractions(customerService);

    }

    @Test
    public void findById_EntryFound() throws Exception {
        Customer first = new Customer(1L,"Nguyen","Tung");
        Customer second = new Customer(2L,"Hoang","Nam");
        when(customerService.findCustomerById(2L)).thenReturn(second);

        mockMvc.perform(get("/customer/{id}", 2L))
                .andExpect(status().isOk())
                .andExpect(view().name("/customer/detail"))
                .andExpect(forwardedUrl("/customer/detail"))
                .andExpect(model().attribute("customer", hasProperty("id", is(2L))))
                .andExpect(model().attribute("customer", hasProperty("firstName", is("Hoang"))))
                .andExpect(model().attribute("customer", hasProperty("lastName", is("Nam"))));
        verify(customerService, times(1)).findCustomerById(2L);
        verifyNoMoreInteractions(customerService);

    }

}
