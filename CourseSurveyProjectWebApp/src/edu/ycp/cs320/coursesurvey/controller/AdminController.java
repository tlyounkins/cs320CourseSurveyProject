package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class AdminController {
	
	public void addSection (String inst, String Course, String Section) { // add more arguments?
		if (DatabaseProvider.getInstance().findCourse(Course) != null 
				&& DatabaseProvider.getInstance().findSection(Section) == null) {
			//int section = DatabaseProvider.getInstance().addToSectionTable(instID, courseID, sectID, userID, student, prof);
		} else { // add the course too
			//DatabaseProvider.getInstance().addCourse(instID, title, dept, year, term)
			//int section = DatabaseProvider.getInstance().addToSectionTable(instID, courseID, sectID, userID, student, prof);
		}
		
	}
	public void addUser(int instId, String userName, String password, String permissions) {
		boolean student = false, prof = false, admin = false;
		if (permissions == "student") {
			student = true;
		} else if (permissions == "prof") {
			prof = true;
		}
		DatabaseProvider.getInstance().addUser(userName, password, instId, student, prof, admin);
	}
}
