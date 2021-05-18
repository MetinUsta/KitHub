package userInterface;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database {
	private static String dbUrl;
	private static Connection connection;
	private static Statement statement;
	private static PreparedStatement prepStatement;

	public static void main(String[] args) {
		connectToDatabase("src/databases/libraryManagement.db");
		createTables();

		System.out.println("Successful");
		closeDatabase();
	}

	public static String getSqlQuery(String queryPath) {
		try {
			File file = new File(queryPath);
			Scanner scan = new Scanner(file);
			StringBuilder builder = new StringBuilder();

			while (scan.hasNextLine()) {
				builder.append(scan.nextLine() + "\n");
			}
			scan.close();
			return builder.toString();
		} catch (FileNotFoundException e) {
			System.out.println("Could not open the query file.");
			e.printStackTrace();
		}
		return null;
	}

	public static void closeDatabase() {
		try {
			if (connection != null) {
				connection.close();
			} else if (statement != null) {
				statement.close();
			} else if (prepStatement != null) {
				prepStatement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void connectToDatabase(String dbPath) {
		dbUrl = "jdbc:sqlite:" + dbPath;

		try {
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection(dbUrl);
			statement = connection.createStatement();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
	}

	public static void createTables() {
		try {

			String sql = getSqlQuery("src/databases/createTables.sql");

			statement = connection.createStatement();
			for (String s : sql.split(";")) {
				if (!s.equals("\n")) {
					statement.execute(s + ";");
				}
			}
		} catch (SQLException e) {
			System.out.println("error: Could not create the tables.");
			e.printStackTrace();
		}
	}
}
