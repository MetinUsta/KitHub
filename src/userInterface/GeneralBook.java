package userInterface;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class GeneralBook extends Book{
	private int bookId;
	private String Overview;
	
	public GeneralBook(int bookId, int imageHeight) {
		this.bookId = bookId;
		try {
			HashMap<String, Object> bookInfo = Database.getBookInfo(bookId);
			
			this.bookId = bookId;
			setISBN((String) bookInfo.get("Isbn13"));
			String coverPath = "/bookCovers/" + getISBN() + ".jpg";
			setName((String) bookInfo.get("Title"));
			setOverview((String) bookInfo.get("Overview"));
			setCover(new ImageIcon(getClass().getResource(coverPath)), imageHeight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getBookId() {
		return bookId;
	}

	public String getOverview() {
		return Overview;
	}

	public void setOverview(String Overview) {
		this.Overview = Overview;
	}
}
