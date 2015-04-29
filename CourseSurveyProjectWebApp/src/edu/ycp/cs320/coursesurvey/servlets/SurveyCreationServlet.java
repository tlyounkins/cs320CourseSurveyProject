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
