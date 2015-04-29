package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.controller.AccountCreationController;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;
import edu.ycp.cs320.coursesurvey.persistence.IDatabase;

public class AccountCreationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		// Just forward to the accountCreation
		req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		

		//initialize variables to receive input from form
		String instName = req.getParameter("institutionName");
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String passwordCheck = req.getParameter("passwordConfirm");
		String error = null;
		
		//initializes the controller class
		AccountCreationController controller = new AccountCreationController();
		
		//make sure password is as intended then create account for the institution
		controller.createAccount(instName, accountName, password, passwordCheck);

		//For logging user session *req.getSession(bool) can also be used here
		HttpSession session = req.getSession();
		
		//req.setAttribute("create", controller);
		
		//req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
		
	
		//goes to admin home page if account info is incorrect
		if (controller.passwordsMatching() && controller.done()){
			//Set user name to session attribute 
			//"user" is currently a string (accountName) should be changed to a User Object later
			session.setAttribute("user", accountName);
			System.out.println("testing session value " + session.getAttribute("user"));
			System.out.println("done");
			//req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
			resp.sendRedirect(req.getContextPath() + "/adminHomePage");
			return;
		}
		//will remain on account creation if institution already exist or the passwords do not match
		else{
			if (!controller.passwordsMatching()){
				if (error == null) error = "Errors :";
				error += "*Password mismatch - please make sure your password is typed correctly \n";
			}
			if (controller.instAlreadyExists()){
				if (error == null) error = "Errors :";
				error += "*This institution already exists, please contact admin to create a new account \n";
			}

			req.setAttribute("errorMessage", error);
			req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
			
		}
		
		
	}
}
