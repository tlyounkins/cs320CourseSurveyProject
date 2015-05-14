package edu.ycp.cs320.coursesurvey.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CourseTest {

	Course course;
	
	@Before 
	public void setup () {
		course = new Course();
		course.setCourseID(5);
		course.setCourseTitle("CS101");
		course.setDept("Computer Science");
		course.setSchoolYear(2015);
		course.setTerm("Spring");
	
	}
	
	@Test
	public void testGetCourseTitle () {
		assertEquals("CS101", course.getCourseTitle());
	}
	@Test
	public void testGetCourseID () {
		assertEquals(5, course.getCourseID());
	}
	@Test
	public void testGetDept () {
		assertEquals("Computer Science", course.getDept());
	}
	
	@Test
	public void testGetSchoolYear() {
		assertEquals(2015, course.getSchoolYear());
	}
	
	@Test
	public void testGetTerm () {
		assertEquals("Spring", course.getTerm());
	}
}
