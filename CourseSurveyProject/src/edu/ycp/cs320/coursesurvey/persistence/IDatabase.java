package edu.ycp.cs320.coursesurvey.persistence;

import java.util.List;

import edu.ycp.cs320.coursesurvey.model.AdminAccount;
import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.Section;

public interface IDatabase {
	//example from Lab06
	//public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title);
	public Institution findInstitution (String instName);
	
	public AdminAccount findAdminAccountByAdminName (String accountName);
	
	public void addAdmin (String adminName, String password, int instId);
	
	public int addInstitution (String instName);

	public Course findCourse(String course);
	
	public Section findSection(String section);

	public void addSurvey(String surveyName);
		
}
