package com.cg.mts.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "transaction")
@ApiModel(description = "All details about the transaction. ")
public class Transaction

/**
 * Class Transaction
 */
{
	@Id
	@SequenceGenerator(name = "transactionSeqGen", sequenceName = "transactionSeq", initialValue = 1101, allocationSize = 100)
	@GeneratedValue(generator = "transactionSeqGen")
	@Column(name = "transaction_Id")
	
	@ApiModelProperty(notes = "The database generated transaction ID")
	private long transactionId;
	
	@ApiModelProperty(notes = "The transactionMode")
	@NotNull(message="Please Enter Transaction Mode")
	@NotEmpty(message="Please Enter Transaction Mode.Transaction Mode can not be blank.")
	private String transactionMode;
	
	@ApiModelProperty(notes = "The transactionStatus")
	@NotNull(message="Please Enter Transaction Status")
	@NotEmpty(message="Please Enter Transaction Status.Transaction Status can not be blank.")
	private String transactionStatus;
	
	@ApiModelProperty(notes = "The transactionAmount")
	private double transactionAmount;
	/**
	 * Parameterized Constructor
	 * 
	 * @Param transactionId
	 * @Param transactionMode
	 * @Param transactionStatus
	 * @Param transactionAmount
	 */

	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="wallet_id")
	private Wallet wallet;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "booking_id")
	private Booking booking;

	/**
	 * Unparametrized Constructor
	 */

	public Transaction() {
		super();
	}

	

	public Transaction(String transactionMode,String transactionStatus,double transactionAmount, Wallet wallet, Booking booking) {
		super();
		this.transactionMode = transactionMode;
		this.transactionStatus = transactionStatus;
		this.transactionAmount = transactionAmount;
		this.wallet = wallet;
		this.booking = booking;
	}



	/**
	 * 
	 * @return transactionAmount
	 */

	public double getTransactionAmount() {
		return transactionAmount;
	}

	/**
	 * 
	 * @param transactionAmount
	 */

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/**
	 * 
	 * @return transactionId
	 */

	public long getTransactionId() {
		return transactionId;
	}

	/**
	 * 
	 * @param transactionId
	 */

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * 
	 * @return transactionMode
	 */

	public String getTransactionMode() {
		return transactionMode;
	}

	/**
	 * 
	 * @param transactionMode
	 */

	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}

	/**
	 * 
	 * @return transactionStatus
	 */

	public String getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * 
	 * @param transactionStatus
	 */

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * 
	 * @return wallet
	 */

	public Wallet getWallet() {
		return wallet;
	}

	/**
	 * 
	 * @param wallet
	 */

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	/**
	 * 
	 * @return booking
	 */

	public Booking getBooking() {
		return booking;
	}

	/**
	 * 
	 * @param booking
	 */

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}