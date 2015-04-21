package edu.ycp.cs320.coursesurvey.model;

public class User {

	private String userName, userPassword;
	private int userID;
	private int instID;
	private boolean student;
	private boolean proffesor;
	private boolean admin;
	

	public User (String userName, String userPassword, int userID, /*int instID,*/ boolean isStudent, boolean isProf, boolean isAdmin) {
		this.userName = userName;
		this.userPassword = userPassword;
		//this.instID = instID;
		this.userID = userID;
		this.student = isStudent;
		this.proffesor = isProf;
		this.admin = isAdmin;
		//studentList = new ArrayList<User>();
		//teacherList = new ArrayList<User>();
		
	}
	
	public int instID(){
		return this.instID;
	}
	
	public void setInstID(int instID){
		this.instID = instID;
	}
	
	public int userID(){
		return this.userID;
	}
	
	public void userID(int userID){
		this.userID = userID;
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
