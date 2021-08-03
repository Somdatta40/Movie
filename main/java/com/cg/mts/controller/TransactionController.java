package com.cg.mts.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Positive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Ticket;
import com.cg.mts.entity.Transaction;
import com.cg.mts.entity.Wallet;
import com.cg.mts.repository.IBookingRepository;
import com.cg.mts.repository.ITransactionRepository;
import com.cg.mts.repository.IWalletRepository;
import com.cg.mts.service.GlobalLoginService;
import com.cg.mts.service.ITransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/transaction")
@Api(value = "Online Movie Ticket Booking System", description = "Operations pertaining to Transaction in Online Movie Ticket Booking System")
@Validated

public class TransactionController extends GlobalLoginService
/**
 * Transaction Controller
 */



{

	@Autowired 
	private ITransactionService transactionService;
	
	@Autowired
	private ITransactionRepository transactionRepository;
	
	@Autowired
	private IWalletRepository walletRepository;
	
	@Autowired
	private IBookingRepository bookingRepository;
	/**
	 * 
	 * @return get all transaction
	 */
	  @ApiOperation(value = "View the list of transaction", response = List.class)
	    @ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	    })
	@GetMapping("/getalltransactions")
	public List<Transaction> getAllTransactions(){
		  if(flag==1) {
		return this.transactionService.viewAllTransactions();
		  }
		  return null;
	}
	/**
	 * 
	 * @param wallet
	 * @return get all transaction by walletId
	 */
	@ApiOperation(value = "Get transaction by walletId")
	@GetMapping("/gettransactionbywid/{wId}")
	public List<Transaction> getTransactionByWallet(
			  @ApiParam(value = "wallet id from which transaction object will be retrieved", required = true)@PathVariable("wId")Wallet wallet){
		if(flag==2||flag==1) {
		return this.transactionService.findByWallet(wallet);
		}
		return null;
	}
	/**
	 * 
	 * @param booking
	 * @return get transaction by booking
	 */
	@ApiOperation(value = "Get transaction by bookingId")
	@GetMapping("/gettransactionbybid/{bId}")
	public Transaction getTransactionByBooking(
	        @ApiParam(value = "booking id from which transaction object will be retrieved", required = true)@PathVariable("bId")Booking booking){
		if(flag==1||flag==2) {
		return this.transactionService.findByBooking(booking);
		}
		return null;
	}
	/**
	 * 
	 * @param transactionId
	 * @return get transaction by transactionId
	 */
	@ApiOperation(value = "Get transaction by transactionId")
	@GetMapping("/gettransactionbyid/{trId}")
	public Transaction getTransactionById(
	        @ApiParam(value = "transaction id from which transaction object will be retrieved", required = true)@PathVariable("trId") long transactionId){
		if(flag==1||flag==2) {
		return this.transactionService.viewTransactionById(transactionId);
		}
		return null;
	}
	/**
	 * 
	 * @param tmode
	 * @return get transaction mode
	 */ 
	@ApiOperation(value = "Get transaction by mode")
	@GetMapping("/getbytransactionbymode/{tMode}")
	public List<Transaction> getTransactionByTmode(
	        @ApiParam(value = "transaction mode from which transaction object will retrieve", required = true)@PathVariable("tMode") String tmode){
		if(flag==1||flag==2) {
		return this.transactionService.findByTransactionMode(tmode);
		}
		return null;
	}
	
	
	@ApiOperation(value = "Update wallet by id")
	@PutMapping("/updatetransactionbywid/{wId}/{trId}")
	public Transaction updateTransactionByWallet(
			@ApiParam(value = "wallet Id to update transaction object", required = true)@PathVariable("wId") Wallet wallet,
			@ApiParam(value = "transaction Id to update transaction object", required = true)@PathVariable("trId") Long transactionId,
			@ApiParam(value = "Update transaction object", required = true)@Valid@RequestBody Transaction transaction) {
		Transaction existingTransaction = this.transactionService.updateTransactionByWallet(transaction, transactionId, wallet);
	//	existingTransaction = this.updateBookingTotalAmount(existingBooking, bookingId);
		if(flag==2) {
		return existingTransaction;
		}
		return null;
	}
	/**
	 * 
	 * @param transaction
	 * @param bookingId
	 * @return add transaction by booking Id
	 */
	@ApiOperation(value = "Add transaction")
	@PostMapping("/addtransactionbybid/{bId}")
	public Optional<Object> saveTransactionByBooking(
			  @ApiParam(value = "transaction object stored in database table", required = true)@Valid @RequestBody Transaction transaction,
			  @ApiParam(value = "booking Id to add transaction object", required = true)@PathVariable("bId")long bookingId) {
		if(flag==2) {
		return this.bookingRepository.findById(bookingId).map(booking->{transaction.setBooking(booking);
		return transactionRepository.save(transaction);
		});
		}
		return null;
	}
	/**
	 * update transaction by transactionId
	 */
	 @ApiOperation(value = "Update Transaction")
	@PutMapping("/updatetransactionbyid/{trid}")
	public Transaction updateTransaction(
			@ApiParam(value = "Update transaction object", required = true)@Valid @RequestBody Transaction transaction,
			@ApiParam(value = "transaction Id to transaction object", required = true) @PathVariable("trid")long transactionId) {
		Transaction existingTransaction= this.transactionService.updateTransaction(transaction, transactionId);
		 existingTransaction=this.updateTransactionTotalCost( existingTransaction, transactionId);
		 if(flag==2) {
		 return existingTransaction;       
		 }
		 return null;
	}

	 public Transaction updateTransactionTotalCost(Transaction transaction,long transactionId){
		 if(flag==2) {	
		 Transaction existingTransaction= this.transactionService.updateTransactionTotalCost( transaction, transactionId);
			return existingTransaction;
		 }
		 return null;
		}
	 /**
	  * 
	  * @param transactionId
	  * @return delete transaction by transactionId
	  */
	 
	@DeleteMapping("/deletetransactionbyid/{trid}")
	public ResponseEntity<Transaction> deleteTransaction(
			@ApiParam(value = "Transaction Id from which transaction object will be deleted from database table", required = true)@PathVariable("trid")@Positive long transactionId){
		if(flag==2) {
		return this.transactionService.deleteTransaction(transactionId);
		}
		return null;
	}
}
