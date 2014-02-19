package beagle.benckmark.infrastucture;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SimpleBenchmark implements Benchmark {
	
	private final static int TXNS = 1000;
	
	private Connection connect = null;
	private Statement statement = null;
	private ResultSet resultSet = null;

	public void runBenchmark() {
		System.out.println("Running Simple Benchmark");

		// This will load the MySQL driver, each DB has its own driver
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception ex) {
			System.err.println("Loading MySQL-Driver failed!");
		}

		// Setup the connection with the DB
		try {
			connect = DriverManager
					.getConnection("jdbc:mysql://localhost/beagle?user=admin&password=1qaz2wsx!@#");
			connect.setAutoCommit(true);

		} catch (SQLException ex) {
			if (connect != null) {
				try {
					connect.close();
				} catch (Exception iDontCare) {
				} finally {
					connect = null;
				}
			}
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
			System.exit(1);
		}

		Runtime runtime = Runtime.getRuntime();

		try {
			long startTime, endTime;
			long startTotalMem;

			// Statements allow to issue SQL queries to the database
			statement = connect.createStatement();
			statement.setPoolable(true);

			// Result set get the result of the SQL query
			startTotalMem = runtime.totalMemory() - runtime.freeMemory();
			startTime = System.currentTimeMillis();

			for (int i = 0; i < TXNS; i++) {
				resultSet = statement
						.executeQuery("SELECT * FROM beagle.contracts");
			}

			// writeResultSet(resultSet);
			endTime = System.currentTimeMillis();
			printResult(
					"SimpleBenchmark",
					(endTime - startTime),
					(runtime.totalMemory() - runtime.freeMemory() - startTotalMem),
					500000);
		} catch (Exception e) {
			System.err.println(e);
		} finally {
			close();
		}

	}

	private static void printResult(String name, long ms, long bytes, int i) {
		System.out.println(name + ": " + (ms / 1000.0) + "s   \t "
				+ Math.round(1000.0 * i / ms) + " queries/second   \t");
	}

	// private void writeResultSet(ResultSet resultSet) throws SQLException {
	// // ResultSet is initially before the first data set
	// while (resultSet.next()) {
	// String name = resultSet.getString("name");
	// String owner = resultSet.getString("owner");
	// Date created_date = resultSet.getTimestamp("created_date");
	// System.out.println("Name: " + name);
	// System.out.println("Owner: " + owner);
	// System.out.println("Created Date: " + created_date);
	// System.out.println("----------------------------------");
	// }
	// }

	// You need to close the resultSet
	private void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}

			if (statement != null) {
				statement.close();
			}

			if (connect != null) {
				connect.close();
			}
		} catch (Exception e) {

		}
	}
}
