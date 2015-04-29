package edu.ycp.cs320.coursesurvey.model;

public class Template {
	private int questionNum;
	private int questionType; // 1=Yes or No, 2=Multiple choice 3=Free form
	private String question;
	private String option[] = new String[5];
	
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
	
	public void setOption(int i, String option){
		this.option[i] = option;
	}
	
	public String getOption(int i){
		return this.option[i];
	}
	
}
