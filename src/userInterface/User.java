package userInterface;

import java.sql.SQLException;
import java.util.HashMap;

public class User {
	private int UserId;
	private String Name;
	private String Surname;
	private String Email;
	private boolean LateReturnStatus;

	public User(int UserId) {
		this.UserId = UserId;
		try {
			HashMap<String, Object> userInfo = Database.getUserInfo(UserId);
			this.Name = (String) userInfo.get("Name");
			this.Surname = (String) userInfo.get("Surname");
			this.Email = (String) userInfo.get("Email");
			this.LateReturnStatus = (boolean) userInfo.get("LateReturnStatus");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public int getUserId() {
		return UserId;
	}

	public String getName() {
		return Name;
	}

	public String getSurname() {
		return Surname;
	}

	public String getEmail() {
		return Email;
	}

	public boolean isLateReturnStatus() {
		return LateReturnStatus;
	}

}
