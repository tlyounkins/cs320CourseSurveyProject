package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class SurveyCreationController {
	boolean done;
	
	//TODO make it so that this will choose a specific inst  and course
	public void createSurvey(String course, String section, String surveyName) {
		
		// Check if course and section to create a survey for exists. 
		if (DatabaseProvider.getInstance().findCourse(course) != null && 
				DatabaseProvider.getInstance().findSection(section) != null) {
			
			// Add survey
			DatabaseProvider.getInstance().addSurvey(1, 1, 1, surveyName);
			done = true;
			System.out.println("survey sucessfully created!");
		} else {
			done = false;
		}
		
		
	}
	public void addQuestion(String instName, String survey, String question_type, String Question, String option[]) {
		int instID = DatabaseProvider.getInstance().findInstitution(instName).getInstID();
		//int surveyID = 1;
		//int Questiontype = 1;
		
		//DatabaseProvider.getInstance().addToTemplate(instID, surveyID, Questiontype, Question, option);;
	}
	public boolean done(){
		return this.done;
	}

}
