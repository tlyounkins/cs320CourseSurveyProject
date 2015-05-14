package edu.ycp.cs320.coursesurvey.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;




public class TestSurvey {

	Survey testSurvey;

	@Before
	public void setup() {
		testSurvey = new Survey();
		testSurvey.setCourseID(6);
		testSurvey.setCreatorID(2);
		testSurvey.setSurveyID(8);
		testSurvey.setSurveyName("Final Survey");
	}

	@Test
	public void testGetSurveyCourseID() {
		assertEquals(6, testSurvey.getCourseID());
	}	

	@Test
	public void testGetCreatorID () {
		assertEquals(2, testSurvey.getCreatorID());
	}

	@Test
	public void testGetSurveyID () {
		assertEquals(8, testSurvey.getSurveyID());
	}

	@Test 
	public void testGetSurveyName () {
		assertEquals("Final Survey", testSurvey.getSurveyName());
	}

}
