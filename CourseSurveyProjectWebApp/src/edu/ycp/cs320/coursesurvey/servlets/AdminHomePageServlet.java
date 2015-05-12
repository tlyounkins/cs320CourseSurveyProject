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
			
		String newCourse = req.getParameter("courseID");
		String newDepartment = req.getParameter("deptID");
		String newYear = req.getParameter("yearID");
		String newTerm = req.getParameter("termID");
		String addedAccountName = req.getParameter("accountName");
		String newuserPassword = req.getParameter("newuserPassword");
		String permissions = req.getParameter("permissions");
		
		System.out.println("AdminHomePageServlet:");
		System.out.println("newCourse from form is " +newCourse);
		System.out.println("newDepartment from form is " +newDepartment);
		System.out.println("newYear from form is " +newYear);
		System.out.println("newTerm from form is " +newTerm);
		System.out.println("addedAccoutName from form is  " +addedAccountName);
		System.out.println("newuserPassword from form is " +newuserPassword);
		System.out.println("permission from form is " +permissions);
		// temporarily setting the new user's password to addedAccountName + "Password1"
		// to satisfy login password requirements
		//String password = addedAccountName + "Password1";
		
		HttpSession session = req.getSession();
		User sessionUser = (User) session.getAttribute("user");
		//int instID = sessionUser.instID();

		//User adminUser = (User) session.getAttribute("user");
		//System.out.println(adminUser);
		//System.out.println(adminUser.getUserID());
		//int instID = adminUser.instID();
		String name = sessionUser.getUserName();
		req.setAttribute("admin", name);
		//session.getAttribute("institution") returns null
		//String inst = (String) session.getAttribute("institution");
	//	System.out.println("inst name is " +inst);
		//String inst = "YCP";
		AdminController controller = new AdminController();
		
		if (!newCourse.isEmpty()) {
			controller.addCourse(sessionUser, newCourse);
		}
	
		if (!addedAccountName.isEmpty() && !newuserPassword.isEmpty()) {
			//if (controller.userExists(inst, addedAccountName)) {
				controller.addUser(sessionUser, addedAccountName, newuserPassword, permissions);
			/*} else {
				String error = "Errors :";
					error += "*This user already exists, please enter a new user name \n";
				req.setAttribute("errorMessage", error);
			
			}*/
		}
		
		
		// Just forward to the adminHomePage
		req.getRequestDispatcher("/_view/adminHomePage.jsp").forward(req, resp);
	}
}
