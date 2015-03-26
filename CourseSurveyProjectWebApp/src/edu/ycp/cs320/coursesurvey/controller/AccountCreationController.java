package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;

public class AccountCreationController {
	private boolean passwordMatch = false;
	private boolean finished = false;
	
	
	private void checkPassword(String password, String passwordCheck){
		if (password.contains(passwordCheck)){
			passwordMatch = true;
			System.out.println("match");
		}
		else {
			passwordMatch = false;
			System.out.println(password + " mismatch " + passwordCheck);
		}
	}

	public boolean passwordsMatching(){
		return this.passwordMatch;
	}

	public void createAccount(String instName, String accountName, String password, String passwordCheck){
		this.checkPassword(password, passwordCheck);
		if (passwordMatch) {
			
			// TODO: Check if institution already exists
			
			//create a new institution
			DatabaseProvider.getInstance().addInstitution(instName);	
			DatabaseProvider.getInstance().addAdmin(accountName, password);

			//this.institute.createAdminAccount(accountName, password);
			finished = true;
		}
	}

	public boolean done(){
		return this.finished;
	}
}
