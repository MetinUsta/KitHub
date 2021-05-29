package userInterface;

import java.sql.SQLException;
import java.util.HashMap;

public class Library {
	private int libraryId;
	private String Name;
	private String Address;
	private String PhoneNumber;
	private String Email;
	private int stockCount;
	private static int bookId;
	
	public Library(int libraryId) {
		try {
			this.libraryId = libraryId;
			HashMap<String, Object> libraryInfo = Database.getLibraryContactInfo(libraryId);
			this.Name = (String) libraryInfo.get("Name");
			this.Address = (String) libraryInfo.get("Address");
			this.PhoneNumber = (String) libraryInfo.get("PhoneNumber");
			this.Email = (String) libraryInfo.get("Email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Library(int libraryId, int stockCount, int bookId) {
		this(libraryId);
		this.stockCount = stockCount;
		Library.bookId = bookId;
	}
	
	public int getlibraryId() {
		return libraryId;
	}

	public String getName() {
		return Name;
	}

	public String getAddress() {
		return Address;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getEmail() {
		return Email;
	}

	public int getStockCount() {
		return stockCount;
	}
	
	
}
