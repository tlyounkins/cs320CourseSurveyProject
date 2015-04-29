package edu.ycp.cs320.coursesurvey.model;

//import java.util.ArrayList;
//import java.util.Iterator;


/**
 * 
 * @author Tyler Younkins, Garlan Bowser, Cory Dawson
 *
 */

public class Institution {
	private int instID; //only need one ID for all sub tables that there is only one occurence of.
	private String name;
	private int numUsers = 0;
	private int numCourses = 0;
	private int numSurveys = 0;
	


	/*public Institution (int instID, int sectID, String name, int userTableID, int courseTableID) {
		this.instId = instID;
		this.name = name;
		this.userTableID = userTableID;
		this.courseTableID = courseTableID;
		
		//studentList = new ArrayList<User>();
		//teacherList = new ArrayList<User>();
		
	}*/


	/**
	 * Get Institution name
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set Institution name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get Institution ID
	 * @return instId
	 */
	public int getInstID () {
		return this.instID;
	}

	/**
	 * Set Institution ID#
	 * @param instId
	 */
	public void setInstID (int instId) {
		this.instID = instId;
	}
	
	
	public int getNumUsers(){
		return this.numUsers;
	}
	
	public int getNumCourses(){
		return this.numCourses;
	}
	
	public int getNumSurveys(){
		return this.numSurveys;
	}
	
	public void setNumUsers(int num){
		this.numUsers = num;
	}
	
	public void setNumCourses(int num){
		this.numCourses = num;
	}
	
	public void setNumSurveys(int num){
		this.numSurveys = num;
	}
}
