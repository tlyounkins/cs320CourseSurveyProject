package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		
		//Institution nInstitution = new Institution (req.getParameter("institutionName"));

		//initialize variables to receive input from form
		String instName = req.getParameter("institutionName");
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String passwordCheck = req.getParameter("passwordConfirm");
		String error = null;
		
		//initiailizes the controller class
		AccountCreationController controller = new AccountCreationController();
		
		//make sure password is as intended then create account for the institution
		controller.createAccount(instName, accountName, password, passwordCheck);
		
		
		req.setAttribute("create", controller);
		
		//req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
		
	
		//goes to admin home page if account info is incorrect
		if (controller.passwordsMatching() && controller.done()){
			System.out.println("done");
			req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
		}
		//will remain on account creation if institution already exist or the passwords do not match
		else{
			if (!controller.passwordsMatching()){
				if (error == null) error = "Errors :";
				error += "*Password mismatch - please make sure your password is typed correctly \n";
			}
			if (controller.instAlreadyExists()){
				if (error == null) error = "Errors :";
				error += "*This institution already exists, please conact admin to create a new account \n";
			}

			req.setAttribute("errorMessage", error);
			req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
			
		}
		
		
	}
}
