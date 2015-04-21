package edu.ycp.cs320.coursesurvey.model;

public class Survey {
	private int courseID;
	private int creatorID;
	private int templateTableID;
	private int responseTableID;
	
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
	
	public void setTemplateTableID(int templateTableID){
		this.templateTableID = templateTableID;
	}
	
	public int getTemplateID(){
		return this.templateTableID;
	}
	
	public void setResponseTableID(int responseTableID){
		this.responseTableID = responseTableID;
	}
	
	public int getResponseID(){
		return this.responseTableID;
	}
}
