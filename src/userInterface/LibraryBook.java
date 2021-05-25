package userInterface;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.ImageIcon;

public class LibraryBook extends Book{
	private int bookCopyId;
	
	public LibraryBook(int bookCopyId, int imageHeight) {
		this.bookCopyId = bookCopyId;
		try {
			
			Integer bookId = Database.getBookFromCopyId(bookCopyId);
			
			HashMap<String, Object> bookInfo = Database.getBookInfo(bookId);
			setISBN((String) bookInfo.get("Isbn13"));
			setName((String) bookInfo.get("Name"));
			String coverPath = "/bookCovers/" + getISBN() + ".png";
			setCover(new ImageIcon(getClass().getResource(coverPath)), imageHeight);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getBookCopyId() {
		return bookCopyId;
	}
	
}
