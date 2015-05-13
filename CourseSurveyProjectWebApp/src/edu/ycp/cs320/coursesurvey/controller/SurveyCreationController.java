package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class SurveyCreationController {
	boolean done;
	public int createSurvey(String course, String section, User user, String surveyName) {
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
			return newSurveyID;
		} else {
			done = false;
			return -1;
		}

	}
	public void addQuestion(User sessionUser, int newSurveyID, int question_type, String question, String option[]) {
		int instID = sessionUser.instID();
	
		DatabaseProvider.getInstance().addToTemplate(instID, newSurveyID, question_type, question);
		System.out.println("new question added to template." );
	}
	public boolean done(){
		return this.done;
	}

}
