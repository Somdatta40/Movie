package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Customer;
import com.cg.mts.exception.CustomerNotFoundException;
import com.cg.mts.repository.ICustomerRepository;

@Service
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerRepository customerRepository;

	/**
	 * add Customer
	 */
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return this.customerRepository.save(customer);
	}

	/**
	 * update Customer by custId
	 */
	@Override
	public Customer updateCustomer(Customer customer, long custId) {
		Customer existingAdmin = this.customerRepository.findById(custId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found" + custId));
		existingAdmin.setCustomerName(customer.getCustomerName());
		existingAdmin.setMobileNumber(customer.getMobileNumber());
		return this.customerRepository.save(existingAdmin);
	}

	/**
	 * delete Customer
	 */
	@Override
	public ResponseEntity<Customer> deleteCustomer(long custId) {
		// TODO Auto-generated method stub
		Customer existingAdmin = this.customerRepository.findById(custId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found" + custId));
		this.customerRepository.delete(existingAdmin);
		return ResponseEntity.ok().build();
	}

	/**
	 * view Customer by custId
	 */
	@Override
	public Customer viewCustomerById(long custId) {
		return this.customerRepository.findById(custId)
				.orElseThrow(() -> new CustomerNotFoundException("Customer not found with id" + custId));
	}

	/**
	 * view all Customer
	 */
	@Override
	public List<Customer> viewAllCustomers() {
		return this.customerRepository.findAll();
	}

}
