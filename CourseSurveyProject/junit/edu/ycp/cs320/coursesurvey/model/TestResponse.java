package edu.ycp.cs320.coursesurvey.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class TestResponse {
	Response resp;
	
	@Before
	public void setup() {
		resp =new Response();

		resp.setAnswer("Worst Episode Ever");
		resp.setQuestionNum(2);
	}

	@Test
	public void TestGetAnswer() {
		assertEquals("Worst Episode Ever", resp.getAnswer());
	}
	
	@Test
	public void TestQuestionNum () {
		assertEquals(2, resp.getQuestionNum());
	}
}
