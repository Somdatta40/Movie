package com.cg.mts.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Transaction;
import com.cg.mts.entity.Wallet;
import com.cg.mts.exception.TransactionNotFoundException;
import com.cg.mts.repository.ITransactionRepository;

public class TransactionServiceTest 
{
	private TransactionService transactionService;
    private ITransactionRepository transactionRepository;
    
    static Wallet wallet=new Wallet();
	static Booking booking=new Booking();
    
    @BeforeEach
    void setUp() {
    	transactionRepository = mock(ITransactionRepository.class);
    	transactionService = new TransactionService();
    }

    @Test
    void shouldSavedUserSuccessfully() throws TransactionNotFoundException {
    	Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.viewTransactionById(transaction.getTransactionId())).willReturn(null);
        given(transactionRepository.save(transaction)).willAnswer(invocation -> invocation.getArgument(0));

        Transaction savedTransaction = transactionService.saveTransaction(transaction);

        assertThat(savedTransaction).isNotNull();

        verify(transactionRepository).save(any(Transaction.class));
    }

    @Test
    void shouldThrowErrorWhenSaveTransactionWithExistingBooking() {
    	Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.findByBooking(transaction.getBooking())).willReturn(null);

        assertThrows(TransactionNotFoundException.class, () -> {
        	transactionService.saveTransaction(transaction);
        });

        verify(transactionRepository, never()).save(any(Transaction.class));
    }
}
