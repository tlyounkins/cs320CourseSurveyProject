package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class AccountCreationController {
	private boolean passwordMatch = false;
	private boolean finished = false;
	private boolean instExists = false;

	
	public void checkPassword(String password, String passwordCheck){
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
	
	public boolean instAlreadyExists(){
		return this.instExists;
	}

	public void createAccount(String instName, String accountName, String password, String passwordCheck){
		this.checkPassword(password, passwordCheck);
		if (passwordMatch) {

			int id;
			//create a new institution if it does not exist
			if ((DatabaseProvider.getInstance().findInstitution(instName)) == null){
				instExists = false;
				id = DatabaseProvider.getInstance().addInstitution(instName);	
				DatabaseProvider.getInstance().addUser(accountName, password, id, false, false, true);
				System.out.println("created User and Institution");
				finished = true;
			}
			else {
				//variable will be set to true if the institution already exists and the account will not be created
				System.out.println("institution already exists in database");
				instExists = true;
			}
			
		}
	}

	public boolean done(){
		return this.finished;
	}
}
