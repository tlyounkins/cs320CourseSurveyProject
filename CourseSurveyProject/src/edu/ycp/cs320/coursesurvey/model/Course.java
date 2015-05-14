package edu.ycp.cs320.coursesurvey.model;

public class Course {

	private String courseTitle;
	private String dept;
	private int schoolYear;
	private String term;
	private int courseID;

	public String getCourseTitle () {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	
	public int getCourseID () {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getDept () {
		return dept;
	}

	public void setDept (String dept) {
		this.dept = dept;
	}

	public int getSchoolYear () {
		return schoolYear;
	}

	public void setSchoolYear (int schoolYear) {
		this.schoolYear = schoolYear;
	}

	public String getTerm () {
		return term;
	}

	public void setTerm (String term) {
		this.term = term;
	}

	/*public int getSectionTableID () {
		return sectionTableID;
	}*/
	
	

	/*public void addSection (int section) {
		Section newSection = new Section(section);
		sectionList.add(newSection);
	}

	public int getSectionIndex (int section) {
		int index = 0;
		for (Iterator<Section> itr = sectionList.iterator(); itr.hasNext(); index++) {
			if (section == itr.next().getSect()) {
				return index;
			}
		}
		return -1;
	}*/
	
}
