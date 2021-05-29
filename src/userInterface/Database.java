package userInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

@SuppressWarnings("serial")
class LateBookReturnException extends Exception {
	public LateBookReturnException(String errorMessage) {
		super(errorMessage);
	}
}

@SuppressWarnings("serial")
class CurrentlyLoanedException extends Exception {
	public CurrentlyLoanedException(String errorMessage) {
		super(errorMessage);
	}
}

public class Database {
	public final static int MAX_DAYS_TO_RETURN = 20;
	public final static int LATE_RETURN_PENALTY = 15;

	private static String dbPath = "src/databases/libraryManagement.db";
	private static String dbUrl = "jdbc:sqlite:" + dbPath;

	public static void main(String[] args) {
		createTables();
		fillTables();

		// CALL PYTHON SCRIPT FROM JAVA
		/*callPython("src/scripts/importBooks.py", "src/databases/importFiles/Books.csv",
				"src/databases/libraryManagement.db");*/

		//		System.out.println(isAdmin("123456789"));

		/*
		 * HashMap<String, Object> info;
		 * try {
		 * info = getUserInfo("test@mail.com",
		 * Security.getPasswordHash("12345"));
		 * if (info.get("Name") == null) {
		 * System.out.println("Boyle bir kullanici bulunmamaktadir.");
		 * } else {
		 * System.out.println((String) info.get("Name"));
		 * System.out.println((boolean) info.get("LateReturnStatus"));
		 * }
		 * 
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * HashMap<String, Object> info;
		 * try {
		 * info = getUserInfo(10);
		 * System.out.println((String) info.get("Name"));
		 * System.out.println((boolean) info.get("LateReturnStatus"));
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * String password = "1234*";
		 * try {
		 * addNewUser("Scott", "Rowlett",
		 * "scott_rowlett@tutanota.com", Security.getPasswordHash(password));
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<Integer> bookIds = getSearchedBooks("cruise");
		 * for (int bookId : bookIds) {
		 * System.out.println(bookId);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<String>genres = getUniqueGenres();
		 * for(String genre : genres) {
		 * System.out.println(genre);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<Integer> libraryIds = getLibrariesOfBook(20);
		 * for (int libraryId : libraryIds) {
		 * System.out.println(libraryId);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * HashMap<String, Object> info;
		 * try {
		 * info = getLibraryContactInfo(1);
		 * System.out.println((String) info.get("Name"));
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * h}
		 */

		/*
		 * HashMap<String, Integer> info;
		 * try {
		 * info = getLibraryShelfInfo(1);
		 * System.out.println(info.get("RowCount"));
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<Integer> bookIds =
		 * getBooksFromGenre("Detective and mystery stories");
		 * for (int bookId : bookIds) {
		 * System.out.println(bookId);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * Integer bookId;
		 * try {
		 * bookId = getBookFromIsbn("9780007119332");
		 * System.out.println(bookId);
		 * bookId = getBookFromIsbn("0000000000000");
		 * System.out.println(bookId);
		 * // null checks here
		 * if(bookId == null) {
		 * System.out.println("No book with that Isbn13 exists in the database.");
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * Integer bookId;
		 * try {
		 * bookId = getBookFromCopyId(3);
		 * System.out.println(bookId);
		 * bookId = getBookFromCopyId(29);
		 * System.out.println(bookId);
		 * // null checks here
		 * if (bookId == null) {
		 * System.out.println("No book with that bookCopyId exists in the database.");
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * HashMap<String, Object> info;
		 * try {
		 * info = getBookInfo(5);
		 * System.out.println((String) info.get("Title"));
		 * System.out.println((String) info.get("PublishDate"));
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		try {
			var comments = getCommentsOfBook(10);
			for (var comment : comments) {
				System.out.println(comment.get("UserId"));
				System.out.println(comment.get("Comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/

		/*
		 * try {
		 * LinkedList<String> genres = getGenresOfBook(7);
		 * for (String genre : genres) {
		 * System.out.println(genre);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * addNewComment(2, 5365, "It's okay, I guess.");
		 * } catch (SQLException e) {
		 * // Duplicate review handling here
		 * if(e.getErrorCode() == 19) {
		 * System.out.println("error: You cannot review the same book twice.");
		 * }
		 * e.printStackTrace();
		 * }
		 */

		/*
		try {
			System.out.println(isBookInStock(5));
			System.out.println(isBookInStock(10));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/

		/*
		try {
			System.out.println(isCurrentlyLoaned(5, 14));
			System.out.println(isCurrentlyLoaned(5, 9));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/

		/*
		Clock clock = Clock.systemUTC();
		
		try {
			userLoanBook(8, 68, 1, clock.instant().toString());
		} catch (SQLException | LateBookReturnException | CurrentlyLoanedException e) {
			e.printStackTrace();
		}
		*/

		/*
		// Demo code to show how to turn clock instant string into printable YYYY-MM-DD string
		Instant instant = Instant.parse("2021-05-22T10:22:04.912340600Z");
		LocalDate date = LocalDate.ofInstant(instant, clock.getZone());
		System.out.println(date.toString());
		*/

		/*
		 * try {
		 * System.out.println(isBookReturnedLate(5, 4,
		 * "2021-06-28T10:22:04.912340600Z"));
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * Clock clock = Clock.systemUTC();
		 * try {
		 * userReturnBook(5, 4, "2021-07-22T10:22:33.057493200Z");
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * } catch (LateBookReturnException e) {
		 * System.out.println(
		 * "You returned this book late. You cannot loan new books until you donate a book to one of the libraries."
		 * );
		 * }
		 */

		/*
		 * try {
		 * LinkedList<Integer>libraries = getLibraries();
		 * for(Integer library : libraries) {
		 * System.out.println(library);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * donateBook("TestTitle", "TestAuthor", "1965", 322, "9780006280934",
		 * "I'm Groot!", "Crime, Thriller", 3);
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<String>comments = getUserComments(1);
		 * for(String comment : comments) {
		 * System.out.println(comment);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<Integer> bookIds = getLoanedBooks(5);
		 * for (int bookId : bookIds) {
		 * System.out.println(bookId);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		/*
		 * try {
		 * LinkedList<Integer> bookIds = getReturnedBooks(15);
		 * for (int bookId : bookIds) {
		 * System.out.println(bookId);
		 * }
		 * } catch (SQLException e) {
		 * e.printStackTrace();
		 * }
		 */

		System.out.println("-Database.java main terminated succesfully-");
	}

