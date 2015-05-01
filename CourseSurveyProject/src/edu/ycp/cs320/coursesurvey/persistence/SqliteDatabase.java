package edu.ycp.cs320.coursesurvey.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;







/*import edu.ycp.cs320.booksdb.model.Author;
import edu.ycp.cs320.booksdb.model.Book;
import edu.ycp.cs320.booksdb.persist.DBUtil;
import edu.ycp.cs320.booksdb.persist.InitialData;
import edu.ycp.cs320.booksdb.persist.SqliteDatabase.Transaction;*/
import edu.ycp.cs320.coursesurvey.model.Response;
import edu.ycp.cs320.coursesurvey.model.Survey;
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
	public int addInstitution(final String instName) {
		// TODO Auto-generated method stub
		final int newID = getNextInstID() + 1;
		createCourseTable(newID);
		createUserTable(newID);
		createSurveyTable(newID);
		
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {

				PreparedStatement insertInst = null;

				//for testing
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				
				
				try {
					insertInst = conn.prepareStatement("insert into institution values (?, ?, ?, ?, ?)");
					
						insertInst.setInt(1, newID);
						insertInst.setString(2, instName);
						insertInst.setInt(3, 0);
						insertInst.setInt(4, 0);
						insertInst.setInt(5, 0);
						insertInst.addBatch();
						
					insertInst.executeBatch();
					
					stmt = conn.prepareStatement(
							"select institution.* " +
							"  from institution " +
							" where institution.instID = ? "
					);
					stmt.setInt(1, newID);
					
					resultSet = stmt.executeQuery();
					
					
					
					System.out.println("Inst added is: " + resultSet.getInt(1) + resultSet.getString(2) + resultSet.getInt(3) + resultSet.getInt(4) + resultSet.getInt(5));
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertInst);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				
				
			}
		});
		return newID;
	}
	

	//@Override
	public int getNextInstID(){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select max(instID) as max from institution"   //should obtain the largest institution ID
					);
					
					int result;
					
					resultSet = stmt.executeQuery();
					result = resultSet.getInt(1);
					
					System.out.println("The max val is:" + result);
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	@Override
	public void submitResponse(int instID, int surveyID, ArrayList<Response> responses){
		//TODO
	}
	
	
	@Override
	public void addToTemplate(int instID, int surveyID, int questionType, String question, String options[]){
		
	}
	
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
	@Override
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {

					stmt1 = conn.prepareStatement(
							"create table institution (" +
									"    instId integer primary key," +
									"    name varchar(40)," +
									"    numUsers integer," +
									"    numCourses integer," +
									"    numSurveys integer" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	
	//various functions for creating tables
	public void createInstitutionTable() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table institution (" +
									"    instId integer primary key," +
									"    name varchar(40)," +
									"    numUsers integer," +
									"    numCourses integer," +
									"    numSurveys integer" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createUserTable(final int instTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table user_" + instTableID +  " (" +
									"    userID integer primary key," +
									"    userName varchar(40)," +
									"    userPassword varchar(16)," +
									"    instTableID integer," +
									"    student BOOLEAN," +
									"    proffesor BOOLEAN," +
									"    admin BOOLEAN" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createCourseTable(final int courseTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table course_" + courseTableID +  " (" +
									"    courseID integer primary key," +
									"    courseTitle varchar(60)," +
									"    dept varchar(40)," +
									"    schoolYear integer," +
									"    sectionTableID integer," +
									"    term varchar(40)" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createSectionTable(final int sectionTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table section_" + sectionTableID +  " (" +
									"    sectID integer primary key," +
									"    userID integer," +
									"    student BOOLEAN," +
									"    proffesor BOOLEAN" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createSurveyTable(final int surveyTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table survey_" + surveyTableID +  " (" +
									"    courseID integer," +
									"    creatorID integer," +
									"    surveyName varchar(40)," +
									"    surveyID integer primary key" + 
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createTemplateTable(final int templateTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table template_" + templateTableID +  " (" +
									"    questionNum integer primary key," +
									"    questionType integer primary key," +
									"    question varchar(255)," +
									"    option0 varchar(255)," +
									"    option1 varchar(255)," +
									"    option2 varchar(255)," +
									"    option3 varchar(255)," +
									"    option4 varchar(255)" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createResponseIndexTable(final int responseIndexTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table responseIndex_" + responseIndexTableID +  " (" +
									"    responseID integer primary key," +
									"    responseNum integer primary key" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}
	
	public void createResponseTable(final int responseTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				
				try {
					stmt1 = conn.prepareStatement(
							"create table response_" + responseTableID +  " (" +
									"    questionNum integer primary key," +
									"    answer varchar(255)" +
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
					insertInstitution = conn.prepareStatement("insert into institution values (?, ?, ?, ?)");
					for (Institution instItr : instList) {
						insertInstitution.setInt(1, instItr.getInstID());
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
	public Course findCourseByName(String course, int instID) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Section findSection(String section) {
		// TODO Auto-generated method stub
		return null;
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
	@Override
	public void addToSectionTable(int instID, int courseID, int sectID,
			int userID, boolean student, boolean prof) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int addSurvey(int instID, int courseID, int creatorID,
			String surveyName) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public Boolean clearDB(){
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"drop table institution"   
					);
					
					
					stmt.executeQuery();
					
					
					System.out.println("Database is cleared");
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	@Override
	public Survey findSurveyByID(int instID, int surveyID) {
		// TODO Auto-generated method stub
		return null;
	}
}
