package com.cg.mts.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Transaction;

public class TransactionTest {
	public static Transaction tr;
	static Wallet wallet=new Wallet();
	static Booking booking=new Booking();
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in Theatre Test");
		tr=new Transaction("Online","Paid",265.89,wallet,booking);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testAdmin() {
		tr=new Transaction("Online","Paid",265.89,wallet,booking);
		assertNotNull(tr);
		Transaction trOne=null;
		assertNull(trOne);
	}
	@Test
	public void testGetters() {
		assertEquals("Online",tr.getTransactionMode());
		assertEquals("Confirmed",tr.getTransactionStatus());
		assertEquals(265.89, tr.getTransactionAmount());
		assertEquals(wallet, tr.getWallet());
		assertEquals(booking, tr.getBooking());
		
	}

	
	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class TransactionTest");
		tr = null;//now after assigning tr object to null it is eligible for removing from the memory
	}
	
}
