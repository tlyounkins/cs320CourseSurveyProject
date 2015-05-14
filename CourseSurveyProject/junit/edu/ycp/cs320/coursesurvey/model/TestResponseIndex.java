package edu.ycp.cs320.coursesurvey.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestResponseIndex {

	
	ResponseIndex respIndex;
	
	@Before 
	public void setup () {
		respIndex =new ResponseIndex();
		respIndex.setResponseID(4);
	}
	@Test	
	public void TestGetResponseID () {
		assertEquals (4, respIndex.getResponseID());
	}

	
}
