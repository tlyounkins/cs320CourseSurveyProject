package edu.ycp.cs320.coursesurvey.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.coursesurvey.model.AdminAccount;
import edu.ycp.cs320.coursesurvey.model.Institution;

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
					}
					else {
						throw e;
					}
				}
			}
			if (!success) {
				throw new SQLException("Transaction failed after too many retries");
			}

			//Success!
			return result;
		}
		finally {
			DBUtil.closeQuietly(conn);
		}
	}
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jbdc:sqlite:test.db");

		//Set autocommit to false t
		conn.setAutoCommit(false);
		return conn;
	}



	@Override
	public Institution findInstitution(String instName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addAdmin(String adminName, String password, int instId) {
		// TODO Auto-generated method stub

	}

	@Override
	public int addInstitution(String instName) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AdminAccount findAdminAccountByAdminName (final String accountName) {
		return executeTransaction(new Transaction<AdminAccount>() {
			@Override
			public AdminAccount execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;

				try {
					stmt = conn.prepareStatement ( 
							"select adminId.*, adminName.* " +
									" from admin_account " +
									" where admin_account.adminName = " + accountName
							);
					stmt.setString(1, accountName);

					AdminAccount result  = new AdminAccount();
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

	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;

				try {
					stmt1 = conn.prepareStatement(
							"create table admin_acount (" +
									"    id integer primary key," +
									"    name varchar(20)," +
									"    password varchar(20)" +
									"    inst_id integer," +
							")");
					stmt1.executeUpdate();

					stmt2 = conn.prepareStatement(
							"create table institution (" +
									"    id integer primary key," +
									"    name varchar(40)," +
							")");
					stmt2.executeUpdate();

					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}


	private void loadAdminAccount(AdminAccount admin, ResultSet resultSet, int index) throws SQLException {
		admin.setAdminId(resultSet.getInt(index++));
		admin.setAccountName(resultSet.getString(index++));
		admin.setPassword(resultSet.getString(index++));
	}
	public <ResultType> ResultType executeTransaction (Transaction<ResultType> txn) {
		try {
			return doExecuteTransation(txn);
		}
		catch (SQLException e) {
			throw new PersistenceException ("Tranaction failed", e);
		}
	}
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<AdminAccount> adminList;
				List<Institution> instList;

				try {
					adminList = InitialData.getAdminAccounts();
					instList = InitialData.getInstitutions();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAdminAccount = null;
				PreparedStatement insertInstitution = null;

				try {
					insertAdminAccount = conn.prepareStatement("insert into AdminAccounts values (?, ?, ?)");
					for (AdminAccount adminItr : adminList) {
						insertAdminAccount.setInt(1, adminItr.getAdminId());
						insertAdminAccount.setString(2, adminItr.getAccountName());
						insertAdminAccount.setString(3, adminItr.getPassword());
						insertAdminAccount.setInt(4,  adminItr.getInstId());
						insertAdminAccount.addBatch();
					}
					insertAdminAccount.executeBatch();

					insertInstitution = conn.prepareStatement("insert into Institutions values (?, ?, ?, ?)");
					for (Institution instItr : instList) {
						insertInstitution.setInt(1, instItr.getInstId());
						insertInstitution.setString(3,instItr.getName());
						insertInstitution.addBatch();
					}
					insertInstitution.executeBatch();

					return true;
				} finally {
					DBUtil.closeQuietly(insertInstitution);
					DBUtil.closeQuietly(insertAdminAccount);
				}
			}
		});
	}

	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		SqliteDatabase db = new SqliteDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Success!");
	}
		

}
