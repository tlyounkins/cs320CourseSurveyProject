package edu.ycp.cs320.coursesurvey.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Section {
	//TODO : Update model using id and section name
	int sectID;
	int userID;
	boolean student;
	boolean proffesor;
	//ArrayList<User> studentList;
	//ArrayList<User> teacherList;
	
	public Section (int sectID, int userID, boolean student, boolean proffesor) {
		this.setSectID(sectID);
		this.setUserID(userID);
		this.setStudent(student);
		this.setProf(proffesor);
	}

	public int getSectID () {
		return this.sectID;
	}

	public void setSectID (int sectID) {
		this.sectID = sectID;
	}
	
	public int getUSerID () {
		return this.userID;
	}

	public void setUserID (int userID) {
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
	
	
	/*public ArrayList<User> getStudentList () {
		return studentList;
	}
	
	public ArrayList<User> getTeacherList () {
		return teacherList;
	}

	public void displayStudentList () {
		Iterator<User> iter = studentList.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	public void displayTeacherList() {
		Iterator<User> iter = teacherList.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}*/

}
