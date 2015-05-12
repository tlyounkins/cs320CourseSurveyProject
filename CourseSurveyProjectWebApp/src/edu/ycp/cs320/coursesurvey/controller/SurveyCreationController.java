package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class SurveyCreationController {
	boolean done;
	public void createSurvey(String course, String section, User user, String surveyName) {
		System.out.println("survey creation controller running");

		// Check if course exists 
		if (DatabaseProvider.getInstance().findCourseByName(course, user.instID())!=null) { 
			int courseID = DatabaseProvider.getInstance().findCourseByName(course, user.instID()).getCourseID();
			System.out.println("courseID found is " + courseID);
			
			//Create new Survey and Save ID
			int newSurveyID = DatabaseProvider.getInstance().addSurvey(user.instID(), courseID, user.getUserID(), surveyName);
			done = true;
			System.out.println("survey sucessfully created!");
			
			System.out.println("New Survey ID is" + DatabaseProvider.getInstance().findSurveyByID(user.instID(), newSurveyID));
		
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
