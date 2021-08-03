package com.cg.mts.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Ticket;

public class TicketTest {
	public static Ticket ti;
	static Theatre theatre=new Theatre();
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in Ticket Test");
		ti=new Ticket(true,"SN21",theatre,2,500);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	}
	
	@Test
	public void testAdmin() {
		ti=new Ticket(true,"SN21",theatre,2,500);
		assertNotNull(ti);
		Ticket tiOne=null;
		assertNull(tiOne);
	}
	@Test
	public void testGetters() {
		assertEquals(true,ti.isTicketStatus());
		assertEquals("SN21",ti.getScreenName());
		assertEquals(theatre,ti.getTheatre());
		assertEquals(2,ti.getNoOfSeats());
		assertEquals(500,ti.getTotalCost());
		
		
	}

	
	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class TicketTest");
		ti = null;//now after assigning ti object to null it is eligible for removing from the memory
	}
	
}
