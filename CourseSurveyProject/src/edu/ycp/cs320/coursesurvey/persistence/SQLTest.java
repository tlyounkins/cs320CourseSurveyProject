package edu.ycp.cs320.coursesurvey.persistence;

import edu.ycp.cs320.coursesurvey.persistence.*;

//import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class SQLTest {
	
	public static void test1(){
		DatabaseProvider.getInstance().createTables();
		System.out.println("Table created");
	}
	
	public static void test2(){
		DatabaseProvider.getInstance().addInstitution("another test");
		System.out.println("Added a institute");
	}
	
	public static void test3(){
		DatabaseProvider.getInstance().addCourse(1, "Software Engineering", "CS", 2015, "Spring");
		System.out.println("Added a course");
	}
	
	public static void test4(){
		DatabaseProvider.getInstance().addUser("gabowser2", "password", 1, false, false, true);
		System.out.println("Added a user");
	}
	
	public static void test5(){
		DatabaseProvider.getInstance().addSurvey(1, 1, 1, "newSurvey");
		System.out.println("Added a survey");
	}
	
	public static void test6(){
		DatabaseProvider.getInstance().addToSectionTable(1, 1, 1, 1, true, false);
		System.out.println("Added a student to a class");
	}
	
	public static void clearDB(){
		DatabaseProvider.getInstance().clearDB();
		System.out.println("cleared DB");
	}
}
