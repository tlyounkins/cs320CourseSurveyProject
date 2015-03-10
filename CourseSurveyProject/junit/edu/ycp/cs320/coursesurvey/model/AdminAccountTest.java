package edu.ycp.cs320.coursesurvey.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.coursesurvey.model.AdminAccount;


public class AdminAccountTest {
	private AdminAccount accountOne, accountTwo;


	@Before 
	public void setUp() {
		accountOne = new AdminAccount ("foo", "mAnChu");
		accountTwo= new AdminAccount ("Ravens", "Nation");
	}
	@Test
	public void testGetAccountName() throws Exception {
		assertEquals("foo", accountOne.getName());
		assertNotEquals("fo", accountOne.getName());

		assertEquals("Ravens", accountTwo.getName());
		assertNotEquals("ravens", accountTwo.getName());

	}

	@Test
	public void testSetAccountName() throws Exception {
		accountOne.setName("Zelda");
		assertEquals("Zelda", accountOne.getName());
		assertNotEquals ("foo", accountOne.getName());
		
		accountTwo.setName("Zombie");
		assertEquals("Zombie", accountTwo.getName());
		assertNotEquals("foo", accountTwo.getName());
		
	}
	
	@Test
	public void testGetPassword() throws Exception {
		assertEquals("mAnChu", accountOne.getPassword());
		assertNotEquals("MaNcHu", accountOne.getPassword());

		assertEquals("Nation", accountTwo.getPassword());
		assertNotEquals("nAtIon", accountTwo.getPassword());
	}

	@Test
	public void testSetPassword () throws Exception {
		accountOne.setPassword("bar");
		assertNotEquals("Nation", accountOne.getPassword());
		assertEquals("bar", accountOne.getPassword());
		
		accountTwo.setPassword("Relentless");
		assertEquals("Relentless", accountTwo.getPassword());
		assertNotEquals("irresolute", accountTwo.getPassword());
		
	}

}
