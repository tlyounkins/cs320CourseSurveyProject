package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class AdminController {

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
	public void addCourse (User sessionUser, String title, String dept, String newyear, String term) {
		int instID = sessionUser.instID();
		int year = Integer.parseInt(newyear);
		DatabaseProvider.getInstance().addCourse(instID, title, dept, year, term);
		
	}

	public boolean userExists(User adminUser, String userName) {
		int instId = adminUser.instID();
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
