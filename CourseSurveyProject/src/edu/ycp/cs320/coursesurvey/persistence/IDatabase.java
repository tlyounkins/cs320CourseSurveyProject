package edu.ycp.cs320.coursesurvey.persistence;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.Response;
import edu.ycp.cs320.coursesurvey.model.Survey;
import edu.ycp.cs320.coursesurvey.model.Template;
import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.Section;

public interface IDatabase {

	public Institution findInstitution (String instName);
	
	public User findUserAccountByName (String accountName, int instID);
	
	public int addUser (String userName, String password, int instId, boolean student, boolean prof, boolean admin);
	
	public int addInstitution (String instName);
	
	//public int getNextInstID();
	
	public int addCourse(int instID, String title, String dept, int year, String term);

	void createTables();
	
	//public void addToSectionTable(int instID, int courseID, int sectID, int userID, boolean student, boolean prof);
	
	public int addSurvey(int instID, int courseID, int creatorID, String surveyName);
	
	public void addToTemplate(int instID, int surveyID, int questionType, String question);
	
	public void submitResponse(int instID, int surveyID, ArrayList<Response> responses);
	
	public Boolean clearDB();

	public Survey findSurveyByID(int instID, int surveyID);

	public Course findCourseByName(String course, int instID);

	public Institution findInstitutionByID(int instID);

	public List<Template> findSurveyQuesitons(int instID);

}
