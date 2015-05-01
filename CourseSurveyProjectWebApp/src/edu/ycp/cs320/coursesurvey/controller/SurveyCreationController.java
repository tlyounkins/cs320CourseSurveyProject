package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class SurveyCreationController {
	boolean done;
	public void createSurvey(String course, String section, User adminUser, String surveyName) {
		System.out.println("survey creation controller running");
	
	//TODO make it so that this will choose a specific inst  and course
		
		// Check if course and section to create a survey for exists. 
		
		if (DatabaseProvider.getInstance().findCourseByName(course, adminUser.instID())!=null) { 
				//&& DatabaseProvider.getInstance().findSection(section) != null) {
			int courseID = DatabaseProvider.getInstance().findCourseByName(course, adminUser.instID()).getCourseID();
			System.out.println("courseID found is " + courseID);
			
			// TODO - Deal with section
			//int sectionID = DatabaseProvider.getInstance().findSection(section).getSectID();

		// Add survey
		
		int newSurveyID = DatabaseProvider.getInstance().addSurvey(adminUser.instID(), courseID, adminUser.getUserID(), surveyName);
		done = true;
		System.out.println("survey sucessfully created!");
		DatabaseProvider.getInstance().findSurveyByID(adminUser.instID(), newSurveyID);
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
