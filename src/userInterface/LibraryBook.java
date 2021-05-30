package userInterface;

import java.net.URL;
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
			setName((String) bookInfo.get("Title"));
			String coverPath = "/bookCovers/" + getISBN() + ".jpg";
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

	public int getBookCopyId() {
		return bookCopyId;
	}
	
}
