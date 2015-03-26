package edu.ycp.cs320.coursesurvey.persistence;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.AdminAccount;
import edu.ycp.cs320.coursesurvey.model.Institution;

public class FakeDatabase  implements IDatabase{
	
	private List<Institution> institutionList;
	private List<AdminAccount> adminList;
	
	public FakeDatabase() {
		institutionList = new ArrayList<Institution>();
		adminList = new ArrayList <AdminAccount> ();
	
	}
	
	public AdminAccount findAdminAccountByAdminName (String accountName) {
		for (AdminAccount admin : adminList) {
			if (admin.getAccountName().equals(accountName)) {
				return admin;
			}
		}
		return null;
	}

	public Institution findInstitution (String instName) {
		for (Institution institution : institutionList) {
			if (institution.getName().equals(instName)) {
				return institution;
			}
		}
		return null;
	}
}
