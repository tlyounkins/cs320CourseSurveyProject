package edu.ycp.cs320.coursesurvey.model;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.coursesurvey.model.Institution;

/*
public class InstitutionTest {

	Institution testOne;
	Institution testTwo;
	
	@Before 
	public void setUp() {
		testOne = new Institution ("York College of Pennsylvania");
		testTwo = new Institution ("Towson University");
	}
	
	@Test
	public void testGetName() {
		assertEquals("York College of Pennsylvania", testOne.getName());
		assertNotEquals("Towson University", testOne.getName());
		
		assertEquals("Towson University", testTwo.getName());
		assertNotEquals("York College of Pennsylvania", testTwo.getName());
	}
	
	@Test
	public void testSetName () {
		testOne.setName("YCP");
		
		assertEquals("YCP", testOne.getName());
		assertNotEquals("York College of Pennsylvania", testOne.getName());
		
		testTwo.setName("Towson");
		
		assertEquals("Towson", testTwo.getName());
		assertNotEquals("towson", testTwo.getName());
		
	}
	
	@Test
	public void testCreateAdminAccount () {
		testOne.createAdminAccount("masterAccount", "somethingComplex");
		assertEquals(0, testOne.findAdminIndex("masterAccount"));
	
		testTwo.createAdminAccount("MasterAccount", "somethingComplex2");
		testTwo.createAdminAccount("secondary", "abcComplex");
		//test findAdminIndex
		assertEquals(0, testTwo.findAdminIndex("MasterAccount"));
		assertEquals(1, testTwo.findAdminIndex("secondary"));
		assertEquals(-1, testTwo.findAdminIndex("DoesNotExist"));
	}
	public void testCreateGeneralUser () {
		testOne.createGeneralUser("Mario", "mushrooms");
		testOne.createGeneralUser("Zelda", "LinK");
		testOne.createGeneralUser("Samus", "Suit");
		testOne.createGeneralUser("Battle", "Toads");
		
		assertEquals(0, testOne.findGeneralIndex("Mario"));
		assertEquals(1, testOne.findGeneralIndex("Zelda"));
		assertEquals(2, testOne.findGeneralIndex("Samus"));
		assertEquals(3, testOne.findGeneralIndex("Battle"));
		assertEquals(-1, testOne.findGeneralIndex("Luigi"));
	}
}
*/