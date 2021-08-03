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

import com.cg.mts.entity.Movie;
import com.cg.mts.entity.Theatre;
import com.cg.mts.entity.Ticket;

public class MovieTest {
	public static Movie mv;
	
	static Theatre theatre=new Theatre();
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute  in movie test to show every detail of movie");
		mv=new Movie("Insidious","horror","2:30","English",theatre);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testAdmin() {
		 Movie mv=new Movie("Insidious","horror","2:30","English",theatre);
		assertNotNull(mv);
		Movie mvOne=null;
		assertNull(mvOne);
	}
	@Test
	public void testGetters() {
		assertEquals("Insidious",mv.getMovieName());
		assertEquals("horror",mv.getMovieGenre());
		assertEquals("02:00",mv.getMovieHours());
		assertEquals("English",mv.getLanguage());
		assertEquals(theatre,mv.getTheatre());
		
		
	}
	/**@Test
	public void testValidateFirstName() {
		//ct = new Contact("sharmistha","roy","0903234157");
		assertSame("First Name is valid", ct.validateFirstName(ct.getFirstName()));
		assertNotSame(null, ct.validateFirstName(ct.getFirstName()));
	}

	@Test
	public void testValidateLastName() {
		//ct = new Contact("sharmistha","roy","0903234157");
		assertSame("Last Name is valid", ct.validateLastName(ct.getLastName()));
		assertNotSame(null, ct.validateLastName(ct.getLastName()));
	}
	
	@Test
	public void testValidateMobileNo() {
		//ct = new Contact("sharmistha","roy","0903234157");
		assertNotSame("mobileNo cannot be less than 10 digits", ct.validateMobileNo(ct.getMobileNo()));
		assertNotSame(null, ct.validateMobileNo(ct.getMobileNo()));
		assertNotSame("mobileNo can contain only digits", ct.validateMobileNo(ct.getMobileNo()));
		assertNotSame("mobileNo should start with 0", ct.validateMobileNo(ct.getMobileNo()));
		assertSame("Mobile number is valid",  ct.validateMobileNo(ct.getMobileNo()));
		assertSame("mobileNo can contain only digits",  ct.validateMobileNo("s123456780"));
		assertSame("mobileNo should start with 0",  ct.validateMobileNo("9075468967"));
	}
	**/
	
	
	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class MovieTest");
		mv = null;//now after assigning mv object to null it is eligible for removing from the memory
	}
	
}