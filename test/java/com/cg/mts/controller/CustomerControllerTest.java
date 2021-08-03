package com.cg.mts.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.cg.mts.entity.Customer;
import com.cg.mts.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
@WebMvcTest(controllers = CustomerController.class)
@ActiveProfiles("test")
public class CustomerControllerTest {
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @Autowired
    private ObjectMapper objectMapper;

    private List<Customer> customerList;
    
    @BeforeEach
    void setUp() {
        this.customerList = new ArrayList<>();
        this.customerList.add(new Customer("RamaCust","0903234157","Kolkata"));
        this.customerList.add(new Customer("RamaCust1","0903234168","Mumbai"));
        this.customerList.add(new Customer("RamaCust2,","0903234497","Delhi"));

    }
        
        @Test
	    void shouldFetchAllCustomer() throws Exception {
	        given(customerService.viewAllCustomers()).willReturn(this.customerList);

	        this.mockMvc.perform(get("/customer/getallcustomers"))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.size()", is(customerList.size())));
	    }
        @Test
	    void shouldFindCustomerById() throws Exception {
	        long custId = 3001;
	        Customer customer = new Customer("RamaCust","0903234157","Kolkata");
	        given(customerService.viewCustomerById(custId)).willReturn(customer);

	        this.mockMvc.perform(get("/customers/getcustomerbyid/{custId}", custId))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
	                .andExpect(jsonPath("$.mobileNumber", is(customer.getMobileNumber())))
	                .andExpect(jsonPath("$.city", is(customer.getCity())))
	        ;
	    }
        @Test
	    void shouldReturn404WhenFetchingNonExistingCustomer() throws Exception {
	        long custId = 3001;
	        given(customerService.viewCustomerById(custId)).willReturn(null);

	        this.mockMvc.perform(get("/customers/getcustomerbyid/{custId}", custId))
	                .andExpect(status().isNotFound());

	    }
        void shouldCreateNewCustomer() throws Exception {
	        given(customerService.addCustomer(any(Customer.class))).willAnswer((invocation) -> invocation.getArgument(0));

	        Customer customer = new Customer("RamaCust","0903234157","Kolkata");
	        this.mockMvc.perform(post("/customers/getcustomerbyid/{custId}")
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(customer)))
	                .andExpect(status().isCreated())
	                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
	                .andExpect(jsonPath("$.mobileNumber", is(customer.getMobileNumber())))
	                .andExpect(jsonPath("$.city", is(customer.getCity())))	              
	                ;

	    }
        
	  
        @Test
	    void shouldUpdateCustomer() throws Exception {
	        long custId = 3001;
	        Customer customer = new Customer("RamaCust","0903234157","Kolkata");
	        given(customerService.viewCustomerById(custId)).willReturn(customer);
	        given(customerService.updateCustomer(any(Customer.class), custId)).willAnswer((invocation) -> invocation.getArgument(0));

	        this.mockMvc.perform(put("/getcustomerbyid/{custId}", customer.getCustomerId())
	                .contentType(MediaType.APPLICATION_JSON_UTF8)
	                .content(objectMapper.writeValueAsString(customer)))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
	                .andExpect(jsonPath("$.mobileNumber", is(customer.getMobileNumber())))
	                .andExpect(jsonPath("$.city", is(customer.getCity())))
	                ;
	    
        }
        @Test
	    void shouldDeleteCustomer() throws Exception {
	        long custId = 3001;
	        Customer customer = new Customer("RamaCust","0903234157","Kolkata");
	        given(customerService.viewCustomerById(custId)).willReturn(customer);
	        doNothing().when(customerService).deleteCustomer(customer.getCustomerId());

	        this.mockMvc.perform(delete("/getcustomerbyid/{custId}", customer.getCustomerId()))
	                .andExpect(status().isOk())
	                .andExpect(jsonPath("$.customerName", is(customer.getCustomerName())))
	                .andExpect(jsonPath("$.mobileNumber", is(customer.getMobileNumber())))
	                .andExpect(jsonPath("$.city", is(customer.getCity())))
	                ;

	    }
        @Test
	    void shouldReturn404WhenDeletingNonExistingCustomer() throws Exception {
	        long custId = 3001;
	        given(customerService.viewCustomerById(custId)).willReturn(null);

	        this.mockMvc.perform(delete("/getcustomerbyid/{custId}", custId))
	                .andExpect(status().isNotFound());

	    }

}
