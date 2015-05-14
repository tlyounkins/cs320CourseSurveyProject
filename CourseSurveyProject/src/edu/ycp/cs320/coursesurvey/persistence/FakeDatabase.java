package edu.ycp.cs320.coursesurvey.persistence;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.*;

public class FakeDatabase  implements IDatabase{

	private List<Institution> institutionTable;
	private List<ArrayList<User>> userTables;
	private List<ArrayList<Course>> courseTables;
	private List<ArrayList<ArrayList<Section>>> sectionTables;
	private List<ArrayList<Survey>> surveyTables;
	private List<ArrayList<ArrayList<ResponseIndex>>> responseIndexTables;
	private List<ArrayList<ArrayList<Template>>> templateTables;
	private List<ArrayList<ArrayList<ArrayList<Response>>>> responseTables;

	public FakeDatabase() {
		this.institutionTable = new ArrayList<Institution>();
		this.userTables = new ArrayList <ArrayList<User>>();
		this.courseTables = new ArrayList <ArrayList<Course>>();
		this.sectionTables = new ArrayList <ArrayList<ArrayList<Section>>>();
		this.surveyTables = new ArrayList <ArrayList<Survey>>();
		this.responseIndexTables = new ArrayList <ArrayList<ArrayList<ResponseIndex>>>();
		this.templateTables = new ArrayList <ArrayList<ArrayList<Template>>>();
		this.responseTables = new ArrayList <ArrayList<ArrayList<ArrayList<Response>>>>();
		
	}
	@Override
	public User findUserAccountByName(String accountName, int instID) {
		//grabs the institution information
		Institution inst = this.institutionTable.get(instID-1);
		
		//grabs the user table for the institution
		ArrayList<User> instUserTable = this.userTables.get(inst.getInstID()-1); 
		
		for (User user : instUserTable) {
			if (user.getUserName().equals(accountName)) {
				System.out.println(accountName);
				return user;
			}
		}
		return null;
	}
	
	@Override
	public Institution findInstitution (String instName) {
		for (Institution institution : this.institutionTable) {
			if (institution.getName().equals(instName)) {
				return institution;
			}
		}
		return null;
	}
	
	@Override
	public int addInstitution (String instName) {
		
		//creates ID values for pertinent tables
		int instID = this.institutionTable.size()+1;
		
		
		//institutionList.get(index).setName(instName);
		//institutionList.get(index).setInstId(instId);
		
		this.institutionTable.add(new Institution());
		this.institutionTable.get(instID - 1).setInstID(instID);
		this.institutionTable.get(instID - 1).setName(instName);
		this.institutionTable.get(instID - 1).setNumUsers(0);
		this.institutionTable.get(instID - 1).setNumCourses(0);
		this.institutionTable.get(instID - 1).setNumSurveys(0);
		
		this.courseTables.add(new ArrayList<Course>());
		this.userTables.add(new ArrayList<User>());
		this.surveyTables.add(new ArrayList<Survey>());
		this.sectionTables.add(new ArrayList<ArrayList<Section>>());
		
		System.out.println("Institution Name is " + this.institutionTable.get(instID-1).getName());
		System.out.println("institution id is " + this.institutionTable.get(instID-1).getInstID());
		
		//returns the id to be stored if needed (look at account creation controller for an ex.)
		return instID;
	}
	
	@Override
	public int addUser (String userName, String password, int instId, boolean student, boolean prof, boolean admin) {
		
		ArrayList<User> userTable = this.userTables.get(instId-1);
		Institution institute  = this.institutionTable.get(instId-1);
		//generates new userID
		int newID = institute.getNumUsers()+1;
		
		//updates number of users
		institute.setNumUsers(newID);
		
		userTable.add(new User());
		userTable.get(newID-1).setInstID(instId);
		userTable.get(newID-1).setUserID(newID);
		userTable.get(newID-1).setUserName(userName);
		userTable.get(newID-1).setPassword(password);
		userTable.get(newID-1).setStudent(student);
		userTable.get(newID-1).setProf(prof);
		userTable.get(newID-1).setAdmin(admin);
		
		
		System.out.println("user name is " + userTable.get(newID-1).getUserName() );
		System.out.println("user id is " + userTable.get(newID-1).getUserID());
		System.out.println("user password is " + userTable.get(newID-1).getPassword());
		if (student) System.out.println("user is a student");
		if (prof) System.out.println("user is a profesor");
		if (admin) System.out.println("user is an admin");
		
		return newID;
	}
	
