package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Actions {

}

class UserProfileHandler implements ActionListener{
	
	static HashMap<String, Object> info;
	static LinkedList<String> comments = new LinkedList<>();
	static DefaultListModel<String> commentsJList = new DefaultListModel<>();
	
	public UserProfileHandler() {
		
	}
	private static HashMap<String, Object> GetInfo() {
		// TODO: Daha sonra giriş yapan kullanıcının id değeri getirilecek
		int testUserId=33;
		
		if(info==null) {
			try {
				info= Database.getUserInfo(testUserId);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return info;
	}
	public static String GetName() {
		return GetInfo().get("Name").toString();
	}
	
	public static String GetLastName() {
		return GetInfo().get("Surname").toString();
	}
	
	public static String GetEmail() {
		return GetInfo().get("Email").toString();
	}
	
	public static DefaultListModel<String> GetPreComments(){
		int testUserId=33;
		try {
			comments = Database.getUserComments(testUserId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i =0;i<comments.size();i++) {
			commentsJList.addElement(comments.get(i));
		}
		return commentsJList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}


class BookSearchListHandler implements ActionListener {
	private JTextField searchBar;
	JComboBox<String> searchTypeComboBox;
	DefaultListModel<GeneralBook> bookList;

	public BookSearchListHandler(JTextField searchBar, JComboBox<String> searchTypeComboBox,
			DefaultListModel<GeneralBook> bookList) {
		this.searchBar = searchBar;
		this.searchTypeComboBox = searchTypeComboBox;
		this.bookList = bookList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		bookList.clear();
		
		String selectedType = (String) searchTypeComboBox.getSelectedItem();
		LinkedList<Integer> bookIds = null;
		if (searchBar.getText().isBlank() || searchBar.getText().isEmpty()) {
			return;
		}
		try {
			switch (selectedType) {
			case "Type":
				bookIds = Database.getSearchedBooks(searchBar.getText());
				break;
			case "Title":
				bookIds = Database.getBooksFromTitle(searchBar.getText());
				break;
			case "Author":
				bookIds = Database.getBooksFromAuthor(searchBar.getText());
				break;
			case "ISBN13":
				bookIds = new LinkedList<Integer>();
				if (Database.getBookFromIsbn(searchBar.getText()) == null) {
					return;
				}
				bookIds.add(Database.getBookFromIsbn(searchBar.getText()));
				break;
			case "Genre":
				bookIds = Database.getBooksFromGenre(searchBar.getText());
				break;
			}
			if (bookIds.size() == 0) {
				return;
			}
			for (Integer bookId : bookIds) {
				bookList.addElement(new GeneralBook(bookId, 494));
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

	}

}

/*
 * list.addListSelectionListener(new ListSelectionListener() {
 * public void valueChanged(ListSelectionEvent e) {
 * }
 * });
 */

class BookInfoListHandler implements ListSelectionListener {
	DefaultListModel<GeneralBook> bookSuggestList;

	public BookInfoListHandler(DefaultListModel<GeneralBook> bookSuggestList) {
		this.bookSuggestList = bookSuggestList;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		bookSuggestList.clear();
		try {
			if (!e.getValueIsAdjusting()) {
				int bookId = ((JList<GeneralBook>) e.getSource()).getSelectedValue().getBookId();
				String bookGenre = Database.getGenreFromBook(bookId);
				LinkedList<Integer> recommendedBooks = Database.getBooksFromGenre(bookGenre);
				for(Integer recommendedBook : recommendedBooks) {
					if(bookId != recommendedBook) {
					bookSuggestList.addElement(new GeneralBook(recommendedBook, Book.smallIcon));
					}
				}
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}

	}

}