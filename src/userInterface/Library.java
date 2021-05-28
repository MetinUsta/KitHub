package userInterface;

import java.sql.SQLException;
import java.util.HashMap;

public class Library {
	private int LibraryId;
	private String Name;
	private String Address;
	private String PhoneNumber;
	private String Email;
	
	public Library(int LibraryId) {
		try {
			this.LibraryId = LibraryId;
			HashMap<String, Object> libraryInfo = Database.getLibraryContactInfo(LibraryId);
			this.Name = (String) libraryInfo.get("Name");
			this.Address = (String) libraryInfo.get("Address");
			this.PhoneNumber = (String) libraryInfo.get("PhoneNumber");
			this.Email = (String) libraryInfo.get("Email");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getLibraryId() {
		return LibraryId;
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
	
}