	@Override
	public int addCourse(int instID, String title, String dept, int year, String term){
		ArrayList<Course> courseTable = this.courseTables.get(instID-1);
		
		Institution institute  = this.institutionTable.get(instID-1);
		//generates new userID
		int newID = institute.getNumCourses()+1;
		
		//updates number of users
		institute.setNumCourses(newID);
		
		this.sectionTables.get(instID-1).add(new ArrayList<Section>());
		courseTable.add(new Course());
		courseTable.get(newID-1).setCourseID(newID);
		courseTable.get(newID-1).setCourseTitle(title);
		courseTable.get(newID-1).setDept(dept);
		courseTable.get(newID-1).setSchoolYear(year);
		courseTable.get(newID-1).setTerm(term);
		
		System.out.println(title + " has been added to the courses");
		System.out.println("ID for instID is" + (instID-1));
		
		return newID;
	}

	/*//this function is used to add an already existing user to a course
	@Override
	public void addToSectionTable(int instID, int courseID, int sectID, int userID, boolean student, boolean prof){
		//ArrayList<Course> courseTable = this.courseTables.get(instID-1);
		ArrayList<Section> sectionTable = this.sectionTables.get(instID-1).get(courseID-1);
		
		Section newEntry = new Section();
		newEntry.setSectID(sectID);
		newEntry.setUserID(userID);
		newEntry.setStudent(student);
		newEntry.setProf(prof);
		
		sectionTable.add(newEntry);
	}
	*/
	@Override
	public int addSurvey(int instID, int courseID, int creatorID, String surveyName){
		ArrayList<Survey> surveyTable = this.surveyTables.get(instID-1);
		
		Institution institute  = this.institutionTable.get(instID-1);
		//generates new surveyID
		int newID = institute.getNumSurveys()+1;
		
		//updates number of survey
		institute.setNumSurveys(newID);
		
		//create new Survey
		Survey newSurvey = new Survey();
		
		//set surveyInfo
		newSurvey.setSurveyID(newID);
		newSurvey.setCourseID(courseID);
		newSurvey.setCreatorID(creatorID);
		newSurvey.setSurveyName(surveyName);
		
		// TODO - fix me ! out of bounds
		//create responseIndex table and template table based on newID
		//this.templateTables.get(instID-1).add(new ArrayList<Template>());
		//this.responseIndexTables.get(instID-1).add(new ArrayList<ResponseIndex>());
		
		surveyTable.add(newSurvey);
		
		System.out.println(surveyName + " has been added to the surveys");
		
		return newID;
	}
	
	@Override 
	public Survey findSurveyByID (int instID, int surveyID) {
		ArrayList<Survey> surveyTable = this.surveyTables.get(instID-1);
		Survey survey = surveyTable.get(surveyID-1);
		System.out.println("Survey name is " + survey.getSurveyName());
		return survey;
	}
	// Option arguement is for multiple choice, 5 element array with different multiple choice options in there
	@Override
	public void addToTemplate(int instID, int surveyID, int questionType, String question){
		ArrayList<Template> template = this.templateTables.get(instID-1).get(surveyID-1);
		int questionNum = template.size();
		
		Template newQuestion = new Template();
		newQuestion.setQuestionNum(questionNum);
		newQuestion.setQuestionType(questionType);
		newQuestion.setQuestion(question);
		template.add(newQuestion);
	}
	
	
	//due to maintaining anonymity of the user, we cannot keep track of which table an individual users responses are in, so all responses must be submitted 
	//bundled into an arraylist
	@Override
	public void submitResponse(int instID, int surveyID, ArrayList<Response> responses){
		ArrayList<ResponseIndex> responseIndex = this.responseIndexTables.get(instID-1).get(surveyID-1);
		ArrayList<ArrayList<Response>> rTables = this.responseTables.get(instID-1).get(surveyID-1);
		
		int newID = rTables.size()+1;
		//adds response to the tables
		rTables.add(responses);
		
		//create and add responseIndex entry for finding the response later
		ResponseIndex newEntry = new ResponseIndex();
		newEntry.setResponseID(newID);
		
		responseIndex.add(newEntry);
	}
	
	
	
	@Override
	public Course findCourseByName(String course, int instID) {
		ArrayList<Course> courseTable = this.courseTables.get(instID-1);
		System.out.println("finding course by name instID is " + (instID-1));
		for (Course courseItr : courseTable) {
			System.out.println("itr name for course find " + courseItr.getCourseTitle());
			System.out.println("name for course to find is " + course); 
			if (courseItr.getCourseTitle().equals(course)){
				System.out.println("found course!");
				return courseItr;
			}
		}
		return null;
	}

	@Override
	public void createTables() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Boolean clearDB(){
		return false;
	}
	@Override
	public Institution findInstitutionByID(int instID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Template> findSurveyQuesitons(int instID) {
		// TODO Auto-generated method stub
		return null;
	}
}
