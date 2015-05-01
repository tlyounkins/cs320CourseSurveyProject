package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class CompleteSurveyController {
	public boolean anyOpenSurveys(String inst, String Course, String Section, String accountName) {
		
		Institution instID = DatabaseProvider.getInstance().findInstitution(inst);
		if (instID.getNumSurveys() == 0) {
			return false;
		}
		return true;	
	}
	
}
