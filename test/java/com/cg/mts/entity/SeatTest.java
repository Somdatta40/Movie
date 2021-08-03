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

public class SeatTest {
	public static Seat st;
	static Ticket ticket=new Ticket(); 
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in seat test");
		st=new Seat("A12",true,ticket);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testSeat() {
		Seat st=new Seat("A12",true,ticket);
		assertNotNull(st);
		Seat stOne=null;
		assertNull(stOne);
	}
	@Test
	public void testGetters() {
		assertEquals("A12",st.getSeatNumber());
		assertEquals(true,st.isSeatStatus());
		assertEquals(ticket,st.getTicket());
	}

	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class SeatTest");
		st = null;//now after assigning st object to null it is eligible for removing from the memory
	}
	
}