	/**
	 * Gets command line arguments as varargs and calls the given python script with
	 * those arguments.
	 * Exit code and the stdout of the python script is also tied to the Java.
	 * 
	 * @param args command-line arguments of the python script (argv)
	 */
	public static void callPython(String scriptPath, String... args) {
		try {
			ArrayList<String> argList = new ArrayList<>();

			argList.add("python");
			argList.add(scriptPath);
			for (String arg : args) {
				argList.add(arg);
			}
			ProcessBuilder pb = new ProcessBuilder(argList);

			pb.redirectErrorStream(true);
			Process proc;
			proc = pb.start();
			int exitCode = proc.waitFor();
			if (exitCode != 0) {
				System.out.println("error: An error occured in the " + scriptPath + " script.");
			}

			Reader reader = new InputStreamReader(proc.getInputStream());
			BufferedReader bf = new BufferedReader(reader);
			String s;
			while ((s = bf.readLine()) != null) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reads sql query lines from a file and builds a sql string containing them.
	 * 
	 * @param queryPath absolute or relative path of the sql text file
	 * @return a ready to execute sql string
	 */
	public static String getSqlQuery(String queryPath) {
		File file = new File(queryPath);
		StringBuilder builder = new StringBuilder();

		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNextLine()) {
				builder.append(scan.nextLine() + "\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		return builder.toString();
	}

	/**
	 * Establishes connection with the database.
	 */
	public static Connection connectToDatabase() {
		Connection conn = null;
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection(dbUrl);
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		return conn;
	}

	/**
	 * Creates all of the needed database tables for the application.
	 * No operation if the tables already exist.
	 */
	public static void createTables() {
		try (Connection conn = connectToDatabase();
				Statement stmt = conn.createStatement()) {
			String sql = getSqlQuery("src/databases/createTables.sql");

			for (String s : sql.split(";")) {
				if (!s.equals("\n")) {
					stmt.execute(s + ";");
				}
			}
		} catch (SQLException e) {
			System.out.println("error: Could not create the tables.");
			e.printStackTrace();
		}
	}

	/**
	 * Fills all of the needed database tables for the application.
	 * No operation if the tables already exist.
	 */
	public static void fillTables() {
		try (Connection conn = connectToDatabase();
				Statement stmt = conn.createStatement()) {
			String sql = getSqlQuery("src/databases/fillTables.sql");

			for (String s : sql.split(";\n")) {
				if (!s.equals("\n")) {
					stmt.execute(s + ";");
				}
			}
		} catch (SQLException e) {
			System.out.println("error: Could not fill the tables.");
			e.printStackTrace();
		}
	}

	/**
	 * Checks if the given password belongs to an admin account.
	 */
	public static boolean isAdmin(String password) {
		String sql = "SELECT Password FROM Users WHERE Email = 'admin@sistemanalizi.com'";

		try (Connection conn = connectToDatabase();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			rs.next();
			String adminPassword = rs.getString("Password");
			return (adminPassword.equals(Security.getPasswordHash(password))) ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Finds the given user in the database and returns all of its columns.
	 * 
	 * @return HashMap containing the columns of the matching user or empty HashMap
	 *         if the user does not exist
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static HashMap<String, Object> getUserInfo(String email, String passwordHash) throws SQLException {
		String sql = "SELECT UserId, Name, Surname, Email, Password, LateReturnStatus FROM Users WHERE Email = ? AND Password = ?";
		HashMap<String, Object> info = new HashMap<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, email);
			pstmt.setString(2, passwordHash);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				info.put("UserId", rs.getInt("UserId"));
				info.put("Name", rs.getString("Name"));
				info.put("Surname", rs.getString("Surname"));
				info.put("Email", rs.getString("Email"));
				info.put("Password", rs.getString("Password"));
				info.put("LateReturnStatus", rs.getBoolean("LateReturnStatus"));
			}
			return info;
		}
	}

	/**
	 * Finds the given user in the database and returns all of its columns.
	 * 
	 * @return HashMap containing the columns of the matching usepr or empty HashMap
	 *         if the user does not exist
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static HashMap<String, Object> getUserInfo(int userId) throws SQLException {
		String sql = "SELECT UserId, Name, Surname, Email, Password, LateReturnStatus FROM Users WHERE UserId = ?";
		HashMap<String, Object> info = new HashMap<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				info.put("UserId", rs.getInt("UserId"));
				info.put("Name", rs.getString("Name"));
				info.put("Surname", rs.getString("Surname"));
				info.put("Email", rs.getString("Email"));
				info.put("Password", rs.getString("Password"));
				info.put("LateReturnStatus", rs.getBoolean("LateReturnStatus"));
			}
			return info;
		}
	}

	/**
	 * Adds a new user with the given properties to the Users table.
	 * 
	 * @throws SQLException
	 */
	public static void addNewUser(String name, String surname, String email, String passwordHash) throws SQLException {
		String sql = "INSERT INTO Users (Name, Surname, Email, Password)"
				+ "VALUES (?, ?, ?, ?)";

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, name);
			pstmt.setString(2, surname);
			pstmt.setString(3, email);
			pstmt.setString(4, passwordHash);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Checks the availability of the book copy in its library
	 * 
	 * @return true if the library has the book available in stock, otherwise
	 *         returns false
	 */
	private static boolean isBookInStock(int bookCopyId) throws SQLException {
		int loanCount = 0;
		int copyCount = 0;

		String sqlCopyCount = "SELECT CopyCount FROM BookCopies WHERE BookCopyId = ?";
		String sqlLoanCount = "SELECT COUNT(*) as LoanCount FROM BookLoans "
				+ "WHERE BookCopyId = ? AND IsReturned = 0 GROUP BY BookCopyId";

		try (Connection conn = connectToDatabase()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sqlCopyCount)) {
				pstmt.setInt(1, bookCopyId);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					copyCount = rs.getInt("CopyCount");
				}
			}
			try (PreparedStatement pstmt = conn.prepareStatement(sqlLoanCount)) {
				pstmt.setInt(1, bookCopyId);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					loanCount = rs.getInt("LoanCount");
				}
			}
			if (copyCount == 0) {
				return false;
			} else if (loanCount < copyCount) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Checks if the user currently loans a copy of the book.
	 * 
	 * @return true if the user has loaned a copy of this book and did not return
	 *         yet, otherwise returns false.
	 * @throws SQLException
	 */

