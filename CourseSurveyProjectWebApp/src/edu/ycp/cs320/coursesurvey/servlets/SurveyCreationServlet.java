package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.coursesurvey.controller.AccountCreationController;
import edu.ycp.cs320.coursesurvey.controller.SurveyCreationController;
import edu.ycp.cs320.coursesurvey.model.Institution;

public class SurveyCreationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Just forward to the surveyCreation
		req.getRequestDispatcher("/_view/surveyCreation.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		

		String courseID = req.getParameter("courseID");
		String sectionID = req.getParameter("sectionID");
		String surveyName = req.getParameter("surveyName");
		
		String question1_type = req.getParameter("Question1_type");
		System.out.println("Question1 is: " + question1_type);
		String question1 = req.getParameter("Question1");
		System.out.println("question1 is: " + question1);
		
		String question2_type = req.getParameter("Question2_type");
		System.out.println("Question2 is: " + question2_type);
		String question2 = req.getParameter("Question2");
		System.out.println("question2 is: " + question2);
		
		String question3_type = req.getParameter("Question3_type");
		System.out.println("Question3 is: " + question3_type);
		String question3 = req.getParameter("Question3");
		System.out.println("question3 is: " + question3);
		
		//initializes the controller class
		SurveyCreationController controller = new SurveyCreationController();
		
		controller.createSurvey(courseID,sectionID,surveyName);
		//req.setAttribute("create", controller);
		
		//goes to admin home page if survey has been created 
		if (controller.done()){
			System.out.println("done");
			req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
		}
	}
}
