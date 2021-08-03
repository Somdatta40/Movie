package com.cg.mts.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.cg.mts.entity.Booking;
import com.cg.mts.entity.Transaction;
import com.cg.mts.entity.Wallet;
import com.cg.mts.service.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = TransactionController.class)
@ActiveProfiles("test")
public class TransactionControllerTest 
{
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;
    
    private List<Transaction> transactionList;
    static Wallet wallet=new Wallet();
	static Booking booking=new Booking();
	
    @BeforeEach
    void setUp() 
    {
        this.transactionList = new ArrayList<>();
        this.transactionList.add(new Transaction("Online","Paid",265.89,wallet,booking));
        this.transactionList.add(new Transaction("Online","Ongoing",500,wallet,booking));
        this.transactionList.add(new Transaction("Online","Paid",1000,wallet,booking));
    }
    
    @Test
    void shouldFetchAllTransactions() throws Exception {
        given(transactionService.viewAllTransactions()).willReturn(this.transactionList);

        this.mockMvc.perform(get("/transaction/getalltransactions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(transactionList.size())));
    }
    
    @Test
    void shouldFindTransactionById() throws Exception {
        long transactionId=1101;
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.viewTransactionById(transactionId)).willReturn(transaction);

        this.mockMvc.perform(get("/transaction/gettransactionbyid/{trId}", transactionId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingTransaction() throws Exception {
        long transactionId = 1101;
        given(transactionService.viewTransactionById(transactionId)).willReturn(null);

        this.mockMvc.perform(get("/transaction/gettransactionbyid/{trId}", transactionId))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldAddNewTransaction() throws Exception {
    	long bookingId=2001;
        given(transactionService.saveTransaction(any(Transaction.class))).willAnswer((invocation) -> invocation.getArgument(0));

        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        this.mockMvc.perform(post("/transaction/addtransactionbybid/{bId}",bookingId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));

    }

    @Test
    void shouldReturn400WhenCreateNewUserWithoutWallet() throws Exception {
    	Transaction transaction = new Transaction("Online","Paid",265.89,null,booking);
    	long bookingId=2001;
        this.mockMvc.perform(post("/transaction/addtransactionbybid/{bId}",bookingId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isBadRequest())
                .andExpect(header().string("Content-Type", is("application/problem+json")))
                .andExpect(jsonPath("$.type", is("https://zalando.github.io/problem/constraint-violation")))
                .andExpect(jsonPath("$.title", is("Constraint Violation")))
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.violations", hasSize(1)))
                .andExpect(jsonPath("$.violations[0].field", is("wallet")))
                .andExpect(jsonPath("$.violations[0].message", is("Wallet should not be empty")))
                .andReturn();
    }
    
    @Test
    void shouldUpdateTransaction() throws Exception {
    	long transactionId = 1101;
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.viewTransactionById(transactionId)).willReturn(transaction);
        given(transactionService.updateTransaction(any(Transaction.class),transactionId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/transaction/updatetransactionbyid/{trid}", transaction.getTransactionId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingTransaction() throws Exception {
        long transactionId = 1101;
        given(transactionService.viewTransactionById(transactionId)).willReturn(null);
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);

        this.mockMvc.perform(put("/api/customers/{id}", transactionId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isNotFound());

    }

    
    @Test
    void shouldDeleteTransaction() throws Exception {
    	long transactionId = 1101;
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.viewTransactionById(transactionId)).willReturn(transaction);
        doNothing().when(transactionService).deleteTransaction(transaction.getTransactionId());

        this.mockMvc.perform(delete("/transaction/deletetransactionbyid/{trid}", transaction.getTransactionId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));

    }

    @Test
    void shouldReturn404WhenDeletingNonExistingTransaction() throws Exception {
        long transactionId = 1101;
        given(transactionService.viewTransactionById(transactionId)).willReturn(null);

        this.mockMvc.perform(delete("/transaction/deletetransactionbyid/{trid}", transactionId))
                .andExpect(status().isNotFound());

    }

    
    
    
    

    @Test
    void shouldUpdateTransactionByWalletId() throws Exception {
    	long transactionId = 1101;
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.viewTransactionById(transactionId)).willReturn(transaction);
        given(transactionService.updateTransaction(any(Transaction.class),transactionId)).willAnswer((invocation) -> invocation.getArgument(0));

        this.mockMvc.perform(put("/transaction/updatetransactionbywid/{wId}/{trId}",transaction.getWallet().getWalletId(),transaction.getTransactionId())
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));

    }

    @Test
    void shouldReturn404WhenUpdatingNonExistingTransactionByWalletId() throws Exception {
        long transactionId = 1101;
        given(transactionService.viewTransactionById(transactionId)).willReturn(null);
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);

        this.mockMvc.perform(put("/transaction/updatetransactionbywid/{wId}/{trId}", transactionId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(status().isNotFound());

    }

    
    @Test
    void shouldFindTransactionByWalletId() throws Exception {
        long walletId=1301;
        transactionList=new ArrayList<Transaction>();
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.findByWallet(wallet)).willReturn(transactionList);

        this.mockMvc.perform(get("/transaction/gettransactionbywid/{wId}",walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingTransactionByWalletId() throws Exception {
        long walletId=1301;
        given(transactionService.findByWallet(wallet)).willReturn(null);

        this.mockMvc.perform(get("/transaction/gettransactionbybid/{bId}",walletId))
                .andExpect(status().isNotFound());

    }

    
    @Test
    void shouldFindTransactionByBookingId() throws Exception {
        long bookingId=1301;
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.findByBooking(booking)).willReturn(transaction);

        this.mockMvc.perform(get("/transaction//gettransactionbybid/{bId}",bookingId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingTransactionByBookingId() throws Exception {
        long bookingId=1301;
        given(transactionService.findByBooking(booking)).willReturn(null);

        this.mockMvc.perform(get("/transaction/gettransactionbywid/{wId}",bookingId))
                .andExpect(status().isNotFound());

    }
    
    @Test
    void shouldFindTransactionByMode() throws Exception {
        String mode="Online";
        long walletId=1301;
        transactionList=new ArrayList<Transaction>();
        Transaction transaction = new Transaction("Online","Paid",265.89,wallet,booking);
        given(transactionService.findByTransactionMode(mode)).willReturn(transactionList);

        this.mockMvc.perform(get("/transaction/gettransactionbywid/{wId}",walletId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionMode", is(transaction.getTransactionMode())))
                .andExpect(jsonPath("$.transactionStatus", is(transaction.getTransactionStatus())))
                .andExpect(jsonPath("$.transactionAmmount", is(transaction.getTransactionAmount())));
    }
    
    @Test
    void shouldReturn404WhenFetchingNonExistingTransactionByMode() throws Exception {
        long walletId=1301;
        String mode="Online";
        given(transactionService.findByTransactionMode(mode)).willReturn(null);

        this.mockMvc.perform(get("/transaction/gettransactionbybid/{bId}",walletId))
                .andExpect(status().isNotFound());

    }
}
