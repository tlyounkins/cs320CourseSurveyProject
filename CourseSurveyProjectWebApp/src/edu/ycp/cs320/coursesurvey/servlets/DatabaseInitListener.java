package edu.ycp.cs320.coursesurvey.servlets;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;
import edu.ycp.cs320.coursesurvey.persistence.SqliteDatabase;

public class DatabaseInitListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent e) {
		// Initialize database
		System.out.println("Initializing database!");
		DatabaseProvider.setInstance(new FakeDatabase());

		//DatabaseProvider.setInstance(new SqliteDatabase());
	}

	public void contextDestroyed(ServletContextEvent e) {
		// Nothing to do
	}

}