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

	public int addInstitution ( String instName) {
		institutionList.add(new Institution());
		int index = institutionList.size()-1;
		int instId = institutionList.size();

		institutionList.get(index).setName(instName);
		institutionList.get(index).setId(instId);
		
		//TODO: Remove me later and create junit tests for database
		System.out.println("Institution Name is " + institutionList.get(index).getName());
		System.out.println("institution id is " + institutionList.get(index).getInstId(instId));
		
		//returns the id to be stored if needed (look at account creation controller for an ex.)
		return instId;
	}

	public void addAdmin (String adminName, String password, int instId) {
		adminList.add(new AdminAccount());

		int index = adminList.size()-1;
		int adminId = adminList.size();

		adminList.get(index).setAccountName(adminName);
		adminList.get(index).setPassword(password);
		adminList.get(index).setAdminId(adminId);
		
		// TODO: remove me later and create junit tests for database
		System.out.println("admin user name is " + adminList.get(index).getAccountName() );
		System.out.println("admin id is " + adminList.get(index).getAdminId());
		System.out.println("admin password is " + adminList.get(index).getPassword());


	}
}
