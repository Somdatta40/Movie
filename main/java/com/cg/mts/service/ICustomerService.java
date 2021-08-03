package com.cg.mts.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Customer;

public interface ICustomerService {
	/**
	 * 
	 * @param customer
	 * @return customer
	 */
	public Customer addCustomer(Customer customer);
	/**
	 * 
	 * @param customer
	 * @param custId
	 * @return update customer
	 */
	public Customer updateCustomer(Customer customer,long custId);
	/**
	 * 
	 * @param custId
	 * @return custId
	 */
	public ResponseEntity<Customer> deleteCustomer(long custId);
	/**
	 * 
	 * @param custId
	 * @return custId
	 */
	public Customer viewCustomerById(long custId);
	/**
	 * 
	 * @return all customers
	 */
	public List<Customer> viewAllCustomers();
	//public List<Customer> viewCustomerList(int theatreid);

}
