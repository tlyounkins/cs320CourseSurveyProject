package edu.ycp.cs320.coursesurvey.model;

public class GeneralUser {

	private String genName, genPassword;
	
	public GeneralUser (String genName, String genPassword) {
		this.genName = genName;
		this.genPassword = genPassword;
	}

	public String getGenName () {
		return genName;
	}

	public void setGenName (String genName) {
		this.genName = genName;
	}

	public String getGenPassword () {
		return genPassword;
	}

	public void setGenPassword (String genPassword) {
		this.genPassword = genPassword;
	}

	
}
