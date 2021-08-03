package com.cg.mts.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entity.Customer;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.ICustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/customer")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Customer in Online Movie Ticket Booking System")
@Validated
public class CustomerController extends GlobalLoginService
/**
 * Customer Controller
 */

{
	@Autowired
	private ICustomerService customerService;

	/**
	 * 
	 * @param customerId
	 * @return viewCustomer by customerId
	 */
	@ApiOperation(value = "Get customer by Id", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

	@GetMapping("/getcustomerbyid/{custId}")
	public Customer getCustomerById(
			@ApiParam(value = "Customer id from which customer object will be retrieved", required = true)@PathVariable("custId")@Positive long customerId) {
		if(flag==2) {
		return this.customerService.viewCustomerById(customerId);
		}
		return null;
	}

	/**
	 * 
	 * @param customer
	 * @return addCustomer 
	 * taking input
	 */
	  @ApiOperation(value = "Add a Customer")
	@PostMapping("/addcustomer")
	public Customer saveOrder(
	        @ApiParam(value = "customer object stored in database table", required = true)@Valid @RequestBody Customer customer) {
		  if(flag==2) {
		return this.customerService.addCustomer(customer);
		  }
		  return null;
	}

	/**
	 * 
	 * @param customer
	 * @param customerId
	 * @return updateCustomer by customerId
	 */
	  @ApiOperation(value = "Update Customers")
	@PutMapping("/updatecustomerbyid/{custId}")
	public Customer updateOrder(
			@ApiParam(value = "Update customer object", required = true)@Valid @RequestBody Customer customer, 
			@ApiParam(value = "customer Id to update customer object", required = true)@PathVariable("custId") long customerId) {
		  if(flag==2) {
		Customer existingOrder = this.customerService.updateCustomer(customer, customerId);
		return existingOrder;
		  }
		  return null;
	}

	/**
	 * 
	 * @param customerId
	 * @return deleteCustomer by customerId
	 */
	 @ApiOperation(value = "Delete Customer")
	@DeleteMapping("/deletecustomerbyid/{custId}")
	public ResponseEntity<Customer> deleteOrder(
			@ApiParam(value = "customer Id from which customer object will be deleted from database table", required = true)@PathVariable("custId") long customerId) {
		 if(flag==2) {
		 return this.customerService.deleteCustomer(customerId);
		 }
		 return null;
	}
}
