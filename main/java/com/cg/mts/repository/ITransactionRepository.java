package com.cg.mts.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Transaction;
import com.cg.mts.entity.Wallet;

@Repository
public interface ITransactionRepository extends JpaRepository<Transaction,Long>{

	/**
	 * 
	 * @param tmode
	 * @return details of transaction by transaction mode
	 */
	public List<Transaction> findByTransactionMode(String tmode);
	/**
	 * 
	 * @param wallet
	 * @return details of transaction by wallet
	 */
	public List<Transaction> findByWallet(Wallet wallet);
	/**
	 * 
	 * @param booking
	 * @return details of transaction by booking
	 */
	public Transaction findByBooking(Booking booking);
}
