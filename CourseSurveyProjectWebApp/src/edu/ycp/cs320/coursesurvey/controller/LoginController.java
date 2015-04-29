package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.controller.AccountCreationController;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;
import edu.ycp.cs320.coursesurvey.persistence.IDatabase;
import edu.ycp.cs320.coursesurvey.model.User;

public class LoginController {

	public boolean login(String inst,String accountName, String password){
		Institution instName = DatabaseProvider.getInstance().findInstitution(inst);
		if (instName == null) {
			//System.out.println("Institution doesn't exist");
			return false;
		}
		User user = DatabaseProvider.getInstance().findUserAccountByName(accountName, 1); 
		if (user != null){
			if (user.getPassword().equals(password)) {
				System.out.println(user);
				System.out.println(password);
				return true;
			} 
		}
		return false;
	}
	public boolean isAdmin(String accountName, String password) {
		User user = DatabaseProvider.getInstance().findUserAccountByName(accountName, 1);
		if (user != null) {
			if (user.isAdmin()) {
				System.out.println("User is an admin");
				return true;
			}

		}
		System.out.println("User is not an admin");
		return false;
	}
	public boolean isProf(String accountName, String password) {
		User user = DatabaseProvider.getInstance().findUserAccountByName(accountName, 1);
		if (user.isProf()) {
			return true;
		}
		return false;
	}
	public boolean isStudent(String accountName, String password) {
		User user = DatabaseProvider.getInstance().findUserAccountByName(accountName, 1);
		if (user.isStudent()) {
			return true;
		}
		return false;
	}

	public boolean isAdminTest (User sessionUser) {
		if (sessionUser.isAdmin()){
			return true;
		}
		return false;
	}

	public boolean isStudentTest (User sessionUser) {
		if (sessionUser.isStudent()) {
			return true;
		}
		return false;

	}

	public boolean isProfTest (User sessionUser) {
		if (sessionUser.isProf()) {
			return true;
		}
		return false;

	}
	
	public User createUserSession (String accountName, String instName) {
		int instID = DatabaseProvider.getInstance().findInstitution(instName).getInstID();
		User sessionUser = DatabaseProvider.getInstance().findUserAccountByName(accountName, instID);
		return sessionUser;
	}

}