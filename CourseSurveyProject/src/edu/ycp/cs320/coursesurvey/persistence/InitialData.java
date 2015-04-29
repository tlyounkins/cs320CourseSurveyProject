package edu.ycp.cs320.coursesurvey.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.omg.PortableServer.POAPackage.AdapterAlreadyExists;

import edu.ycp.cs320.coursesurvey.model.Institution;

public class InitialData {
	
	public static List<Institution> getInstitutions() throws IOException {
		List<Institution> instList = new ArrayList<Institution>();
		ReadCSV readInst = new ReadCSV("institution.csv");
		try {
			while (true) {
				List<String> tuple = readInst.next();
				if (tuple == null ) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Institution inst = new Institution(null, 0, 0, 0);
				inst.setInstID(Integer.parseInt(i.next()));
				//inst.setName(i.next());
				// For testing:
				String test = i.next();
				System.out.println(test);
				inst.setName(test);
			}
			return instList;
		}
		finally {
			readInst.close();
		}
	}
/*
	public static List<AdminAccount> getAdminAccounts() throws IOException {
		List<AdminAccount> adminList = new ArrayList<AdminAccount>();
		ReadCSV readAdmin = new ReadCSV ("admin_account.csv");
		try {
			while (true) {
				List<String> tuple = readAdmin.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				AdminAccount admin = new AdminAccount();
				admin.setAdminId(Integer.parseInt(i.next()));
				admin.setAccountName(i.next());
				admin.setPassword(i.next());
				admin.setInstId(Integer.parseInt(i.next()));
			}
			return adminList;
		}
		finally {
			readAdmin.close();
		}
	}
*/
}
