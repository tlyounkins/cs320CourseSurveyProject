package edu.ycp.cs320.coursesurvey.model;

public class User {

	private String userName, userPassword;
	private int userID;
	private boolean student;
	private boolean proffesor;
	private boolean admin;
	
	public boolean isStudent(){
		return this.student;
	}
	
	public void setStudent(boolean state){
		this.student = state;
	}
	
	public boolean isProf(){
		return this.proffesor;
	}
	
	public void setProf(boolean state){
		this.proffesor = state;
	}
	
	public boolean isAdmin(){
		return this.admin;
	}
	
	public void setAdmin(boolean state){
		this.admin = state;
	}
	

	public String getUserName () {
		return this.userName;
	}

	public void setGenName (String userName) {
		this.userName = userName;
	}

	public String getGenPassword () {
		return this.userPassword;
	}

	public void setGenPassword (String userPassword) {
		this.userPassword = userPassword;
	}

	
}