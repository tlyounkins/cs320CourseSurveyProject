package edu.ycp.cs320.coursesurvey.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;

public class TestAccountCreationController {

	AccountCreationController testCreationController;
	AccountCreationController testCreationController2;
	
	String testPassword;
	String testPasswordCheck;
	String testPassFail;
	String testInstName;
	String testInstNameExists;
	String testAccountName;
	
	
	
	@Before
	public void setup() {
		testCreationController = new AccountCreationController();
		testCreationController2 = new AccountCreationController();
		testPassword = "myPass";
		testPasswordCheck = "myPass";
		testPassFail = "notMyPass";
		testInstName = "YCP";		
		testInstNameExists = "HACC";
		testAccountName = "myName";
		
		DatabaseProvider.setInstance(new FakeDatabase());
		
	}

	@Test
	public void testCheckPassword () {
		assertEquals(false, testCreationController.passwordsMatching());
		testCreationController.checkPassword(testPassword, testPasswordCheck);
		
		assertEquals(true, testCreationController.passwordsMatching());
		
		testCreationController.checkPassword(testPassword, testPassFail);
		
		assertNotEquals(true, testCreationController.passwordsMatching());
		
	}
	
	@Test
	public void testCreateAccount () {
	DatabaseProvider.getInstance().addInstitution(testInstNameExists);
		
	testCreationController.createAccount(testInstName, testAccountName, testPassword, testPasswordCheck);
	testCreationController2.createAccount(testInstName, testInstNameExists, testPassword, testPasswordCheck);
	
	assertEquals(true, testCreationController.passwordsMatching());
	assertEquals(false, testCreationController.instAlreadyExists());
	assertEquals(true, testCreationController.done());
	assertEquals("YCP", DatabaseProvider.getInstance().findInstitution(testInstName).getName());
	
	assertEquals(true, testCreationController2.passwordsMatching());
	assertEquals(true, testCreationController2.instAlreadyExists());
	assertEquals (false, testCreationController2.done());
	assertEquals("HACC", DatabaseProvider.getInstance().findInstitution(testInstNameExists).getName());
	assertEquals(null, DatabaseProvider.getInstance().findInstitution("YCPHACC"));
	
	}


}

