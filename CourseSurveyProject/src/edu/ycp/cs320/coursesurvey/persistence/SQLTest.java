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
	
	public static void clearDB(){
		DatabaseProvider.getInstance().clearDB();
		System.out.println("cleared DB");
	}
}
