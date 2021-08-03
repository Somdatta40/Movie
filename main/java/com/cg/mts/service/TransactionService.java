package com.cg.mts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Transaction;
import com.cg.mts.entity.Wallet;
import com.cg.mts.exception.BookingNotFoundException;
import com.cg.mts.exception.TransactionNotFoundException;
import com.cg.mts.repository.ITransactionRepository;
@Service
public class TransactionService implements ITransactionService{

	@Autowired
	private ITransactionRepository transactionRepository;
	/**
	 * save transaction
	 */
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return this.transactionRepository.save(transaction);
	}
	/**
	 * update transaction by transactionId
	 */

	@Override
	public Transaction updateTransaction(Transaction transaction,long transactionId) {
		// TODO Auto-generated method stub
		Transaction existingTransaction=this.transactionRepository.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("Transaction not found"+transactionId));
		existingTransaction.setTransactionMode(transaction.getTransactionMode());
		existingTransaction.setTransactionStatus(transaction.getTransactionStatus());
		return this.transactionRepository.save(existingTransaction);
	}
	/**
	 * delete transaction by transactionId
	 */

	@Override
	public ResponseEntity<Transaction> deleteTransaction(long transactionId)  {
		// TODO Auto-generated method stub
		Transaction existingTransaction=this.transactionRepository.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("Transaction not found with id"+transactionId));
		this.transactionRepository.delete(existingTransaction);
		return ResponseEntity.ok().build();
	}
	/**
	 * view transaction by transactionId
	 */

	@Override
	public Transaction viewTransactionById(long transactionId) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("Transaction not found with id"+transactionId));
	}
	/**
	 * view all transaction
	 */

	@Override
	public List<Transaction> viewAllTransactions() {
		// TODO Auto-generated method stub
		return this.transactionRepository.findAll();
	}
	/**
	 * find by transaction mode
	 */

	@Override
	public List<Transaction> findByTransactionMode(String tmode) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findByTransactionMode(tmode);
	}
	/**
	 * find by wallet
	 */

	@Override
	public List<Transaction> findByWallet(Wallet wallet) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findByWallet(wallet);
	}
	/**
	 * find by booking
	 */

	@Override
	public Transaction findByBooking(Booking booking) {
		// TODO Auto-generated method stub
		return this.transactionRepository.findByBooking(booking);
	}
	/**
	 * update transaction total cost by transaction Id
	 */

	@Override
	public Transaction updateTransactionTotalCost(Transaction transaction, long transactionId) {
		// TODO Auto-generated method stub
		Transaction existingTransaction=this.transactionRepository.findById(transactionId).orElseThrow(()-> new TransactionNotFoundException("Transaction not found"+transactionId));
		existingTransaction.setTransactionAmount(transaction.getBooking().getTotalAmount());
		
		return this.transactionRepository.save(existingTransaction);
	}
	
	/**
	 * update transaction by wallet
	 */
	@Override
	public Transaction updateTransactionByWallet(Transaction transaction, long transactionId, Wallet wallet) {
		// TODO Auto-generated method stub
		Transaction existingTransaction = this.transactionRepository.findById(transactionId)
				.orElseThrow(() -> new TransactionNotFoundException("Transaction not found" + transactionId));
		existingTransaction.setWallet(wallet);
		  wallet.setBalance((wallet.getBalance())-(existingTransaction.getTransactionAmount()));
		  existingTransaction.setWallet(wallet);
		 
		 
		return this.transactionRepository.save(existingTransaction);
	}

}