	@SuppressWarnings("resource")
	private static boolean isCurrentlyLoaned(int userId, int bookCopyId) throws SQLException {
		String sql = "SELECT UserId FROM BookLoans WHERE UserId = ? AND BookCopyId = ? AND IsReturned = 0";

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			pstmt.setInt(2, bookCopyId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Checks BookLoans table and finds late returned book records of the user. If
	 * the needed time has passed, penalty is removed. Removes all of the served
	 * penalties of the user.
	 * 
	 * @return true if the user has served all of his/her penalties, otherwise
	 *         returns false
	 */
	@SuppressWarnings("resource")
	public static boolean serveUserPenalties(int userId) throws SQLException {
		boolean servedAll = true;
		String sqlGetPenalties = "SELECT BookCopyId, LoanDate FROM BookLoans"
				+ " WHERE UserId = ? AND IsReturned = -1";
		String sqlSetServed = "UPDATE BookLoans SET IsReturned = 1 WHERE UserId = ? AND BookCopyId = ?";
		String sqlChangeStatus = "UPDATE Users SET LateReturnStatus = 0 WHERE UserId = ?";
		Clock clock = Clock.systemUTC();
		LocalDate today = LocalDate.ofInstant(clock.instant(), clock.getZone());

		if ((boolean) getUserInfo(userId).get("LateReturnStatus") == false) {
			return true;
		}

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sqlGetPenalties);
				PreparedStatement pstmt2 = conn.prepareStatement(sqlSetServed)) {

			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String loanDateInstant = rs.getString("LoanDate");
				LocalDate loanDate = LocalDate.ofInstant(Instant.parse(loanDateInstant), clock.getZone());
				int bookCopyId = rs.getInt("BookCopyId");

				if (ChronoUnit.DAYS.between(loanDate, today) >= LATE_RETURN_PENALTY) {
					pstmt2.setInt(1, userId);
					pstmt2.setInt(2, bookCopyId);
					pstmt2.executeUpdate();
				} else {
					servedAll = false;
				}
			}
			if (servedAll) {
				try (PreparedStatement pstmt3 = conn.prepareStatement(sqlChangeStatus)) {
					pstmt3.setInt(1, userId);
					pstmt3.executeUpdate();
				}
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Registers the new book loan to the BookLoans table. You must provide a
	 * library that has this book in stock.
	 * This method does not include book stock check.
	 * 
	 * @throws SQLException
	 * @throws LateBookReturnException if the person has pending late return
	 *                                 penalties
	 * @throws CurrentlyLoanedException if the person currently loans that copy
	 */
	public static void userLoanBook(int userId, int bookId, int libraryId, String loanDateInstant)
			throws SQLException, LateBookReturnException, CurrentlyLoanedException {
		String sql = "INSERT INTO BookLoans (UserId, BookCopyId, LoanDate)"
				+ "VALUES (?, ?, ?)";
		Integer bookCopyId;

		try (Connection conn = connectToDatabase()) {
			bookCopyId = convertToCopyId(bookId, libraryId);
			if (bookCopyId == null) {
				throw new SQLException(
						"BookCopyId not found with the columns BookId = " + bookId + ", LibraryId = " + libraryId);
			}
			if (isCurrentlyLoaned(userId, bookCopyId)) {
				throw new CurrentlyLoanedException("user: " + userId
						+ " currently loans a copy with the bookCopyId = " + bookCopyId
						+ ". He/she cannot loan another copy until he returns the one he owns.");
			}
			if (serveUserPenalties(userId)) {
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, userId);
					pstmt.setInt(2, bookCopyId);
					pstmt.setString(3, loanDateInstant);
					pstmt.executeUpdate();
				}
			} else {
				throw new LateBookReturnException(
						"User: " + userId + " has pending late return penalties. He/she cannot loan new books.");
			}
		}
	}

