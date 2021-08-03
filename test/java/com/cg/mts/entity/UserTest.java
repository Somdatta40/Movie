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
import com.cg.mts.entity.Seat;
import com.cg.mts.entity.User;

public class UserTest {
	public static User ur;
	@BeforeAll
	public static void setUp() {
		System.out.println("This method id annoted with @before all to execute as first in user test");
		ur=new User("abcAB123","admin");
	}
	@BeforeEach
	public void setUpForTest() {
		System.out.println("This method id annoted with ");
	
	}
	@Test
	public void testUser() {
		User ur=new User("abcAB123","admin");
		assertNotNull(ur);
		Seat urOne=null;
		assertNull(urOne);
	}
	@Test
	public void testGetters() {
		assertEquals("abcAB123",ur.getPassword());
		assertNotEquals("ab@12", ur.getPassword());
		assertEquals("admin",ur.getRole());
		assertEquals("customer",ur.getRole());
		
		
	}

	@AfterEach
	public void stopThis() {
		System.out.println("this method is annoted with @AfterEach"
				+ "to execute after each test case");
	}
	
	@AfterAll
	public static void stopAll() {
		System.out.println("this method is annoted with @AfterAll"
				+ " to execute as last method in the test class UserTest");
		ur = null;//now after assigning ur object to null it is eligible for removing from the memory
	}
	
}
