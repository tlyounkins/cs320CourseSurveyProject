package edu.ycp.cs320.coursesurvey.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Section {
	//TODO : Update model using id and section name
	int sect;
	ArrayList<User> studentList;
	ArrayList<User> teacherList;
	
	public Section (int sect) {
		this.sect = sect;
		studentList = new ArrayList<User>();
		teacherList = new ArrayList<User>();
		
	}

	public int getSect () {
		return sect;
	}

	public void setSect (int sect) {
		this.sect = sect;
	}
	
	public ArrayList<User> getStudentList () {
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
	}

}
