package edu.ycp.cs320.coursesurvey.persistence;

import java.awt.Cursor;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.Response;
import edu.ycp.cs320.coursesurvey.model.Survey;
import edu.ycp.cs320.coursesurvey.model.Template;
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
	public Institution findInstitution(final String instName) {

		return executeTransaction(new Transaction <Institution>() {
			@Override
			public Institution execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"SELECT *" +
									"  FROM institution " +
									"  WHERE institution.name = ?" 
							);

					stmt.setString(1, instName);

					Institution result = new Institution ();

					resultSet =  stmt.executeQuery();
					if (resultSet.next()) {
						System.out.println("resultSet has next");
						result.setInstID(resultSet.getInt(1));
						result.setName(resultSet.getString(2));
						System.out.println("returned inst name is " + result.getName() );
					}
					else {
						System.out.println("Didn't find inst with instName");
						return null;
					}
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	@Override
	public Institution findInstitutionByID(final int instID) {

		return executeTransaction(new Transaction <Institution>() {
			@Override
			public Institution execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"SELECT *" +
									"  FROM institution " +
									"  WHERE institution.instId = ?" 
							);

					stmt.setInt(1, instID);

					Institution result = new Institution ();

					resultSet =  stmt.executeQuery();
					if (resultSet.next()) {
						System.out.println("resultSet has next");
						result.setInstID(resultSet.getInt(1));
						result.setName(resultSet.getString(2));
						System.out.println("returned inst name is " + result.getName() );
					}
					else {
						System.out.println("Didn't find inst with instID");
						return null;
					}
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public int addUser(final String userName, final String password, final int instID, final boolean student, final boolean prof, final boolean admin){
		final int newID = getNumUsers(instID) + 1;
		setNumUsers(instID, newID);

		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {

				PreparedStatement insert = null;

				//for testing
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					insert = conn.prepareStatement("insert into user_"+ instID +" values (?, ?, ?, ?, ?, ?, ?)");
					//creates reference to correct course Table

					//set values for new course
					insert.setInt(1, newID);
					insert.setString(2, userName);
					insert.setString(3, password);
					insert.setInt(4, instID);
					insert.setBoolean(5, student);
					insert.setBoolean(6, prof);
					insert.setBoolean(7, admin);
					insert.addBatch();

					insert.executeBatch();

					stmt = conn.prepareStatement(
							"select user_"+ instID +".*" +
									"  from user_"+ instID +
									" where user_"+ instID +".userID" +" = ? "
							);
					stmt.setInt(1, newID);

					resultSet = stmt.executeQuery();

					System.out.println("User added is: " +" "+ resultSet.getInt(1) +" "+ resultSet.getString(2) +" "+ resultSet.getInt(3) +" "+ resultSet.getInt(4) +" "+ resultSet.getInt(5)+" "+ resultSet.getInt(6));

					return true;
				} finally {
					DBUtil.closeQuietly(insert);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}	
			}
		});
		return newID;
	}

	@Override
	public int addInstitution(final String instName) {
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
					System.out.println("trying getnextID");
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

	//works by taking all the responses for one survey from one user to ensure they are matched and submits them
	@Override
	public void submitResponse(final int instID, final int surveyID, ArrayList<Response> responses){
		final int newID = 1;
		///createResponseTable(instID, surveyID, newID);


		for (int i = 0; i < responses.size(); i++){
			final int x = i;
			final int questionNum = responses.get(i).getQuestionNum();
			final String answer = responses.get(i).getAnswer();

			executeTransaction(new Transaction<Integer>() {
				@Override
				public Integer execute(Connection conn) throws SQLException {

					PreparedStatement insert = null;
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try {
						insert = conn.prepareStatement("insert into response_"+ instID + "_" + surveyID + "_" + newID + " values (?, ?)");
						//creates reference to correct course Table

						//set values for new course
						insert.setInt(1, questionNum);
						insert.setString(2, answer);
						insert.addBatch();
						insert.executeBatch();

						stmt = conn.prepareStatement(
								"select response_"+ instID + "_" + surveyID + "_" + newID + ".* " +
										"from response_"+ instID + "_" + surveyID + "_" + newID +
										" where response_" + instID + "_" + surveyID + "_" + newID +".questionNum" +" = ? "
								);
						stmt.setInt(1, x);

						resultSet = stmt.executeQuery();

						System.out.println("Response submited is: " +" "+ resultSet.getInt(1) +" "+ resultSet.getString(2));

						return newID;
					} finally {
						DBUtil.closeQuietly(insert);
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}	
				}
			});

		}
	}

	public int addToResponseIndex(final int instID, final int surveyID){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				PreparedStatement insert = null;

				try {
					stmt = conn.prepareStatement(
							"select max(responseID) as max from responseIndex_" + instID + "_" + surveyID   //should obtain the largest institution ID
							);

					int result;

					resultSet = stmt.executeQuery();
					result = resultSet.getInt(1) + 1;

					insert = conn.prepareStatement("insert into responseIndex_"+ instID + "_" + surveyID +" values (?)");
					//creates reference to correct course Table

					//set values for new course
					insert.setInt(1, result);
					insert.addBatch();
					insert.executeBatch();

					System.out.println("The max val is:" + result);

					return result;
				} finally {
					DBUtil.closeQuietly(insert);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}


	@Override
	public void addToTemplate(final int instID, final int surveyID, final int questionType, final String question){
		final int newID = getNumQuestions(instID, surveyID) + 1;
		System.out.println("new question ID is " + newID);
		executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {

				PreparedStatement insert = null;

				//for testing
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					insert = conn.prepareStatement("insert into template_"+ instID + "_" + surveyID +" values (?, ?, ?)");
					//creates reference to correct course Table

					//set values for new survey
					insert.setInt(1, newID);
					insert.setInt(2, questionType);
					insert.setString(3, question);
					insert.addBatch();
					insert.executeBatch();

					stmt = conn.prepareStatement(
							"select template_"+ instID + "_" + surveyID +".*" +
									"from template_"+ instID + "_" + surveyID +
									" where template_" + instID + "_" + surveyID +".questionNum" +" = ? "
							);
					stmt.setInt(1, newID);

					resultSet = stmt.executeQuery();

					System.out.println("Question added is: " +" "+ resultSet.getInt(1) +" "+ resultSet.getInt(2) +" "+ resultSet.getString(3));

					return newID;
				} finally {
					DBUtil.closeQuietly(insert);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}	
			}
		});

	}

	public int getNumQuestions(final int instID, final int surveyID){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"select max(questionNum) as max from template_" + instID +"_"+ surveyID   //should obtain the largest question number
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
									"    professor BOOLEAN," +
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

	public void createSectionTable(final int instID, final int sectionTableID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table section_" + instID + "_" + sectionTableID +  " (" +
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

	public void createTemplateTable(final int instID, final int surveyID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table template_" + instID + "_" + surveyID +  " (" +
									"    questionNum integer primary key," +
									"    questionType integer," +
									"    question varchar(255)" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	public void createResponseIndexTable(final int instID, final int surveyID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table responseIndex_" + instID + "_" + surveyID + " (" +
									"    responseID integer primary key" +
							")");
					stmt1.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
				}
			}
		});
	}

	public void createResponseTable(final int instID, final int surveyID, final int responseID) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table response_" + instID + "_" + surveyID + "_" + responseID +  " (" +
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

	@Override
	public Course findCourseByName(final String course, final int instID) {
		return executeTransaction(new Transaction <Course>() {
			@Override
			public Course execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"SELECT *" +
									"  FROM course_" + instID + 
									"  WHERE course_" + instID +".courseTitle = ?" 
							);
					stmt.setString(1, course);

					Course result = new Course ();

					resultSet = stmt.executeQuery();
					int index = 1;
					if (resultSet.next()) {
						result.setCourseID(resultSet.getInt(index++));
						result.setCourseTitle(resultSet.getString(index++));
						result.setDept(resultSet.getString(index++));
						result.setSchoolYear(resultSet.getInt(index++));
						resultSet.getInt(index++);
						result.setTerm(resultSet.getString(index++));

					}
					else {
						return null;
					}
					return result;

				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});

	}


	@Override
	public int addCourse(final int instID, final String title, final String dept, final int year, final String term){
		final int newID = getNumCourses(instID) + 1;
		setNumCourses(instID, newID);
		createSectionTable(instID, newID);

		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {

				PreparedStatement insert = null;

				//for testing
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					insert = conn.prepareStatement("insert into course_"+ instID +" values (?, ?, ?, ?, ?, ?)");
					//creates reference to correct course Table


					//set values for new course
					insert.setInt(1, newID);
					insert.setString(2, title);
					insert.setString(3, dept);
					insert.setInt(4, year);
					insert.setInt(5, 101);
					insert.setString(6, term);
					insert.addBatch();

					insert.executeBatch();

					stmt = conn.prepareStatement(
							"select course_"+ instID +".*" +
									"  from course_"+ instID +
									" where  course_"+ instID +".courseID" +" = ? "
							);
					stmt.setInt(1, newID);

					resultSet = stmt.executeQuery();

					System.out.println("Course added is: " +" "+ resultSet.getInt(1) +" "+ resultSet.getString(2) +" "+ resultSet.getString(3) +" "+ resultSet.getInt(4) +" "+ resultSet.getInt(5)+" "+ resultSet.getString(6));

					return true;
				} finally {
					DBUtil.closeQuietly(insert);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}	
			}
		});
		return newID;
	}

	public int getNumCourses(final int instID){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"select institution.* from institution where institution.instId = ?"   //should obtain current number of courses
							);

					stmt.setInt(1, instID);
					int result;

					resultSet = stmt.executeQuery();
					result = resultSet.getInt(4);

					System.out.println("The number of courses is:" + result);

					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public Boolean setNumCourses(final int instID, final int numCourses){
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;

				try {
					stmt = conn.prepareStatement(
							"update institution " + "set numCourses = ? " + "where instId = ?"   //should obtain current number of courses
							);

					stmt.setInt(1, numCourses);
					stmt.setInt(2, instID);

					stmt.executeUpdate();

					System.out.println("The number of courses has been updated");

					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public int getNumUsers(final int instID){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"select institution.* from institution where institution.instId = ?"   //should obtain current number of users
							);

					stmt.setInt(1, instID);
					int result;

					resultSet = stmt.executeQuery();
					result = resultSet.getInt(3);

					System.out.println("The number of users is:" + result);

					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public Boolean setNumUsers(final int instID, final int numUsers){
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;

				try {
					stmt = conn.prepareStatement(
							"update institution set numUsers = ? where institution.instId = ?"  
							);

					stmt.setInt(1, numUsers);
					stmt.setInt(2, instID);

					stmt.executeUpdate();

					System.out.println("The number of courses has been updated");

					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public Boolean setNumSurveys(final int instID, final int numSurveys){
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;

				try {
					stmt = conn.prepareStatement(
							"update institution set numSurveys = ? where institution.instId = ?"   //should obtain current number of courses
							);

					stmt.setInt(1, numSurveys);
					stmt.setInt(2, instID);

					stmt.executeUpdate();

					System.out.println("The number of surveys has been updated to " + numSurveys);

					return true;
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}

	public int getNumSurveys(final int instID){
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"select institution.* from institution where institution.instId = ?"   //should obtain current number of users
							);

					stmt.setInt(1, instID);
					int result;

					resultSet = stmt.executeQuery();
					result = resultSet.getInt(5);

					System.out.println("The number of surveys is:" + result);

					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}



	@Override
	public User findUserAccountByName(final String accountName, final int instID) {

		return executeTransaction(new Transaction <User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"SELECT *" +
									"  FROM user_" + instID + 
									"  WHERE user_" + instID +".userName = ?" 
							);
					stmt.setString(1, accountName);

					User result = new User ();

					resultSet = stmt.executeQuery();
					int index = 1;
					if (resultSet.next()) {
						result.setInstID(resultSet.getInt(index++));
						result.setUserName(resultSet.getString(index++));
						result.setPassword(resultSet.getString(index++));
						result.setInstID(resultSet.getInt(index++));
						result.setStudent(resultSet.getBoolean(index++));
						result.setProf(resultSet.getBoolean(index++));
						result.setAdmin(resultSet.getBoolean(index++));
					}
					else {
						return null;
					}
					return result;

				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});

	}

/*	@Override
	public void addToSectionTable(final int instID, final int courseID, final int sectID,
			final int userID, final boolean student, final boolean prof) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {

				PreparedStatement insert = null;

				//for testing
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					insert = conn.prepareStatement("insert into section_"+ instID +"_"+ courseID +" values (?, ?, ?, ?)");
					//creates reference to correct course Table

					//set values for new course
					insert.setInt(1, sectID);
					insert.setInt(2, userID);
					insert.setBoolean(3, student);
					insert.setBoolean(4, prof);

					insert.executeUpdate();

					stmt = conn.prepareStatement(
							"select section_"+ instID +"_"+ courseID +".*" +
									" from section_"+ instID +"_"+ courseID +
									" where  section_"+ instID +"_"+ courseID + ".userID" +" = ? "
							);

					stmt.setInt(1, userID);

					resultSet = stmt.executeQuery();

					System.out.println("User added to section_"+ instID +"_"+ courseID + " is: " + resultSet.getInt(1) +" "+ resultSet.getInt(2) +" "+ resultSet.getBoolean(3) +" "+ resultSet.getBoolean(4));

					return true;
				} finally {
					DBUtil.closeQuietly(insert);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}	
			}
		});
	}
*/
	@Override
	public int addSurvey(final int instID, final int courseID, final int creatorID, final String surveyName){
		final int newID = getNumSurveys(instID) + 1;
		setNumSurveys(instID, newID);

		createTemplateTable(instID, newID);
		createResponseIndexTable(instID, newID);

		executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {

				PreparedStatement insert = null;

				//for testing
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					insert = conn.prepareStatement("insert into survey_"+ instID +" values (?, ?, ?, ?)");
					//creates reference to correct course Table

					//set values for new course
					insert.setInt(1, courseID);
					insert.setInt(2, creatorID);
					insert.setString(3, surveyName);
					insert.setInt(4, newID);

					insert.executeUpdate();

					stmt = conn.prepareStatement(
							"select survey_"+ instID +".*" +
									" from survey_"+ instID +
									" where  survey_"+ instID +".surveyID" +" = ? "
							);
					stmt.setInt(1, newID);

					resultSet = stmt.executeQuery();

					System.out.println("Survey added is: " + resultSet.getInt(1) +" "+ resultSet.getInt(2) +" "+ resultSet.getString(3) +" "+ resultSet.getInt(4));

					return newID;
				} finally {
					DBUtil.closeQuietly(insert);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}	
			}
		});
		return newID;
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
	public List <Template> findSurveyQuesitons (final int instID) {
		return executeTransaction(new Transaction <List<Template>>() {
			@Override
			public List <Template> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				final int surveyID = getNumSurveys(instID);
				try {
					stmt = conn.prepareStatement(
							"SELECT  *" +
									"  FROM template_" + instID + "_" + surveyID +
									"  WHERE template_" + instID + "_" + surveyID +".questionNum < ?" 
							);

					int maxID = getNumQuestions(instID, surveyID) + 1;
					System.out.println("max id is " + maxID);
					stmt.setInt(1, maxID);

					List <Template> result = new ArrayList<Template> ();

					resultSet =  stmt.executeQuery();

					while (resultSet.next()) {
						int index = 1;
						System.out.println("resultSet has next");
						Template template = new Template();
						template.setQuestionNum(resultSet.getInt(index++));
						System.out.println("QuestionNUM is" + template.getQuestionNum() + "current index after add is " + index);
						template.setQuestionType(resultSet.getInt(index++));
						System.out.println("QuestionType is" + template.getQuestionType() + "current index after add is " + index);
						template.setQuestion(resultSet.getString(index++));
						System.out.println("Question is" + template.getQuestion() + "current index after add is " + index);

						result.add(template);
					}
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});


	}


	@Override
	public Survey findSurveyByID(final int instID, final int surveyID) {
		return executeTransaction(new Transaction <Survey>() {
			@Override
			public Survey execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement(
							"SELECT *" +
									"  FROM survey_" + instID +
									"  WHERE survey_" + instID +".surveyID = ?" 
							);

					stmt.setInt(1, surveyID);

					Survey result = new Survey ();

					resultSet =  stmt.executeQuery();
					int index = 1;

					if (resultSet.next()) {
						System.out.println("resultSet has next");
						result.setCourseID(resultSet.getInt(index++));
						result.setCreatorID(resultSet.getInt(index++));
						result.setSurveyName(resultSet.getString(index++));
						result.setSurveyID(resultSet.getInt(index++));
						System.out.println("returned survey name is " + result.getSurveyName() );
					}
					else {
						System.out.println("Didn't find Survey with that name");
						return null;
					}
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});


	}
}
