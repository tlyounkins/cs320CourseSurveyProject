package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.coursesurvey.model.User;

public class GeneralUserHomePageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession();
		User sessionUser = (User) session.getAttribute("user");
		if (sessionUser == null) {
			System.out.println("no session user, forwarding to login page");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
			return;
		}
		req.getRequestDispatcher("/_view/generalUserHomePage.jsp").forward(req, resp);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		
		String name = (String) session.getAttribute("user");
		//req.setAttribute("student", name);
		// Just forward to the generalUserHomePage
		req.getRequestDispatcher("/_view/generalUserHomePage.jsp").forward(req, resp);
	}
}
