package edu.ycp.cs320.coursesurvey.persistence;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.Section;

public class FakeDatabase  implements IDatabase{

	private List<Institution> institutionTable;
	private List<ArrayList<User>> userTables;
	private List<ArrayList<Course>> courseTables;
	private List<ArrayList<Section>> sectionTables;
	//private List<Section> sectionList;

	public FakeDatabase() {
		this.institutionTable = new ArrayList<Institution>();
		this.userTables = new ArrayList <ArrayList<User>>();
		this.courseTables = new ArrayList <ArrayList<Course>>();
		this.sectionTables = new ArrayList <ArrayList<Section>>();
		

	}
	@Override
	public User findUserAccountByName(String accountName, int instID) {
		//grabs the institution information
		Institution inst = this.institutionTable.get(instID-1);
		
		//grabs the user table for the institution
		ArrayList<User> instUserTable = this.userTables.get(inst.getUserTableID()-1); 
		
		for (User user : instUserTable) {
			if (user.getUserName().equals(accountName)) {
				return user;
			}
		}
		return null;
	}
	
	@Override
	public Institution findInstitution (String instName) {
		for (Institution institution : this.institutionTable) {
			if (institution.getName().equals(instName)) {
				return institution;
			}
		}
		return null;
	}
	
	@Override
	public int addInstitution ( String instName) {
		
		//creates ID values for pertinent tables
		int instID = this.institutionTable.size()+1;
		int courseTableID = this.courseTables.size()+1;
		int userTableID = this.userTables.size()+1;
		
		
		//institutionList.get(index).setName(instName);
		//institutionList.get(index).setInstId(instId);
		
		this.institutionTable.add(new Institution(instName, instID, courseTableID, userTableID));
		this.courseTables.add(new ArrayList<Course>());
		this.userTables.add(new ArrayList<User>());
		
		System.out.println("Institution Name is " + this.institutionTable.get(instID-1).getName());
		System.out.println("institution id is " + this.institutionTable.get(instID-1).getInstId());
		
		//returns the id to be stored if needed (look at account creation controller for an ex.)
		return instID;
	}
	@Override
	public int addUser (String userName, String password, int instId, boolean student, boolean prof, boolean admin) {
		ArrayList<User> userTable = this.userTables.get(this.institutionTable.get(instId-1).getUserTableID()-1);
		int newID = userTable.size() + 1;
		
		System.out.println("user name is " + userTable.get(newID-1).getUserName() );
		System.out.println("user id is " + userTable.get(newID-1).getUserID());
		System.out.println("user password is " + userTable.get(newID-1).getPassword());
		if (student) System.out.println("user is a student");
		if (prof) System.out.println("user is a profesor");
		if (admin) System.out.println("user is an admin");
		
		return newID;
	}
	
	@Override
	public int addCourse(int instID, String title, String dept, int year, String term){
		ArrayList<Course> courseTable = this.courseTables.get(this.institutionTable.get(instID-1).getCourseTableID()-1);
		
		int newCourseID = courseTable.size() + 1;
		int newSectionTableID = this.sectionTables.size() + 1;
		
		this.sectionTables.add(new ArrayList<Section>());
		courseTable.add(new Course(title, dept, year, term, newSectionTableID));
		System.out.println(title + " has been added to the courses");
		
		return newSectionTableID;
	}
	
	@Override
	public Course findCourse(String course) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Section findSection(String section) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addSurvey(String surveyName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createTables() {
		// TODO Auto-generated method stub
		
	}
}
