package com.cg.mts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

import java.util.Objects;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

public class CustomerServiceTest 
{
	private CustomerService customerService;
    private ICustomerRepository ICustomerRepository;

    @BeforeEach
    void setUp() {
    	ICustomerRepository = mock(ICustomerRepository.class);
        customerService = new CustomerService();
    }

    @Test
    void shouldSavedCustomerSuccessfully() throws CustomerNotFoundException {
    	Customer customer = new Customer("abc","09903234157","abc");
        given(customerService.viewCustomerById(customer.getCustomerId())).willReturn(null);
        given(ICustomerRepository.save(customer)).willAnswer(invocation -> invocation.getArgument(0));

        Customer savedCustomer = customerService.addCustomer(customer);

        assertThat(savedCustomer).isNotNull();

        verify(ICustomerRepository).save(any(Customer.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingMobileNumber() {
    	Customer customer = new Customer("bcd","09903234157","xyz");
    	
        given(customerService.viewCustomerById(customer.getCustomerId())).willReturn(customer);

        assertThrows(CustomerNotFoundException.class, () -> {
            customerService.addCustomer(customer);
        });

        verify(ICustomerRepository, never()).save(any(Customer.class));
    }
}
