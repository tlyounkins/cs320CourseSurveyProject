package edu.ycp.cs320.coursesurvey.model;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestTemplate {

	Template testTemplate;
	@Before 
	public void setup() {
		testTemplate = new Template();

		testTemplate.setQuestion("We know the answer. What is the question?");
		testTemplate.setQuestionNum(1);
		testTemplate.setQuestionType(1);


	}

	@Test
	public void testGetQuestion () {
		assertEquals("We know the answer. What is the question?", testTemplate.getQuestion());
	}
	
	@Test
	public void testGetQuestionNum () {
		assertEquals(1, testTemplate.getQuestionNum());
	}

	@Test
	public void testGetQuestionType () {
		assertEquals(1, testTemplate.getQuestionType());
	}

}
