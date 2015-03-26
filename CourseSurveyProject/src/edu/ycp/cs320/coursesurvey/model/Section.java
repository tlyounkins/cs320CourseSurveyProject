package edu.ycp.cs320.coursesurvey.model;

import java.util.ArrayList;
import java.util.Iterator;


public class Section {
	//TODO : Update model using id and section name
	int sect;
	ArrayList<GeneralUser> studentList;
	ArrayList<GeneralUser> teacherList;
	
	public Section (int sect) {
		this.sect = sect;
		studentList = new ArrayList<GeneralUser>();
		teacherList = new ArrayList<GeneralUser>();
		
	}

	public int getSect () {
		return sect;
	}

	public void setSect (int sect) {
		this.sect = sect;
	}
	
	public ArrayList<GeneralUser> getStudentList () {
		return studentList;
	}
	
	public ArrayList<GeneralUser> getTeacherList () {
		return teacherList;
	}

	public void displayStudentList () {
		Iterator<GeneralUser> iter = studentList.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	
	public void displayTeacherList() {
		Iterator<GeneralUser> iter = teacherList.iterator();
		
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}

}
