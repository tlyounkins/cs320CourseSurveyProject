package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.controller.AccountCreationController;
import edu.ycp.cs320.coursesurvey.database.InitDatabase;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;
import edu.ycp.cs320.coursesurvey.persistence.IDatabase;

public class AccountCreationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO : Is this the ideal location to initilize the database?
		InitDatabase.init();
		IDatabase db = DatabaseProvider.getInstance();
		// Just forward to the accountCreation
		req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//Institution nInstitution = new Institution (req.getParameter("institutionName"));

		//initialize variables to receive input from form
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String passwordCheck = req.getParameter("passwordConfirm");
			
		//initiailizes the controller class
		AccountCreationController controller = new AccountCreationController();
		
		//sets the institution of the controller
		controller.setInstitution(nInstitution);
			
			
		//make sure password is as intended then create account for the institution
		controller.createAccount(accountName, password, passwordCheck);
		
		
		req.setAttribute("create", controller);
		
		//req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
		
		if (controller.done()){
			System.out.println("done");
		}
		req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
		
		
	}
}
