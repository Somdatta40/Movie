package com.cg.mts.entity;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




@Entity

@Table(name = "userdetails")
@ApiModel(description = "All details about the user. ")
public class User
/**
 * Class User
 */

{
	@Id
	@SequenceGenerator(name = "userSeqGen",sequenceName = "userSeq",initialValue = 1201,allocationSize = 100)
	@GeneratedValue(generator = "userSeqGen")
	@Column(name ="user_id")
	
	@ApiModelProperty(notes = "The database generated user ID")
	private long userId;
	
	@ApiModelProperty(notes = "The  password")
	@Size(min = 6, message = "Password must be a greater than 6 characters")
	//(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^A-Za-z0-9])(?=.{8,})
	@Pattern(regexp="[a-zA-z0-9]{8,}",message="Contact should start with 0.") 
	private String password;
	
	@ApiModelProperty(notes = "The  role")
	@NotNull(message="Please Enter Admin/Customer")
	@NotEmpty(message="Please Enter Admin/Customer.Role can not be blank.")
	private String role;
	/**
	 * Parametrized constructor
	 * 
	 * @Param userId
	 * @Param password
	 * @Param role
	 */
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="type_id")
	private Admin admin;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cust_Id")
	private Customer customer;
	
	public User(String password, String role) {
		super();
		this.password = password;
		this.role = role;
	}
	/**
	 * Unparametrized constructor
	 */

	public User() {
		super();
	}
	/**
	 * 
	 * @return userId
	 */

	public long getUserId() {
		return userId;
	}
	/**
	 * 
	 * @param userId
	 */

	public void setUserId(long userId) {
		this.userId = userId;
	}
	/**
	 * 
	 * @return password
	 */

	public String getPassword() {
		return password;
	}
	/**
	 * 
	 * @param password
	 */

	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 
	 * @return role
	 */

	public String getRole() {
		return role;
	}
	/**
	 * 
	 * @param role
	 */

	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * 
	 * @return admin
	 */

	public Admin getAdmin() {
		return admin;
	}
	/**
	 * 
	 * @param admin
	 */

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	/**
	 * 
	 * @return customer
	 */

	public Customer getCustomer() {
		return customer;
	}
	/**
	 * 
	 * @param customer
	 */

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	

}
