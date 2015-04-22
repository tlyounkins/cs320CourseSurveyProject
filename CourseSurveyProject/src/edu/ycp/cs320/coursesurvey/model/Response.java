package edu.ycp.cs320.coursesurvey.model;

public class Response {
	private int questionNum;
	private String answer;
	
	public void setQuestionNum(int questionNum){
		this.questionNum = questionNum;
	}
	
	public int getQuestionNum(){
		return this.questionNum;
	}
	
	public void setAnswer(String answer){
		this.answer = answer;
	}
	
	public String getAnswer(){
		return this.answer;
	}
}
