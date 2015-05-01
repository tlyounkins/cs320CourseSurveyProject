package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.coursesurvey.controller.LoginController;
import edu.ycp.cs320.coursesurvey.model.User;


public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Just forward to the login
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		//System.out.println("In LoginServlet doPost");
		//initialize variables to receive input from form
		String instName = req.getParameter("institutionName");
		String accountName = req.getParameter("accountName");
		String password = req.getParameter("password");
		String error = null;
		System.out.println(instName);
		System.out.println(accountName);
		System.out.println(password);
		
		//Initializes the controller class
		LoginController controller = new LoginController();
		System.out.println("Login Controller created");
		
		//For logging user session *req.getSession(bool) can also be used here
		HttpSession session = req.getSession();
		
		session.setAttribute("user", controller.createUserSession(accountName, instName));
		System.out.println("Session Created");
		
	
		
		User sessionUser = (User) session.getAttribute("user");
		
		if (sessionUser == null) {
			System.out.println("SessionUser is null");
			if (error == null) { error = "Errors :";
			error += "*Login incorrect - please make sure your Institution, Account Name and Password are typed correctly \n";
			}

			req.setAttribute("errorMessage", error);
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			//resp.sendRedirect(req.getContextPath() + "/login");
			return;
			
		}
		
		System.out.println(sessionUser.getUserID());
		
		if (controller.login(instName,accountName, password)) {
			if (controller.isAdminTest(sessionUser)) {
				
				System.out.println("Login forwarding to adminHomePage.jsp");
				resp.sendRedirect(req.getContextPath() + "/adminHomePage");
				return;
			} else if (controller.isProfTest(sessionUser)) { // admins and profs go to the same page for now
				System.out.println("Login forwarding to adminHomePage.jsp");
				resp.sendRedirect(req.getContextPath() + "/adminHomePage");
				return;
			} else {	// by default, the user must be a student
				System.out.println("Login forwarding to GeneralUserHomePage.jsp");
				resp.sendRedirect(req.getContextPath() + "/generalUserHomePage");
				return;
			}
		} else {
			//will remain on login if accountName and password are incorrect for that institution
			if (!controller.login(instName,accountName, password)){
				System.out.println("login doesn't exist");
				if (error == null) error = "Errors :";
					error += "*Login incorrect - please make sure your Institution, Account Name and Password are typed correctly \n";
				}
		
				req.setAttribute("errorMessage", error);
				//req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
				resp.sendRedirect(req.getContextPath() + "/login");
				return;
			}
			
	}

}
