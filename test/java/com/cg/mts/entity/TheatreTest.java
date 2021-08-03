package com.cg.mts.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Theatre;

public class TheatreTest {
	public static Theatre th;
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in Theatre Test");
		th=new Theatre("Inox","78980446198");
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testAdmin() {
		Theatre th=new Theatre("Inox","78980446198");
		assertNotNull(th);
		Theatre thOne=null;
		assertNull(thOne);
	}
	@Test
	public void testGetters() {
		assertEquals("Inox",th.getTheatreName());
		assertEquals("07980446198",th.getTheatreContact());
		assertNotEquals("9903234150", th.getTheatreContact());
		assertNotEquals("990323415", th.getTheatreContact());
		
	}

	
	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class TheatreTest");
		th = null;//now after assigning th object to null it is eligible for removing from the memory
	}
	
}
