package edu.ycp.cs320.coursesurvey.controller;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;

public class TestAdminController {

	AdminController testController;
	AdminController testController2;
	User adminUser;
	User userExists;
	String inst;
	
	@Before
	public void setup () {
		DatabaseProvider.setInstance(new FakeDatabase());
	
		inst = "YCP";
		DatabaseProvider.getInstance().addInstitution(inst);
		DatabaseProvider.getInstance().addUser("myName", "password1", 1, false, false, true);
		DatabaseProvider.getInstance().addUser("user1", "myPass", 1, true, false, false);
		
		adminUser =	DatabaseProvider.getInstance().findUserAccountByName("myName",1);
		userExists = DatabaseProvider.getInstance().findUserAccountByName("user1", 1);
		testController = new AdminController();
		testController2 = new AdminController();
		
		
	}

	@Test
	public void testUserExists () {
		assertEquals(false, testController.userExists(adminUser, "DNE"));
		assertEquals(true, testController.userExists(adminUser, userExists.getUserName()));
	}

	@Test
	public void testAddUser () {
		testController.addUser(adminUser, "newUser", "newUserPass", "student");
		assertEquals(true, testController.userExists(adminUser, "newUser"));
		
		testController.addUser(adminUser, "secondUser", "theirPass", "prof");
		assertEquals(true, testController.userExists(adminUser, "secondUser"));
		assertEquals(false, testController.userExists(adminUser, "DNE"));
		
		
	}
	@Test
	public void testAddCourse () {
		testController.addCourse(adminUser, "CS320", "Computer Science", "2015", "Fall");
		
		assertEquals("CS320", DatabaseProvider.getInstance().findCourseByName("CS320", 1).getCourseTitle());
		assertEquals(null, DatabaseProvider.getInstance().findCourseByName("CS321", 1));
	}
	
}
