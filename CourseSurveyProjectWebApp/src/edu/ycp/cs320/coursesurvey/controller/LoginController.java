package edu.ycp.cs320.coursesurvey.controller;



public class LoginController {
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

}
