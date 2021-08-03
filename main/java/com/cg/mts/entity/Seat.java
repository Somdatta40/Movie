package com.cg.mts.entity;

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
@Table(name = "seat")
@ApiModel(description = "All details about the seat. ")
public class Seat
/**
 * Class Seat
 */

{
	@Id
	@SequenceGenerator(name = "seatSeqGen", sequenceName = "seatSeq", initialValue = 6001, allocationSize = 100)
	@GeneratedValue(generator = "seatSeqGen")
	
	@ApiModelProperty(notes = "The database generated seat ID")
	private long seatId;
	
	@ApiModelProperty(notes = "The  seat number")
	@NotNull(message="Please Enter Seat Number")
	@NotEmpty(message="Please Enter Seat Number. Seat Number can not be blank.")
	private String seatNumber;
	
	@ApiModelProperty(notes = "The  seat status")
	@NotNull(message="Please Enter Seat Status")
	private boolean seatStatus;

	@ManyToOne(cascade=CascadeType.ALL,fetch = FetchType.LAZY, targetEntity = Ticket.class)
	@JoinColumn(name="ticket_id", insertable = false, updatable = false)
	private Ticket ticket;

	/**
	 * Parameterized Constructor
	 * 
	 * @param seatNumber
	 * @param seatStatus
	 * @param ticket
	 */
	public Seat(String seatNumber, boolean seatStatus, Ticket ticket) {
		super();
		this.seatNumber = seatNumber;
		this.seatStatus = seatStatus;
		this.ticket = ticket;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Seat() {
		super();
	}

	/**
	 * 
	 * @return seatId
	 */
	public long getSeatId() {
		return seatId;
	}

	/**
	 * 
	 * @param seatId
	 */
	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}

	/**
	 * 
	 * @return seatNumber
	 */
	public String getSeatNumber() {
		return seatNumber;
	}

	/**
	 * 
	 * @param seatNumber
	 */
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	/**
	 * 
	 * @return seatStatus
	 */
	public boolean isSeatStatus() {
		return seatStatus;
	}

	/**
	 * 
	 * @param seatStatus
	 */
	public void setSeatStatus(boolean seatStatus) {
		this.seatStatus = seatStatus;
	}

	/**
	 * 
	 * @return ticket
	 */

	public Ticket getTicket() {
		return ticket;
	}

	/**
	 * 
	 * @param ticket
	 */
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

}
