package edu.ycp.cs320.coursesurvey.model;

public class Template {
	private int questionNum;
	private int questionType; // 1 = Multiple choice 2 = Free form
	private String question;
	
	public void setQuestionNum(int questionNum){
		this.questionNum = questionNum;
	}
	
	public int getQuestionNum(){
		return this.questionNum;
	}
	
	public void setQuestionType(int questionType){
		this.questionType = questionType;
	}
	
	public int getQuestionType(){
		return this.questionType;
	}
	
	public void setQuestion(String question){
		this.question = question;
	}
	
	public String getQuestion(){
		return this.question;
	}
	
}