	/**
	 * Check if the return time of the book exceeds the library limitations.
	 * 
	 * @return Flag true if user returned the book late, otherwise return false
	 * @throws SQLException
	 */

	@SuppressWarnings("resource")
	private static boolean isBookReturnedLate(int userId, int bookCopyId, String returnDateInstant)
			throws SQLException {
		Clock clock = Clock.systemUTC();
		LocalDate returnDate = LocalDate.ofInstant(Instant.parse(returnDateInstant), clock.getZone());
		LocalDate loanDate;
		String sql = "SELECT LoanDate FROM BookLoans WHERE UserId = ? AND BookCopyId = ?";

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setInt(2, bookCopyId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				loanDate = LocalDate.ofInstant(Instant.parse(rs.getString("LoanDate")), clock.getZone());
			} else {
				throw new SQLException();
			}
			if (ChronoUnit.DAYS.between(loanDate, returnDate) >= MAX_DAYS_TO_RETURN) {
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Returns given book to the library. If the user returned the book late
	 * according to the MAX_DAYS_TO_RETURN, user
	 * gets banned from loaning new books until he/she serves his penalty.
	 * 
	 * @throws SQLException            if any error occurs while executing SQL
	 *                                 queries
	 * @throws LateBookReturnException if the user returned the book late
	 */
	public static void userReturnBook(int userId, int bookCopyId, String returnDateInstant)
			throws SQLException, LateBookReturnException {
		String sql = "UPDATE BookLoans SET IsReturned = 1 WHERE UserId = ? AND BookCopyId = ? AND IsReturned = 0";
		String sqlSetLateReturn = "UPDATE Users SET LateReturnStatus = 1 WHERE UserId = ?";
		String sqlSetBookLoans = "UPDATE BookLoans SET LoanDate = ?, IsReturned = -1 WHERE UserId = ? AND BookCopyId = ?";

		try (Connection conn = connectToDatabase()) {
			if (isBookReturnedLate(userId, bookCopyId, returnDateInstant)) {
				try (PreparedStatement pstmt = conn.prepareStatement(sqlSetLateReturn)) {
					pstmt.setInt(1, userId);
					pstmt.executeUpdate();
				}
				try (PreparedStatement pstmt = conn.prepareStatement(sqlSetBookLoans)) {
					pstmt.setString(1, returnDateInstant);
					pstmt.setInt(2, userId);
					pstmt.setInt(3, bookCopyId);
					pstmt.executeUpdate();
				}
				throw new LateBookReturnException("user: " + userId + " returned the bookCopy: " + bookCopyId
						+ " late. He/she will receive a penalty.");
			} else {
				try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
					pstmt.setInt(1, userId);
					pstmt.setInt(2, bookCopyId);
					pstmt.executeUpdate();
				}
			}
		}
	}

	/**
	 * Searches BookLoans table and returns currently loaned books of the given user
	 * 
	 * @return a LinkedList containing BookCopyIds
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getLoanedBooks(int userId) throws SQLException {
		String sql = "SELECT BookCopyId FROM BookLoans INNER JOIN BookCopies USING(BookCopyId) WHERE UserId = ? AND IsReturned = 0";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.addLast(rs.getInt("BookCopyId"));
			}
			return books;
		}
	}

	/**
	 * Searches BookLoans table and returns previously returned books of the given
	 * user
	 * 
	 * @return a LinkedList containing BookIds
	 * @throws SQLException
	 * @authors Metin Usta
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getReturnedBooks(int userId) throws SQLException {
		String sql = "SELECT DISTINCT BookCopyId FROM BookLoans INNER JOIN BookCopies USING(BookCopyId) WHERE UserId = ? AND (IsReturned = 1 OR IsReturned = -1)";
		String sqlBookCopy = "SELECT BookId FROM BookCopies WHERE BookCopyId = ?";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql);
				PreparedStatement pstmt2 = conn.prepareStatement(sqlBookCopy)) {
			pstmt.setInt(1, userId);

			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookCopyId = rs.getInt("BookCopyId");
				ResultSet rs2;
				pstmt2.setInt(1, bookCopyId);

				rs2 = pstmt2.executeQuery();
				rs2.next();
				books.addLast(rs2.getInt("BookId"));
			}
			return books;
		}
	}

	/**
	 * Adds a new comment with the given properties to the Comments table.
	 * 
	 * @throws SQLException A user cannot comment twice to the same book
	 */
	public static void addNewComment(int userId, int bookId, String comment) throws SQLException {
		String sql = "INSERT INTO Comments (UserId, BookId, Comment)"
				+ "VALUES (?, ?, ?)";

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			pstmt.setInt(2, bookId);
			pstmt.setString(3, comment);
			pstmt.executeUpdate();
		}
	}

