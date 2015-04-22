package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.coursesurvey.controller.LoginController;


public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Just forward to the accountCreation
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//Institution nInstitution = new Institution (req.getParameter("institutionName"));

		//initialize variables to receive input from form
		//String instName = req.getParameter("institutionName");
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		//String error = null;
		
		//initiailizes the controller class
		LoginController controller = new LoginController();
		
		//make sure password is as intended then create account for the institution
		if (controller.login(accountName, password)){
			req.getRequestDispatcher("/_view/adminHomePages.jsp").forward(req, resp);
		}
		
		//req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
		
	
		//goes to admin home page if account info is incorrect
		//if (controller.passwordsMatching() && controller.done()){
		//	System.out.println("done");
		//	req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
		//}
	
			
		}

}
