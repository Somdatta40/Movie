package com.cg.mts.service;

import java.util.List;


import org.springframework.http.ResponseEntity;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Ticket;
import com.cg.mts.entity.Transaction;
import com.cg.mts.entity.Wallet;




public interface ITransactionService {
	/**
	 * 
	 * @param transaction
	 * @return saveTransaction
	 */

	public Transaction saveTransaction(Transaction transaction);
	/**
	 * 
	 * @param transaction
	 * @param transactionId
	 * @return updateTransaction
	 */
	public Transaction updateTransaction(Transaction transaction, long transactionId) ;
	/**
	 * 
	 * @param transactionId
	 * @return deleteTransaction
	 */
	public ResponseEntity<Transaction> deleteTransaction(long transactionId) ;
	/**
	 * 
	 * @param transactionId
	 * @return viewTransactionById
	 */
	public Transaction viewTransactionById(long transactionId) ;
	/**
	 * 
	 * @return viewAllTransactions
	 */
	public List<Transaction> viewAllTransactions();
	/**
	 * 
	 * @param tmode
	 * @return findByTmode
	 */
	public List<Transaction> findByTransactionMode(String tmode);
	/**
	 * 
	 * @param wallet
	 * @return findByWallet
	 */
	public List<Transaction> findByWallet(Wallet wallet);
	/**
	 * 
	 * @param booking
	 * @return findByBooking
	 */
	public Transaction findByBooking(Booking booking);
	/**
	 * 
	 * @param transaction
	 * @param transactionId
	 * @return updateTransactionTotalCost
	 */
	public Transaction updateTransactionTotalCost(Transaction transaction, long transactionId);
	
	/**
	 * 
	 * @param transaction
	 * @param transactionId
	 * @param wallet
	 * @return update by wallet
	 */
	public Transaction updateTransactionByWallet(Transaction transaction, long transactionId,Wallet wallet);
	
}
