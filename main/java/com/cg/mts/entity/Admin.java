package com.cg.mts.entity;

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
@Table(name = "admin")
@ApiModel(description = "All details about the admin. ")
public class Admin
/**
 * Class Admin
 */

{
	@Id
	@SequenceGenerator(name = "adminSeqGen", sequenceName = "adminSeq", initialValue = 1001, allocationSize = 100)
	@GeneratedValue(generator = "adminSeqGen")
	@Column(name = "admin_id")
	
	@ApiModelProperty(notes = "The database generated admin ID")
	private long adminId;
	
	@ApiModelProperty(notes = "The  admin name")
	@NotNull(message="Please Enter Admin Name")
	@NotEmpty(message="Please Enter Admin Name. Name can not be blank.")
	private String adminName;
	
	@Size(min = 11, max = 11, message = "Mobile number must be a 11-digit string.")
	@Pattern(regexp="0[0-9]{10}",message="Contact should start with 0.") 
	@ApiModelProperty(notes = "The  admin contact")
	private String adminContact;

	/**
	 * Parameterized Constructor
	 * 
	 * @param adminName
	 * @param adminContact
	 */
	public Admin(String adminName, String adminContact) {
		super();

		this.adminName = adminName;
		this.adminContact = adminContact;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Admin()

	{
		super();
	}

	/**
	 * 
	 * @return adminId
	 */
	public long getAdminId() {
		return adminId;
	}

	/**
	 * 
	 * @param adminId
	 */
	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	/**
	 * 
	 * @return adminName
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * 
	 * @param adminName
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * 
	 * @return adminContact
	 */
	public String getAdminContact() {
		return adminContact;
	}

	/**
	 * 
	 * @param adminContact
	 */
	public void setAdminContact(String adminContact) {
		this.adminContact = adminContact;
	}

}
