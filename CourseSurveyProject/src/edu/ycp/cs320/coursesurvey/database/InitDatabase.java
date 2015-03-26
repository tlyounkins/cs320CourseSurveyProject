package edu.ycp.cs320.coursesurvey.database;


import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;

public class InitDatabase {
	public static void init() {
	
			DatabaseProvider.setInstance(new FakeDatabase());
	}
}
