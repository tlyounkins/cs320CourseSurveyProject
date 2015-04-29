package edu.ycp.cs320.coursesurvey.controller;

import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.controller.AccountCreationController;
import edu.ycp.cs320.coursesurvey.persistence.DatabaseProvider;
import edu.ycp.cs320.coursesurvey.persistence.FakeDatabase;
import edu.ycp.cs320.coursesurvey.persistence.IDatabase;
import edu.ycp.cs320.coursesurvey.model.User;

public class LoginController {


	public boolean login(String accountName, String password){
		User user = DatabaseProvider.getInstance().findUserAccountByName(accountName, 1); 
		
		if (user != null){
			if (user.getPassword().equals(password)) {
				System.out.println(user);
				System.out.println(password);
				return true;
			} 
		}
		
		return false;
	}

}