	/**
	 * Finds all of the comments of the user and returns them.
	 * 
	 * @return a LinkedList containing comments
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<String> getUserComments(int userId) throws SQLException {
		String sql = "SELECT Comment FROM Comments WHERE UserId = ?";
		LinkedList<String> comments = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				comments.addLast(rs.getString("Comment"));
			}
			return comments;
		}
	}

	/**
	 * Finds the given book in the database and returns all of its columns.
	 * 
	 * @return HashMap containing the columns of the matching book or empty HashMap
	 *         if the book does not exist
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static HashMap<String, Object> getBookInfo(int bookId) throws SQLException {
		String sql = "SELECT BookId, Title, Author, PublishDate, PageCount, Isbn13, Overview FROM Books WHERE BookId = ?";
		HashMap<String, Object> info = new HashMap<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				info.put("BookId", rs.getInt("BookId"));
				info.put("Title", rs.getString("Title"));
				info.put("Author", rs.getString("Author"));
				info.put("PublishDate", rs.getString("PublishDate"));
				info.put("PageCount", rs.getInt("PageCount"));
				info.put("Isbn13", rs.getString("Isbn13"));
				info.put("Overview", rs.getString("Overview"));
			}
			return info;
		}
	}

	/**
	 * Finds the book with the given ISBN and returns its BookId if it exists in the
	 * Books.
	 * 
	 * @return a Integer object which contains BookId or null (must put this return
	 *         value inside an Integer object to be able to detect null values)
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static Integer getBookFromIsbn(String isbn13) throws SQLException {
		String sql = "SELECT BookId FROM Books WHERE Isbn13 = ?";
		Integer bookId = null;

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, isbn13);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bookId = rs.getInt("BookId");
			}
			return bookId;
		}
	}

	/**
	 * Finds the book with the given bookCopyId and returns its BookId if it exists
	 * in the
	 * Books.
	 * 
	 * @return a Integer object which contains BookId or null (must put this return
	 *         value inside an Integer object to be able to detect null values)
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static Integer getBookFromCopyId(int bookCopyId) throws SQLException {
		String sql = "SELECT BookId FROM BookCopies WHERE BookCopyId = ?";
		Integer bookId = null;

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bookCopyId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bookId = rs.getInt("BookId");
			}
			return bookId;
		}
	}

	/**
	 * Finds bookCopyId of the book copy with the given bookId and libraryId
	 * 
	 * @return a Integer object which contains BookCopyId or null (must put this
	 *         return value inside an Integer object to be able to detect null
	 *         values)
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static Integer convertToCopyId(int bookId, int libraryId) throws SQLException {
		String sqlGetCopyId = "SELECT BookCopyId FROM BookCopies WHERE BookId = ? AND LibraryId = ?";
		Integer bookCopyId = null;

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sqlGetCopyId)) {
			pstmt.setInt(1, bookId);
			pstmt.setInt(2, libraryId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				bookCopyId = rs.getInt("BookCopyId");
			}
			return bookCopyId;
		}
	}

	/**
	 * Finds 10 most recent published books that belong to the given genre and
	 * returns them.
	 * 
	 * @return a LinkedList containing BookIds
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getBookRecommendations(String genre) throws SQLException {
		String sql = "SELECT BookId FROM Books LEFT JOIN BookGenres USING(BookId) WHERE Genre = ? ORDER BY PublishDate DESC LIMIT 10";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, genre);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.addLast(rs.getInt("BookId"));
			}
			return books;
		}
	}

	/**
	 * Finds books that belong to the given genre and
	 * returns them.
	 * 
	 * @return a LinkedList containing BookIds
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getBooksFromGenre(String genre) throws SQLException {
		String sql = "SELECT BookId FROM Books LEFT JOIN BookGenres USING(BookId) WHERE Genre LIKE ? ORDER BY PublishDate DESC LIMIT 10";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + genre + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.addLast(rs.getInt("BookId"));
			}
			return books;
		}
	}

	/**
	 * Finds books of the given author.
	 * 
	 * @return a LinkedList containing BookIds
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getBooksFromAuthor(String authorName) throws SQLException {
		String sql = "SELECT BookId FROM Books WHERE Author LIKE ? ORDER BY Title";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + authorName + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.addLast(rs.getInt("BookId"));
			}
			return books;
		}
	}

	/**
	 * Finds books by the given book title.
	 * 
	 * @return a LinkedList containing BookIds
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getBooksFromTitle(String bookTitle) throws SQLException {
		String sql = "SELECT BookId FROM Books WHERE Title LIKE ? ORDER BY Title";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + bookTitle + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.addLast(rs.getInt("BookId"));
			}
			return books;
		}
	}

	/**
	 * Finds all of the written comments of the given book and returns them.
	 * 
	 * @return a LinkedList containing HashMaps of the comments and their users
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<HashMap<String, Object>> getCommentsOfBook(int bookId) throws SQLException {
		String sql = "SELECT UserId, Comment FROM Comments WHERE BookId = ?";
		LinkedList<HashMap<String, Object>> comments = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				HashMap<String, Object> comment = new HashMap<>();
				comment.put("UserId", rs.getInt("UserId"));
				comment.put("Comment", rs.getString("Comment"));
				comments.add(comment);
			}
			return comments;
		}
	}

	/**
	 * Finds the libraries that has the given book in stock and returns them.
	 * 
	 * @return a LinkedList containing LibraryIds, ordered by CopyCount (desc)
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getLibrariesOfBook(int bookId) throws SQLException {
		String sql = "SELECT LibraryId FROM BookCopies WHERE BookId = ? ORDER BY CopyCount DESC";
		LinkedList<Integer> libraries = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				int bookCopyId;
				int libraryId = rs.getInt("LibraryId");

				bookCopyId = convertToCopyId(bookId, libraryId);
				if (isBookInStock(bookCopyId)) {
					libraries.addLast(rs.getInt("LibraryId"));
				}
			}
			return libraries;
		}
	}

	/**
	 * Finds all of the genres of the book and returns them.
	 * 
	 * @return a LinkedList containing genres
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<String> getGenresOfBook(int bookId) throws SQLException {
		String sql = "SELECT Genre FROM BookGenres WHERE BookId = ?";
		LinkedList<String> genres = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				genres.addLast(rs.getString("Genre"));
			}
			return genres;
		}
	}

	/**
	 * Finds one genre of the given book returns it
	 * 
	 * @return a genre
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static String getGenreFromBook(int bookId) throws SQLException {
		String sql = "SELECT Genre FROM BookGenres WHERE BookId = ? LIMIT 1";
		String genre = null;

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, bookId);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				genre = rs.getString("Genre");
			}
			return genre;
		}
	}

	/**
	 * Donates an existing book to a library. If there is no such book, then creates
	 * a new book and donates that.
	 * 
	 * @param genres a string that contains comma seperated genre titles of the book
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static void donateBook(String title, String author, String publishDate, int pageCount, String isbn13,
			String overview, String genres, int libraryId) throws SQLException {
		String sqlCreate = "INSERT INTO Books (Title, Author, PublishDate, PageCount, Isbn13, Overview)"
				+ "VALUES (?, ?, ?, ?, ?, ?)";
		String sqlGenre = "INSERT INTO BookGenres (BookId, Genre)"
				+ "VALUES (?, ?)";
		String sqlUpdateCopies = "UPDATE BookCopies SET CopyCount = CopyCount + 1 WHERE BookId = ? AND LibraryId = ?";
		String sqlInsertCopies = "INSERT INTO BookCopies (BookId, LibraryId, CopyCount)"
				+ "VALUES (?, ?, ?)";

		try (Connection conn = connectToDatabase()) {
			Integer bookId;
			bookId = getBookFromIsbn(isbn13);
			if (bookId == null) { // book does not exist
				int lastBookId;

				try (Statement stmt = conn.createStatement()) {
					ResultSet rs = stmt.executeQuery("SELECT BookId FROM Books ORDER BY BookId DESC");
					if (rs.next()) {
						lastBookId = rs.getInt("BookId");
						lastBookId++;
					} else {
						throw new SQLException();
					}
				}
				try (PreparedStatement pstmt = conn.prepareStatement(sqlCreate)) {
					pstmt.setString(1, title);
					pstmt.setString(2, author);
					pstmt.setString(3, publishDate);
					pstmt.setInt(4, pageCount);
					pstmt.setString(5, isbn13);
					pstmt.setString(6, overview);
					pstmt.executeUpdate();
				}
				try (PreparedStatement pstmt = conn.prepareStatement(sqlGenre)) {
					pstmt.setInt(1, lastBookId);
					for (String genre : genres.split(",")) {
						pstmt.setString(2, genre.strip());
						pstmt.executeUpdate();
					}
				}
				try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertCopies)) {
					pstmt.setInt(1, lastBookId);
					pstmt.setInt(2, libraryId);
					pstmt.setInt(3, 1);
					pstmt.executeUpdate();
				}
			} else { // book already in the books table
				LinkedList<Integer> libraries = getLibrariesOfBook(bookId);
				boolean registered = false;

				for (int lib : libraries) {
					if (lib == libraryId) {
						registered = true;
					}
				}
				if (registered) { // library has at least one copy of this book
					try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdateCopies)) {
						pstmt.setInt(1, bookId);
						pstmt.setInt(2, libraryId);
						pstmt.executeUpdate();
					}
				} else {
					try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertCopies)) {
						pstmt.setInt(1, bookId);
						pstmt.setInt(2, libraryId);
						pstmt.setInt(3, 1);
						pstmt.executeUpdate();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Searches Books table and returns BookIds of the books that has Title or
	 * Author similar to the string.
	 * 
	 * @return a LinkedList containing BookIds, ordered by the book title (a-z)
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getSearchedBooks(String searchStr) throws SQLException {
		String sql = "SELECT BookId FROM Books WHERE Title LIKE ? OR Author LIKE ? ORDER BY Title";
		LinkedList<Integer> books = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, "%" + searchStr + "%");
			pstmt.setString(2, "%" + searchStr + "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				books.addLast(rs.getInt("BookId"));
			}
			return books;
		}
	}

	/**
	 * Finds all of the unique genres in the BookGenres table and returns them
	 * 
	 * @return a LinkedList containing genres
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<String> getUniqueGenres() throws SQLException {
		String sql = "SELECT DISTINCT Genre FROM BookGenres ORDER BY Genre";
		LinkedList<String> genres = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				genres.addLast(rs.getString("Genre"));
			}
			return genres;
		}
	}

	/**
	 * Finds the given library in the database and returns its contact info.
	 * 
	 * @return HashMap containing the columns of the matching library or empty
	 *         HashMap if the library does not exist
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static HashMap<String, Object> getLibraryContactInfo(int libraryId) throws SQLException {
		String sql = "SELECT LibraryId, Name, Address, PhoneNumber, Email, EmailPrivateKey FROM Libraries "
				+ "WHERE LibraryId = ?";
		HashMap<String, Object> info = new HashMap<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, libraryId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				info.put("LibraryId", rs.getInt("LibraryId"));
				info.put("Name", rs.getString("Name"));
				info.put("Address", rs.getString("Address"));
				info.put("PhoneNumber", rs.getString("PhoneNumber"));
				info.put("Email", rs.getString("Email"));
				info.put("EmailPrivateKey", rs.getString("EmailPrivateKey"));
			}
			return info;
		}
	}

	/**
	 * Finds the given library in the database and returns its shelf properties.
	 * 
	 * @return HashMap containing the columns of the matching library or empty
	 *         HashMap if the library does not exist
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static HashMap<String, Integer> getLibraryShelfInfo(int libraryId) throws SQLException {
		String sql = "SELECT LibraryId, StartPointX, StartPointY, RowCount, ColumnCount, ShelfWidth, ShelfHeight, ShelfHorizontalGap, ShelfVerticalGap "
				+ "FROM Libraries "
				+ "WHERE LibraryId = ?";
		HashMap<String, Integer> info = new HashMap<>();

		try (Connection conn = connectToDatabase();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, libraryId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				info.put("LibraryId", rs.getInt("LibraryId"));
				info.put("StartPointX", rs.getInt("StartPointX"));
				info.put("StartPointY", rs.getInt("StartPointY"));
				info.put("RowCount", rs.getInt("RowCount"));
				info.put("ColumnCount", rs.getInt("ColumnCount"));
				info.put("ShelfWidth", rs.getInt("ShelfWidth"));
				info.put("ShelfHeight", rs.getInt("ShelfHeight"));
				info.put("ShelfHorizontalGap", rs.getInt("ShelfHorizontalGap"));
				info.put("ShelfVerticalGap", rs.getInt("ShelfVerticalGap"));
			}
			return info;
		}
	}

	/**
	 * Finds all of the libraries and returns them.
	 * 
	 * @return a LinkedList containing LibraryIds
	 * @throws SQLException
	 */
	@SuppressWarnings("resource")
	public static LinkedList<Integer> getLibraries() throws SQLException {
		String sql = "SELECT LibraryId FROM Libraries";
		LinkedList<Integer> libraries = new LinkedList<>();

		try (Connection conn = connectToDatabase();
				Statement stmt = conn.createStatement()) {

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				libraries.addLast(rs.getInt("LibraryId"));
			}
			return libraries;
		}
	}

}
