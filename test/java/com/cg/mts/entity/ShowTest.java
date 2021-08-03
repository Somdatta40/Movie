package com.cg.mts.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.mts.entity.Show;

public class ShowTest {
	public static Show s;
	static Movie movie=new Movie (); 
	static Screen screen=new Screen();
	static LocalTime localtime = LocalTime.parse("01:00");
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in Customer Test");
		s=new Show(localtime,localtime,"ABC",8001,movie,screen);
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testAdmin() {
	     Show s=new Show(localtime,localtime,"ABC",8001,movie,screen);
		assertNotNull(s);
		Show sOne=null;
		assertNull(sOne);
	}
	@Test
	public void testGetters() {
		assertEquals("08:00",s.getShowStartTime());
		assertEquals("01:00",s.getShowEndTime());
		assertEquals("abc",s.getShowName());
		assertEquals("8000",s.getTheatreId());
		assertEquals("abc",s.getMovie());
		assertEquals("abc",s.getScreen());
		
		
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
				+ " to execute as last method in the test classShowTest");
		s= null;//now after assigning cust object to null it is eligible for removing from the memory
	}
	
}
