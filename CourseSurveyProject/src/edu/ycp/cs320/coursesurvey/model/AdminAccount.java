package edu.ycp.cs320.coursesurvey.model;
/**
 * Class that represents an Admin level user. AdminAccounts are associated with a Institution and can
 * generate new UserAccounts within that Institution.
 * 
 * @author Tyler Younkins, Garlan Bowser, Cory Dawson
 *
 */
public class AdminAccount {
 
	private String accountName, password;
	private int instId, adminId;
	
	
	/**
	 * Get the Admin account's name
	 * @return name
	 */
	
	public String getAccountName () {
		return accountName;
	}
	/**
	 * Set the Admin account's name.
	 * @param name
	 */
	
	public void setAccountName (String name) {
		this.accountName = name;
	}
	
	/**
	 * Get the Admin Account's Institution ID
	 */
	
	public int getInstId () {
		return instId;
	}

	/**
	 * Set the Admin Account's Institution ID
	 * @param instId
	 */
	
	public void setInstId(int instId) {
		this.instId = instId;
	}
	
	/**
	 * Get the Admin Account ID
	 */
	
	public int getAdminId () {
		return adminId;
	}
	
	/**
	 * Set the Admin Account ID
	 * @param adminId
	 */
	
	public void setAdminId (int adminId) {
		this.adminId = adminId;
	}
	
	
	/**
	 * Set the Admin account's password
	 * @param password
	 */
	public void setPassword (String password ) {
		this.password = password;
	}
	
	/**
	 * Get the Admin account's password
	 * @return password 
	 */
	public String getPassword () {
		return password;
	}
	
	
}