package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class SurveyCreationController {
	boolean done;
	public void createSurvey(String course, String section, String surveyName) {
		System.out.println("survey creation controller running");
		// Check if course and section to create a survey for exists. 
		if (DatabaseProvider.getInstance().findCourse(course) != null && 
				DatabaseProvider.getInstance().findSection(section) != null) {
				
			// Add survey
			DatabaseProvider.getInstance().addSurvey(surveyName);
			done = true;
			System.out.println("survey sucessfully created!");
		} else {
			done = false;
		}
		
		
	}
	public boolean done(){
		return this.done;
	}

}
