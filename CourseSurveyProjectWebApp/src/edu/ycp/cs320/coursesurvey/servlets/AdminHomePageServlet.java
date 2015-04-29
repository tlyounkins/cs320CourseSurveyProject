package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.coursesurvey.controller.AdminController;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.User;

public class AdminHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Just forward to the view
		req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
			
		String addedAccountName = req.getParameter("accountName");
		String newCourse = req.getParameter("courseID");
		String newSection = req.getParameter("sectionID");
		String permissions = req.getParameter("permissions");
		System.out.println("AdminHomePageServlet:");
		System.out.println(addedAccountName);
		System.out.println(newCourse);
		System.out.println(newSection);
		System.out.println(permissions);
		
		String password = addedAccountName; // temporarily setting the new user's password to addedAccountName
		HttpSession session = req.getSession();
		// if there is no session created already, than it will create a new one.
		User admin;
		User adminUser = (User) session.getAttribute("user");
		System.out.println(adminUser);
		System.out.println(adminUser.getUserID());
		int instID = adminUser.instID();
		
		AdminController controller = new AdminController();
		if (addedAccountName != null) {
			controller.addUser(instID, addedAccountName, password, permissions);
		}
		
		
		
		// Just forward to the adminHomePage
		req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
	}
}
