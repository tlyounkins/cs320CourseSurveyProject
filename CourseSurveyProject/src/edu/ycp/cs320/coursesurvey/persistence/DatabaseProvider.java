package edu.ycp.cs320.coursesurvey.persistence;

public class DatabaseProvider {
	private static FakeDatabase theInstance;
	
	public static void setInstance(FakeDatabase fakeDatabase) {
		theInstance = fakeDatabase;
	}
	
	public static FakeDatabase getInstance() {
		if (theInstance == null) {
			throw new IllegalStateException("IDatabase instance has not been set!");
		}
		return theInstance;
	}
}
