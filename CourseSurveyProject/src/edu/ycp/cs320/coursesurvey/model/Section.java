package edu.ycp.cs320.coursesurvey.model;

//import java.util.ArrayList;
//import java.util.Iterator;


public class Section {
	//TODO : Update model using id and section name
	private int sectTableID;
	private int sectID;
	private int userID;
	private boolean proffesor;
	private boolean student;
	//ArrayList<User> studentList;
	//ArrayList<User> teacherList;
	
	public Section (int sectTableID, int sectID, int userID, boolean isStudent, boolean isProf) {
		this.sectTableID = sectTableID;
		this.sectID = sectID;
		this.userID = userID;
		this.student = isStudent;
		this.proffesor = isProf;
		//studentList = new ArrayList<User>();
		//teacherList = new ArrayList<User>();
		
	}

	public int getSectID () {
		return this.sectID;
	}

	public void setSectID (int sectID) {
		this.sectID = sectID;
	}
	
	public int getUserID () {
		return this.userID;
	}

	public void setUserID (int userID) {
		this.userID = userID;
	}
	
	public int getTableSectID () {
		return this.sectTableID;
	}

	public void setTableSectID (int sectTableID) {
		this.sectTableID = sectTableID;
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
	//public ArrayList<User> getStudentList () {
	//	return studentList;
	//}
	
	//public ArrayList<User> getTeacherList () {
	//	return teacherList;
	//}

	/*public void displayStudentList () {
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
