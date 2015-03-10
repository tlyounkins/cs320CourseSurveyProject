package edu.ycp.cs320.coursesurvey.model;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 
 * @author Tyler Younkins, Garlan Bowser, Cory Dawson
 *
 */
public class Institution {

	private String name;
	private ArrayList<AdminAccount> adminList;
	private ArrayList<GeneralUser> userList;
	private ArrayList<Course> courseList;
	/**
	 * Constructor
	 * @param name
	 */
	public Institution (String name) {
		this.name = name;
		adminList = new ArrayList<AdminAccount>();
		userList = new ArrayList<GeneralUser>();
		courseList = new ArrayList<Course>();
	}
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
	 * Create a Admin account and add it to ArrayList adminAccount. The account name and password must have > 5 characters. 
	 * @param adminName
	 * @param adminPassword
	 */
	public void createAdminAccount (String adminName, String adminPassword) {
		AdminAccount newAccount = new AdminAccount(adminName, adminPassword);
		adminList.add(newAccount);
	}

	/**
	 * Get adminAccount
	 * @return adminAccount
	 */
	public ArrayList<AdminAccount> getAdminList () {
		return adminList;
	}

	/**
	 * Searches the adminList and returns the index of the Admin account. If admin name is not found return -1.
	 * @param name
	 * @return index 
	 */
	public int findAdminIndex (String name) {
		int i = 0;
		for (Iterator<AdminAccount> itr = adminList.iterator(); itr.hasNext(); i++ ) {
			if (name == itr.next().getName()) {
				return i;
			}
		}
		return -1;
	}
	/**
	 * Creates a new General User.
	 * @param userName
	 * @param userPassword
	 */
	public void createGeneralUser (String userName, String userPassword) {
		GeneralUser newUser = new GeneralUser (userName, userPassword);
		userList.add(newUser);
	}

	/**
	 *  Finds the index of a specific General User. If the GeneralUser name is not found return -1.
	 * @param name
	 * @return index
	 */
	public int findGeneralIndex (String name) {
		int index = 0;
		for (Iterator<GeneralUser> itr = userList.iterator(); itr.hasNext(); index++) {
			if (name == itr.next().getGenName()) {
				return index;
			}
		}
		return -1;
	}
	/**
	 * Add a new course to the coureList ArrayList.
	 * @param title
	 * @param dept
	 * @param schoolYear
	 * @param term
	 */
	public void createCourse (String title, String dept, int schoolYear, String term) {
		Course newCourse = new Course (title, dept, schoolYear, term);
		courseList.add(newCourse);
	}
	
}

