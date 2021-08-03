 package com.cg.mts.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "booking")
@ApiModel(description = "All details about the booking. ")
public class Booking
/**
 * Class Booking
 */

{

	@Id
	@SequenceGenerator(name = "bookingSeqGen", sequenceName = "bookingSeq", initialValue = 2001, allocationSize = 100)
	@GeneratedValue(generator = "bookingSeqGen")
	
	@ApiModelProperty(notes = "The database generated booking Id")
	private long bookingId;
	
	@ApiModelProperty(notes = "The database generated movie Id")
	@NotNull(message="Please Enter movie Id.")
	private long movieId;
	
	@ApiModelProperty(notes = "The database generated show Id ")
	@NotNull(message="Please Enter show Id.")
	private long showId;
	
	@ApiModelProperty(notes = "The booking date")
	@JsonFormat(pattern="yyyy-MM-dd")
	@NotNull(message="Please provide a date.")
	private LocalDate bookingDate;
	
	@ApiModelProperty(notes = "The  status")
	private String status = "Available";
	
	@ApiModelProperty(notes = "The total amount")
	private double totalAmount;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticket_Id")
	private Ticket ticket;

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="customer_id")
	private Customer customer;

	/**
	 * Parameterized Constructor
	 * 
	 * @param movieId
	 * @param showId
	 * @param bookingDate
	 * @param status
	 * @param ticket
	 * @param customer
	 * @param totalAmount
	 */
	public Booking(long movieId, long showId, LocalDate bookingDate, String status, Ticket ticket,
			Customer customer, double totalAmount) {
		super();
		this.movieId = movieId;
		this.showId = showId;
		this.bookingDate = bookingDate;
		this.status = status;
		this.customer = customer;
		this.ticket = ticket;
		this.totalAmount = totalAmount;
	}

	/**
	 * Unparameterized Constructor
	 */
	public Booking() {
		super();
	}

	/**
	 * 
	 * @return bookingId
	 */
	public long getBookingId() {
		return bookingId;
	}

	/**
	 * 
	 * @param bookingId
	 */
	public void setBookingId(long bookingId) {
		this.bookingId = bookingId;
	}

	/**
	 * 
	 * @return movieId
	 */
	public long getMovieId() {
		return movieId;
	}

	/**
	 * 
	 * @param movieId
	 */
	public void setMovieId(long movieId) {
		this.movieId = movieId;
	}

	/**
	 * 
	 * @return showId
	 */
	public long getShowId() {
		return showId;
	}

	/**
	 * 
	 * @param showId
	 */
	public void setShowId(long showId) {
		this.showId = showId;
	}

	/**
	 * 
	 * @return bookingDate
	 */
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	/**
	 * 
	 * @param bookingDate
	 */
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
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

	/**
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * 
	 * @param status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * 
	 * @return totalAmount
	 */
	public double getTotalAmount() {
		return totalAmount;
	}

	/**
	 * 
	 * @param totalAmount
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

}
