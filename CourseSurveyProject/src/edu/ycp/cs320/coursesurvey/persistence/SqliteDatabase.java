package edu.ycp.cs320.coursesurvey.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import edu.ycp.cs320.coursesurvey.model.AdminAccount;
import edu.ycp.cs320.coursesurvey.model.Institution;

public class SqliteDatabase implements IDatabase{

	static {
		try {
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
	public AdminAccount findAdminAccountByAdminName (String accountName) {
		return executeTransaction (new Transaction<ResultType>()) {

		}
		
		
		public <ResultType> ResultType executeTransaction (Transaction<ResultType> txn) {
			try {
				return doExecuteTransation(txn);
			}
			catch (SQLException e) {
				throw new PersistenceException ("Tranaction failed", e);
			}
		}

}
