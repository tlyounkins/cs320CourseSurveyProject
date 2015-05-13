package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class CompleteSurveyController {
	public boolean anyOpenSurveys(User sessionUser, String Course) {
		int instID = sessionUser.instID();
		Institution userInstitution = DatabaseProvider.getInstance().findInstitutionByID(instID);
		if (userInstitution.getNumSurveys() == 0) {
			return false;
		}
		return true;	
	}
	
}
