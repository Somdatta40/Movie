package com.cg.mts.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "wallet")
@ApiModel(description = "All details about the waller. ")

public class Wallet
/**
 * Class Wallet
 */


{
	@Id
	@SequenceGenerator(name = "walletSeqGen",sequenceName = "walletSeq",initialValue = 1301,allocationSize = 100)
	@GeneratedValue(generator = "walletSeqGen")
	
	@ApiModelProperty(notes = "The database generated wallet Id")
	private long walletId;
	
	@ApiModelProperty(notes = "The  balance")
	private double balance;
	
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="cust_Id")
	private Customer customer;
	
	/**
	 * Unparameterised Constructor
	 */
	public Wallet() {
		super();
	}
	/**
	 * Parametrized constructor
	 * 
	 * @Param walletId
	 * @Param balance
	 */
	public Wallet(long walletId, double balance) {
		super();
		this.walletId = walletId;
		this.balance = balance;
	}
	/**
	 * 
	 * @return walletId
	 */

	public long getWalletId() {
		return walletId;
	}
	/**
	 * 
	 * @param walletId
	 */

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}
	/**
	 * 
	 * @return balance
	 */

	public double getBalance() {
		return balance;
	}
	/**
	 * 
	 * @param balance
	 */

	public void setBalance(double balance) {
		this.balance = balance;
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
