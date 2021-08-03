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
@Table(name = "theatre")
@ApiModel(description = "All details about the theatre. ")
public class Theatre
/**
 * Class Theatre
 */
{
	@Id
	@SequenceGenerator(name = "theatreSeqGen", sequenceName = "theatreSeq", initialValue = 8001, allocationSize = 100)
	@GeneratedValue(generator = "theatreSeqGen")
	@Column(name = "theatre_id")
	
	@ApiModelProperty(notes = "The database generated theatre ID")
	private long theatreId;
	
	@ApiModelProperty(notes = "The theatre name")
	@NotNull(message="Please Enter Theatre Name")
	@NotEmpty(message="Please Enter Theatre Name. Name can not be blank.")
	private String theatreName;
	
	@ApiModelProperty(notes = "The theatre contact")
	@Size(min = 11, max = 11, message = "Mobile number must be a 11-digit string.")
	@Pattern(regexp="0[0-9]{10}",message="Contact should start with 0.") 
	private String theatreContact;

	/**
	 * 
	 * Parameterized Constructor
	 * 
	 * @param theatreName
	 * @param theatreContact
	 */
	public Theatre(String theatreName, String theatreContact) {
		super();

		this.theatreName = theatreName;
		this.theatreContact = theatreContact;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Theatre() {
		super();
	}

	/**
	 * 
	 * @return theatreId
	 */
	public long getTheatreId() {
		return theatreId;
	}

	/**
	 * 
	 * @param theatreId
	 */
	public void setUserId(long theatreId) {
		this.theatreId = theatreId;
	}

	/**
	 * 
	 * @return theatreName
	 */
	public String getTheatreName() {
		return theatreName;
	}

	/**
	 * 
	 * @param theatreName
	 */
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}

	/**
	 * 
	 * @return theatreContact
	 */
	public String getTheatreContact() {
		return theatreContact;
	}

	/**
	 * 
	 * @param theatreContact
	 */
	public void setTheatreContact(String theatreContact) {
		this.theatreContact = theatreContact;
	}

}
