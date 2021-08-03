package com.cg.mts.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "customer")
@ApiModel(description = "All details about the customer. ")
public class Customer
/**
 * Class Customer
 */

{
	@ApiModelProperty(notes = "The database generated customer ID")
	@Id
	@SequenceGenerator(name = "custSeqGen", sequenceName = "custSeq", initialValue = 3001, allocationSize = 1)
	@GeneratedValue(generator = "custSeqGen")
	@Column(name = "cust_Id")
	
	private long customerId;
	
	@ApiModelProperty(notes = "The customer name")
	@NotNull(message="Please Enter Customer Name")
	@NotEmpty(message="Please Enter Customer Name. Name can not be blank.")
	private String customerName;
	
	@Size(min = 11, max = 11, message = "Mobile number must be a 11-digit string.")
	@Pattern(regexp="0[0-9]{10}",message="Contact should start with 0.") 
	@ApiModelProperty(notes = "The  mobile number")
	private String mobileNumber;
	
	@ApiModelProperty(notes = "The city name")
	@NotNull(message="Please Enter City")
	@NotEmpty(message="Please Enter City.City can not be blank.")
	private String city;
	/**
	 * Parameterized Constructor
	 * 
	 * @param customerName
	 * @param mobileNumber
	 * @param city
	 */

	public Customer(String customerName,String mobileNumber,String city) {
		super();
		this.customerName = customerName;
		this.mobileNumber = mobileNumber;
		this.city = city;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Customer() {
		super();
	}


	/**
	 * 
	 * @return customerId
	 */
	public long getCustomerId() {
		return customerId;
	}

	/**
	 * 
	 * @param customerId
	 */
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	/**
	 * 
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * 
	 * @param customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * 
	 * @return mobileNumber
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * 
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}
	
	/**
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}
	
	

}
