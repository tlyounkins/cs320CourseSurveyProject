package edu.ycp.cs320.coursesurvey.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.User;
import edu.ycp.cs320.coursesurvey.model.Course;
import edu.ycp.cs320.coursesurvey.model.Institution;
import edu.ycp.cs320.coursesurvey.model.Section;

public class SqliteDatabase implements IDatabase{
	static {
		try {
			System.out.println("database running");
			Class.forName("org.sqlite.JDBC");
		}
		catch (Exception e) {
			throw new IllegalStateException("Could not load sqlite driver");
		}
	}

	private interface Transaction<ResultType> {
		public ResultType execute (Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;

	
	public<ResultType> ResultType doExecuteTransation (Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();

		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;

			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute (conn);
					conn.commit();
					success = true;
				}
				catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("4100")) {
						//DeadLock: retry
						numAttempts++;
					} else {
						throw e;
					}
				}
			}
			if (!success) {
				throw new SQLException("Transaction failed after too many retries");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	private Connection connect() throws SQLException {
		String homeDir = System.getProperty("user.home");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:" + homeDir + "/sexycoursesurvey.db");

			//Set autocommit to false to allow multiple the execution of
			// multiple queries/statements as part of the same transaction.
			conn.setAutoCommit(false);
			
			return conn;
	}

	@Override
	public Institution findInstitution(String instName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addUser(String userName, String password, int instId, boolean student, boolean prof, boolean admin){

		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int addInstitution(String instName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	/*
	public User findUserAccountByName (final String accountName, int instID) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement ( 
							"select adminId.*, adminName.* " +
									" from admin_account " +
									" where admin_account.adminName = " + accountName
							);
					stmt.setString(1, accountName);

					User result  = new User();
					resultSet = stmt.executeQuery();

					if (resultSet.next()) {
						loadAdminAccount(result, resultSet, 1);
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(stmt);
					DBUtil.closeQuietly(resultSet);
				}


			}
		});
	}
	*/

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {

					stmt1 = conn.prepareStatement(
							"create table institution_table1 (" +
									"    id integer primary key," +
									"    name varchar(40)" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createInstitutionTable() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table institution (" +
									"    id integer primary key," +
									"    name varchar(40)," +
									"    courseTableID integer primary key," +
									"    userTableID integer primary key" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createUserTable(final int userTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table course_" + userTableID +  " (" +
									"    id integer primary key," +
									"    name varchar(40)," +
									"    courseTableID integer primary key," +
									"    userTableID integer primary key" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	/*
	private void loadAdminAccount(AdminAccount admin, ResultSet resultSet, int index) throws SQLException {
		admin.setAdminId(resultSet.getInt(index++));
		admin.setAccountName(resultSet.getString(index++));
		admin.setPassword(resultSet.getString(index++));
	}
	*/
	public <ResultType> ResultType executeTransaction (Transaction<ResultType> txn) {
		try {
			return doExecuteTransation(txn);
		}
		catch (SQLException e) {
			throw new PersistenceException ("Transaction failed", e);
		}
	}
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				//List<AdminAccount> adminList;
				List<Institution> instList;

				try {
					//adminList = InitialData.getAdminAccounts();
					instList = InitialData.getInstitutions();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAdminAccount = null;
				PreparedStatement insertInstitution = null;

				try {
					 // Creating an iterator for creating an admin account for loading the initial data
					/*
					insertAdminAccount = conn.prepareStatement("insert into admin_account1 values (?, ?, ?)");
					for (AdminAccount adminItr : adminList) {
						insertAdminAccount.setInt(1, adminItr.getAdminId());
						insertAdminAccount.setString(2, adminItr.getAccountName());
						insertAdminAccount.setString(3, adminItr.getPassword());
						insertAdminAccount.setInt(4,  adminItr.getInstId());
						insertAdminAccount.addBatch();
					}
					insertAdminAccount.executeBatch();
					*/
					insertInstitution = conn.prepareStatement("insert into institution1 values (?, ?, ?, ?)");
					for (Institution instItr : instList) {
						insertInstitution.setInt(1, instItr.getInstId());
						insertInstitution.setString(3,instItr.getName());
						insertInstitution.addBatch();
					}
					insertInstitution.executeBatch();

					return true;
				} finally {
					DBUtil.closeQuietly(insertInstitution);
					//DBUtil.closeQuietly(insertAdminAccount);
				}
			}
		});
	}

	// The main method creates the database tables and loads the initial data.
	//For testing only
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		SqliteDatabase db = new SqliteDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
	@Override
	public Course findCourse(String course) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Section findSection(String section) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addSurvey(String surveyName) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int addCourse(int instID, String title, String dept, int year,
			String term) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public User findUserAccountByName(String accountName, int instID) {
		// TODO Auto-generated method stub
		return null;
	}
}
