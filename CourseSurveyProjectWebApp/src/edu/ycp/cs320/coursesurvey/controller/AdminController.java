package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class AdminController {
	
	public void addSection (String instName, String Course, String Section) { // add more arguments?
		Institution inst = DatabaseProvider.getInstance().findInstitution(instName);
		int instId = inst.getInstID();
		if (DatabaseProvider.getInstance().findCourse(Course) == null) {
			// TODO: Create course and section
		} else if (DatabaseProvider.getInstance().findSection(Section) == null) {
			// TODO: Just add the section
		} else {
			System.out.println("Course and Section already exist!");
		}
		
	}
	public void addUser(String instName, String userName, String password, String permissions) {
		Institution inst = DatabaseProvider.getInstance().findInstitution(instName);
		int instId = inst.getInstID();
		boolean student = false, prof = false, admin = false;
		if (permissions == "student") {
			student = true;
		} else if (permissions == "prof") {
			prof = true;
		}
		DatabaseProvider.getInstance().addUser(userName, password, instId, student, prof, admin);
	}
	public boolean userExists(String instName, String userName) {
		Institution inst = DatabaseProvider.getInstance().findInstitution(instName);
		int instId = inst.getInstID();
		// If the userName does not exist in the database, then return true
		// it can be added
		if (DatabaseProvider.getInstance().findUserAccountByName(userName, instId) == null) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
