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
		// Just forward to the login
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		//initialize variables to receive input from form
		String instName = req.getParameter("institutionName");
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String error = null;
		
		//Initializes the controller class
		
		LoginController controller = new LoginController();
		
		//if (controller.login(accountName, password)){
		System.out.println("Login forwarding to adminHomePage.jsp");
			//req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
		resp.sendRedirect(req.getContextPath() + "/adminHomePage");
		return;
		//} 
		/*(else {
			//will remain on login if accountName and password are incorrect for that institution
			if (!controller.login(accountName, password)){
				if (error == null) error = "Errors :";
					error += "*Password mismatch - please make sure your institution, accountName and password are typed correctly \n";
				}
		
				req.setAttribute("errorMessage", error);
				req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			}*/
			
	}

}
