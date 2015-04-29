package edu.ycp.cs320.coursesurvey.model;

public class Survey {

	private int courseID;
	private int creatorID;
	private int surveyID;
	private String surveyName;
	
	public void setCourseID(int courseID){
		this.courseID = courseID;
	}
	
	public int getCourseID(){
		return this.courseID;
	}
	
	public void setCreatorID(int creatorID){
		this.creatorID = creatorID;
	}
	
	public int getCreatorID(){
		return this.creatorID;
	}
	
	public void setSurveyID(int surveyTableID){
		this.surveyID = surveyTableID;
	}
	
	public int getSurveyID(){
		return this.surveyID;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}


}
