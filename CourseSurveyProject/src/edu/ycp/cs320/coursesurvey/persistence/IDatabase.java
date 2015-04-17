package edu.ycp.cs320.coursesurvey.persistence;

import java.util.List;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.Section;

public interface IDatabase {
	//example from Lab06
	//public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title);
	public Institution findInstitution (String instName);
	
	public User findUserAccountByName (String accountName, int instID);
	
	public int addUser (String userName, String password, int instId, boolean student, boolean prof, boolean admin);
	
	public int addInstitution (String instName);
	
	public int addCourse(int instID, String title, String dept, int year, String term);

	public Course findCourse(String course);
	
	public Section findSection(String section);

	public void addSurvey(String surveyName);
		
}
