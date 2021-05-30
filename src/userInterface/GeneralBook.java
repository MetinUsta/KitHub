package userInterface;

import java.net.URL;
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
			URL url = getClass().getResource(coverPath);
			
			if(url != null) {
				ImageIcon cover = new ImageIcon(getClass().getResource(coverPath));
				setCover(cover, imageHeight);
			}else {
				setCover(null, imageHeight);
			}
			
		} catch (SQLException e) {
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
