package com.cg.mts.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.Wallet;

public class WalletTest {
	public static Wallet wt;
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in user test");
		wt=new Wallet(1001,250);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testWallet() {
		Wallet wt=new Wallet(1001,250);
		assertNotNull(wt);
		Seat wtOne=null;
		assertNull(wtOne);
	}
	@Test
	public void testGetters() {
		assertEquals(250,wt.getBalance());
		
	}

	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class WalletTest");
		wt = null;//now after assigning wt object to null it is eligible for removing from the memory
	}
	
}
