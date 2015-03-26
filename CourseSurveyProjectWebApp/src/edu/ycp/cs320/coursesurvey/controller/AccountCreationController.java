package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Institution;

public class AccountCreationController {
	private int instId;
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
	
	public void createAccount(String accountName, String password, String passwordCheck){
		this.checkPassword(password, passwordCheck);
		if (passwordMatch) {
			
			
			//this.institute.createAdminAccount(accountName, password);
			finished = true;
		}
	}
	
	public boolean done(){
		return this.finished;
	}
}
