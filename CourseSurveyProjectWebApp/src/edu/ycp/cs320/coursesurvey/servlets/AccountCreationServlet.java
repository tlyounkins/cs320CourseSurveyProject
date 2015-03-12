package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.coursesurvey.model.Institution;

public class AccountCreationServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Just forward to the view
		req.getRequestDispatcher("/_view/accountCreation.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			Institution nInstitution = new Institution (req.getParameter("institution"));
			
			nInstitution.createAdminAccount(req.getParameter("account"), req.getParameter("password"));
		
	}
}