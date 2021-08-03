package com.cg.mts.entity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Screen;
import com.cg.mts.entity.Theatre;

public class ScreenTest {
	public static Screen sc;
	static Theatre theatre=new Theatre();
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in contact test");
		sc=new Screen("Screen1",10,10, theatre);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testContact() {
		Screen sc=new Screen("Screen1",10,10,theatre);
		assertNotNull(sc);
		Screen scOne=null;
		assertNull(scOne);
	}
	@Test
	public void testGetters() {
		assertEquals("Screen1",sc.getScreenName());
		assertEquals("1",sc.getRows());
		assertEquals("1",sc.getColumns());
		assertEquals(theatre,sc.getTheatre());
		
	}

	/*
	 * @Test public void testValidateFirstName() { //ct = new
	 * Contact("sharmistha","roy","0903234157"); assertSame("First Name is valid",
	 * ct.validateFirstName(ct.getFirstName())); assertNotSame(null,
	 * ct.validateFirstName(ct.getFirstName())); }
	 * 
	 * @Test public void testValidateLastName() { //ct = new
	 * Contact("sharmistha","roy","0903234157"); assertSame("Last Name is valid",
	 * ct.validateLastName(ct.getLastName())); assertNotSame(null,
	 * ct.validateLastName(ct.getLastName())); }
	 * 
	 * @Test public void testValidateMobileNo() { //ct = new
	 * Contact("sharmistha","roy","0903234157");
	 * assertNotSame("mobileNo cannot be less than 10 digits",
	 * ct.validateMobileNo(ct.getMobileNo())); assertNotSame(null,
	 * ct.validateMobileNo(ct.getMobileNo()));
	 * assertNotSame("mobileNo can contain only digits",
	 * ct.validateMobileNo(ct.getMobileNo()));
	 * assertNotSame("mobileNo should start with 0",
	 * ct.validateMobileNo(ct.getMobileNo())); assertSame("Mobile number is valid",
	 * ct.validateMobileNo(ct.getMobileNo()));
	 * assertSame("mobileNo can contain only digits",
	 * ct.validateMobileNo("s123456780"));
	 * assertSame("mobileNo should start with 0",
	 * ct.validateMobileNo("9075468967")); }
	 * 
	 * @Test public void testDisplayDetails() { assertEquals(new
	 * String("The details of the contact is = sharmistha roy 0903234157"),ct.
	 * displayDetails()); assertNotEquals(new
	 * String("The details of the contact is = diya sharmistha 0903234157"),
	 * ct.displayDetails()); }
	 */
	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class ScreenTest");
		sc = null;//now after assigning sc object to null it is eligible for removing from the memory
	}
	
}
