package edu.ycp.cs320.coursesurvey.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import edu.ycp.cs320.coursesurvey.model.Template;
import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;

public class CompleteASurveyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {


		HttpSession session = req.getSession();
		User sessionUser = (User)session.getAttribute("user");
		int surveyID = 1;
		//Survey survey = DatabaseProvider.getInstance().findSurveyByID(sessionUser.getUserID(), surveyID);
		List<Template> questions = new ArrayList<Template>();
		questions = DatabaseProvider.getInstance().findSurveyQuesitons(sessionUser.instID(), surveyID);
		int index = 1;
		for (Template temp : questions) {
			req.setAttribute("question" + index, temp.getQuestion());
			index++;
		}

				// Just forward to the surveyCreation
				req.getRequestDispatcher("/_view/completeAsurvey.jsp").forward(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		//HttpSession session = req.getSession();
		//User sessionUser = (User) session.getAttribute("user");
		
		//CompleteSurveyController controller = new CompleteSurveyController();
		
	
		req.getRequestDispatcher("/_view/generalUserHomePage.jsp").forward(req, resp);


	}

}
