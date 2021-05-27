package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Clock;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Actions {

}

class UserProfileHandler implements ActionListener{
	static int testUserId = 5;
	// TODO: Daha sonra giriş yapan kullanıcının id değeri getirilecek
	static HashMap<String, Object> info;
	static LinkedList<String> comments = new LinkedList<>();
	static DefaultListModel<String> commentsJList = new DefaultListModel<>();
	DefaultListModel<LibraryBook> libraryBooks;
	JList <LibraryBook> libraryBooksJList;
	JScrollPane takenBooksScroll;
	JFrame frame;
	
	public UserProfileHandler(DefaultListModel<LibraryBook> takenBooks, JList <LibraryBook> libraryBooksJList, JScrollPane pane, JFrame frame) {
		this.frame = frame;
		this.libraryBooks = takenBooks;
		this.takenBooksScroll=pane;
		this.libraryBooksJList = libraryBooksJList;
	}
	private static HashMap<String, Object> GetInfo() {		
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
	
	public static DefaultListModel<LibraryBook> GetBooks(){
		DefaultListModel<LibraryBook> libraryBookList = new DefaultListModel<>();
		LinkedList<Integer> loanedBooks = new LinkedList<>();
		try {
			loanedBooks = Database.getLoanedBooks(testUserId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0;i<loanedBooks.size();i++) {
			LibraryBook temp = new LibraryBook(loanedBooks.get(i),Book.smallIcon);
			libraryBookList.addElement(temp);
		}
		return libraryBookList;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Clock clock = Clock.systemUTC();
		String returnDate = clock.instant().toString();
		int bookCopyId = libraryBooksJList.getSelectedValue().getBookCopyId();
		
		try {
			Database.userReturnBook(testUserId,bookCopyId,returnDate);
			libraryBooks.remove(libraryBooksJList.getSelectedIndex());
			JOptionPane.showMessageDialog(frame, "Book returned succesfully!");
			
		}catch(Exception ex) {
			if(ex instanceof LateBookReturnException) {
				JOptionPane.showMessageDialog(frame, "Book returned succesfully. \nYou will not be able to buy new books for 15 days because you bring the book late");
			}
		}
		
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