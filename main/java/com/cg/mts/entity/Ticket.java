package com.cg.mts.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@Entity
@Table(name = "ticket")
@ApiModel(description = "All details about the ticket. ")

public class Ticket
/**
 * Class Ticket
 */


{
	@Id
	@SequenceGenerator(name = "ticketSeqGen",sequenceName = "ticketSeq",initialValue = 9001,allocationSize = 100)
	@GeneratedValue(generator = "ticketSeqGen")
	
	@ApiModelProperty(notes = "The database generated ticket ID")
	private long ticketId;
	
	@ApiModelProperty(notes = "The  ticket status")
	@NotNull(message="Please Enter Ticket Status")
	private boolean ticketStatus;
	
	@ApiModelProperty(notes = "The screen name")
	@NotNull(message="Please Enter Screen Name")
	@NotEmpty(message="Please Enter Screen Name. Name can not be blank.")
	private String screenName;
	
	@ApiModelProperty(notes = "The  no of seats")
	@NotNull(message="Please Enter Number Of Seats")
	private int noOfSeats;
	
	@ApiModelProperty(notes = "The  total cost")
	private static double totalCost;
	
	@ApiModelProperty(notes = "The  price")
	private static final double price=600.45;
	/**
	 * Parameterized constructor
	 * 
	 * @Param ticketId
	 * @Param ticketStatus
	 * @Param screen name
	 * @Param no of seats
	 * @Param totalcost
	 * 
	 */
	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Theatre.class)
	@JoinColumn(name="theatre_id", insertable = false, updatable = false)
	private Theatre theatre;
	
	public Ticket( boolean ticketStatus, String screenName,Theatre theatre,int noOfSeats,double totalCost) {
		super();
		this.ticketStatus = ticketStatus;
		this.screenName = screenName;
		this.theatre=theatre;
		this.noOfSeats=noOfSeats;
		this.totalCost=totalCost;
	}
	/**
	 * Unparameterized constructor
	 */

	public Ticket() {
		super();
	}
	/**
	 * 
	 * @return ticketId
	 */

	public long getTicketId() {
		return ticketId;
	}
	/**
	 * 
	 * @param ticketId
	 */
	

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}
	/**
	 * 
	 * @return ticketstatus
	 */

	public boolean isTicketStatus() {
		return ticketStatus;
	}
	/**
	 * 
	 * @param ticketStatus
	 */

	public void setTicketStatus(boolean ticketStatus) {
		this.ticketStatus = ticketStatus;
	}
	/**
	 * 
	 * @return screen name
	 */

	public String getScreenName() {
		return screenName;
	}
	/**
	 * 
	 * @param screenName
	 */

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	/**
	 * 
	 * @return theatre
	 */

	public Theatre getTheatre() {
		return theatre;
	}
	/**
	 * 
	 * @param theatre
	 */

	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	/**
	 *  noOfSeats
	 * @return
	 */

	public int getNoOfSeats() {
		return noOfSeats;
	}
	/**
	 * 
	 * @param noOfSeats
	 */

	public void setNoOfSeats(int noOfSeats) {
		this.noOfSeats = noOfSeats;
	}
	/**
	 * 
	 * @return noOfSeats
	 */

	public double getTotalCost() {
		return (price*noOfSeats);
	}
	/**
	 * 
	 * @param totalCost
	 */

	public void setTotalCost(double totalCost) {
		this.totalCost = price*noOfSeats;
	}
	
	
	
	
}
