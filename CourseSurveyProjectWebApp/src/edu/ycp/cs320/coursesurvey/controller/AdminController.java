package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class AdminController {
	
	public void addSection (String instName, String course, String cection) { // add more arguments?
	/*	Institution inst = DatabaseProvider.getInstance().findInstitution(instName);
		int instId = inst.getInstID();
		if (DatabaseProvider.getInstance().findCourseByName(course, instId) == null) {
			// TODO: Create course and section
		} else if (DatabaseProvider.getInstance().findSection(Section) == null) {
			// TODO: Just add the section
		} else {
			System.out.println("Course and Section already exist!");
		}
	
	*/
	}
	
	public void addUser(User adminUser, String userName, String password, String permissions) {
		int instId = adminUser.instID();
		boolean student = false, prof = false, admin = false;
		if (permissions == "student") {
			student = true;
		} else if (permissions == "prof") {
			prof = true;
		}
		DatabaseProvider.getInstance().addUser(userName, password, instId, student, prof, admin);
	}
	public void addCourse (User sessionUser, String title) {
		int instID = sessionUser.instID();
		// TODO - Get dept, year and term from AdminHomePage
		String dept = "CS";
		int year = 2015;
		String term = "Spring";
		DatabaseProvider.getInstance().addCourse(instID, title, dept, year, term);
		
	}

	public boolean userExists(String instName, String userName) {
		Institution inst = DatabaseProvider.getInstance().findInstitution(instName);
		System.out.println("inst Name is " +instName);
		int instId = inst.getInstID();
		System.out.println("inst ID is " + instId);
		// If the userName does not exist in the database, then return true
		// it can be added
		if (DatabaseProvider.getInstance().findUserAccountByName(userName, instId) != null) {
			return true;
		} else {
			return false;
		}
		
	}
	
}