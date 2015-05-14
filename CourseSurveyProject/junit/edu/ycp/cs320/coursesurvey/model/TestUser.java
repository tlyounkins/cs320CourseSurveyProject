package edu.ycp.cs320.coursesurvey.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestUser {

	User testUser;
	User testUser2;
	
	@Before
	public void setup() {
		testUser = new User();
		testUser2 = new User ();
		
		testUser.setUserName("Link");
		testUser.setPassword("to the Past");
		testUser.setUserID(1);
		testUser.setInstID(2);
		testUser.setAdmin(true);
		testUser.setProf(false);
		testUser.setStudent(false);
		
		testUser2.setUserName("Tor");
		testUser2.setPassword("Thunder God");
		testUser2.setUserID(2);
		testUser2.setInstID(4);
		testUser2.setAdmin(false);
		testUser2.setProf(true);
		testUser2.setStudent(false);
	
	}
	
	@Test
	public void testGetUserName () {
		assertEquals("Link", testUser.getUserName());
		assertEquals("Tor", testUser2.getUserName());
	}
	
	@Test 
	public void testGetPassword () {
		assertEquals("to the Past", testUser.getPassword());
		assertEquals("Thunder God", testUser2.getPassword());
	}


	@Test
	public void testGetUserID () {
		assertEquals(1, testUser.getUserID());
		assertEquals(2, testUser2.getUserID());
	}
	
	@Test
	public void testGetInstID () {
		assertEquals(2, testUser.instID());
		assertEquals(4, testUser2.instID());
	}

	@Test
	public void testIsAdmin () {
		assertEquals(true, testUser.isAdmin());
		assertEquals(false, testUser2.isAdmin());
	}

	@Test
	public void testIsProf () {
		assertEquals(false, testUser.isProf());
		assertEquals(true, testUser2.isProf());
	}

	@Test
	public void testIsStudent () {
		assertEquals (false, testUser.isStudent());
		assertEquals (false, testUser2.isStudent());
	}
	








}
