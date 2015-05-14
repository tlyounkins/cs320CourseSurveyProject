package edu.ycp.cs320.coursesurvey.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class InstitutionTest {
	private Institution inst;
	
	@Before
	public void setup() {
		inst = new Institution();
		inst.setInstID(1);
		inst.setName("YCP");
		inst.setNumCourses(1);
		inst.setNumSurveys(1);
		inst.setNumUsers(10);
	}

	@Test
	public void TestGetInstID() {
		assertEquals(1, inst.getInstID());
	}
	@Test 
	public void TestGetName() {
		assertEquals("YCP", inst.getName());
	}

	@Test
	public void TestGetNumCourses () {
		assertEquals(1, inst.getNumCourses());
	}

	@Test
	public void TestGetNumSurveys () {
		assertEquals(1, inst.getNumSurveys());
	}

	@Test
	public void TestGetNumUsers() {
		assertEquals(10, inst.getNumUsers());
	}
	
}
