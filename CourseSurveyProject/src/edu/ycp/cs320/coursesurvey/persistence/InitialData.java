package edu.ycp.cs320.coursesurvey.persistence;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
				Institution inst = new Institution();
				inst.setInstId(Integer.parseInt(i.next()));
				inst.setName(i.next());
			}
			return instList;
		}
		finally {
			readInst.close();
		}
	}
}
