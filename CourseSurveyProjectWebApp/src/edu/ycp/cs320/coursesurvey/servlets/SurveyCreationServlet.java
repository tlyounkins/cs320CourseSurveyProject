package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.coursesurvey.controller.SurveyCreationController;
import edu.ycp.cs320.coursesurvey.model.User;

public class SurveyCreationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// Just forward to the surveyCreation
		System.out.println("do get survey running");
		req.getRequestDispatcher("/_view/surveyCreation.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("Do post survey running");

		HttpSession session = req.getSession();
		User sessionUser =  (User) session.getAttribute("user");
		if (sessionUser == null) {
			System.out.println("Please log into your account");
		}
		System.out.println("Current session user for create survey page " + sessionUser.getUserName());
		
		String courseID = req.getParameter("CourseId");
		String sectionID = req.getParameter("SectionId");
		String surveyName = req.getParameter("SurveyName");

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
		System.out.println("survey creation serv course id is " + courseID);
		controller.createSurvey(courseID, sectionID, sessionUser, surveyName);
		//req.setAttribute("create", controller);
		
		//goes to admin home page if survey has been created 
		if (controller.done()){
			System.out.println("doneS survey creation");
			req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
			return;
		}
		else {
			System.out.println("survey creation collroller!=done");
			req.getRequestDispatcher("/_view/surveyCreation.jsp").forward(req, resp);
			return;
		}
	}
}
