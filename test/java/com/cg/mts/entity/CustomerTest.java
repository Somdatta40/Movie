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

import com.cg.mts.entity.Customer;

public class CustomerTest {
	public static Customer cust;
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in contact test");
		cust=new Customer("RamaCust","0903234157","Kolkata");
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testAdmin() {
		Customer cust=new Customer("RamaCust","0903234157","Kolkata");
		assertNotNull(cust);
		Customer custOne=null;
		assertNull(custOne);
	}
	@Test
	public void testGetters() {
		assertEquals("RamaCust",cust.getCustomerName());
		assertEquals("0903234157",cust.getMobileNumber());
		//assertNotEquals("9903234150", cust.getMobileNumber());
		assertEquals("Kolkata",cust.getCity());
		assertNotEquals("9903234150", cust.getMobileNumber());
		assertNotEquals("990323415", cust.getMobileNumber());
		
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
				+ " to execute as last method in the test class CustomerTest");
		cust = null;//now after assigning cust object to null it is eligible for removing from the memory
	}
	
}
