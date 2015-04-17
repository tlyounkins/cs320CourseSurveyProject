package edu.ycp.cs320.coursesurvey.model;

public class User {

	private String userName, userPassword;
	private int userID;
	private boolean student;
	private boolean proffesor;
	private boolean admin;
	
	public User (String userName, String password, int ID, boolean student, boolean prof, boolean admin){
		this.setUserName(userName);
		this.setPassword(password);
		this.setUserID(ID);
		this.setStudent(student);
		this.setProf(prof);
		this.setAdmin(admin);
	}
	
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

	public void setUserName (String userName) {
		this.userName = userName;
	}
	
	public int getUserID () {
		return this.userID;
	}

	public void setUserID (int userID) {
		this.userID = userID;
	}

	public String getPassword () {
		return this.userPassword;
	}

	public void setPassword (String userPassword) {
		this.userPassword = userPassword;
	}

	
}