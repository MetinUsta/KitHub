package userInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.Clock;
import java.util.HashMap;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Actions {

}

class UserProfileHandler implements ActionListener {
	static int testUserId = 5;
	// TODO: Daha sonra giriş yapan kullanıcının id değeri getirilecek
	static HashMap<String, Object> info;
	static LinkedList<String> comments = new LinkedList<>();
	static DefaultListModel<String> commentsJList = new DefaultListModel<>();
	DefaultListModel<LibraryBook> libraryBooks;
	JList<LibraryBook> libraryBooksJList;
	JScrollPane takenBooksScroll;
	JFrame frame;

	public UserProfileHandler(DefaultListModel<LibraryBook> takenBooks, JList<LibraryBook> libraryBooksJList,
			JScrollPane pane, JFrame frame) {
		this.frame = frame;
		this.libraryBooks = takenBooks;
		this.takenBooksScroll = pane;
		this.libraryBooksJList = libraryBooksJList;
	}

	private static HashMap<String, Object> GetInfo() {
		if (info == null) {
			try {
				info = Database.getUserInfo(testUserId);
			} catch (SQLException e) {
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

	public static DefaultListModel<String> GetPreComments() {
		try {
			comments = Database.getUserComments(testUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < comments.size(); i++) {
			commentsJList.addElement(comments.get(i));
		}
		return commentsJList;
	}

	public static DefaultListModel<LibraryBook> GetBooks() {
		DefaultListModel<LibraryBook> libraryBookList = new DefaultListModel<>();
		LinkedList<Integer> loanedBooks = new LinkedList<>();
		try {
			loanedBooks = Database.getLoanedBooks(testUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < loanedBooks.size(); i++) {
			LibraryBook temp = new LibraryBook(loanedBooks.get(i), Book.smallIcon);
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
			Database.userReturnBook(testUserId, bookCopyId, returnDate);
			libraryBooks.remove(libraryBooksJList.getSelectedIndex());
			JOptionPane.showMessageDialog(frame, "Book returned succesfully!");

		} catch (Exception ex) {
			if (ex instanceof LateBookReturnException) {
				JOptionPane.showMessageDialog(frame,
						"Book returned succesfully. \nYou will not be able to buy new books for 15 days because you bring the book late");
			}
		}

	}

}

class BookSearchListHandler implements ActionListener {
	private JTextField searchBar;
	private JComboBox<String> searchTypeComboBox;
	private DefaultListModel<GeneralBook> bookList;
	private JList<GeneralBook> list;

	public BookSearchListHandler(JTextField searchBar, JComboBox<String> searchTypeComboBox,
			DefaultListModel<GeneralBook> bookList, JList<GeneralBook> list) {
		this.searchBar = searchBar;
		this.searchTypeComboBox = searchTypeComboBox;
		this.bookList = bookList;
		this.list = list;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		bookList.clear();
		list.clearSelection();

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

class BookInfoListHandler implements ListSelectionListener {
	private DefaultListModel<GeneralBook> bookSuggestList;
	private DefaultListModel<String> reviewListModel;
	private JButton overviewHoverButton;
	private JLabel overviewTextLabel;
	private HashMap<String, JLabel> bookInfoLabels;

	public BookInfoListHandler(DefaultListModel<GeneralBook> bookSuggestList, JButton overviewHoverButton, JLabel overviewTextLabel, HashMap<String, JLabel> bookInfoLabels,
			DefaultListModel<String> reviewListModel) {
		this.bookSuggestList = bookSuggestList;
		this.overviewHoverButton = overviewHoverButton;
		this.overviewTextLabel = overviewTextLabel;
		this.bookInfoLabels = bookInfoLabels;
		this.reviewListModel = reviewListModel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		bookSuggestList.clear();
		reviewListModel.clear();
		try {
			if (!e.getValueIsAdjusting()) {
				if (((JList<GeneralBook>) e.getSource()).getSelectedValue() == null) {
					return;
				}
				int bookId = ((JList<GeneralBook>) e.getSource()).getSelectedValue().getBookId();
				GeneralBook selectedBook = new GeneralBook(bookId, 494);

				overviewHoverButton.setIcon(selectedBook.getCover());
				overviewTextLabel.setText("<html>" + selectedBook.getOverview() + "</html>");

				String bookGenre = Database.getGenreFromBook(bookId);
				LinkedList<Integer> recommendedBooks = Database.getBooksFromGenre(bookGenre);
				for (Integer recommendedBook : recommendedBooks) {
					if (bookId != recommendedBook) {
						bookSuggestList.addElement(new GeneralBook(recommendedBook, Book.smallIcon));
					}
				}

				bookInfoWriter(bookId, bookInfoLabels);
				bookCommentWriter(bookId, reviewListModel);
			}
		} catch (SQLException error) {
			error.printStackTrace();
		}
	}

	public void bookInfoWriter(int bookId, HashMap<String, JLabel> bookInfoLabels) {

		HashMap<String, Object> bookInfo;
		try {
			bookInfo = Database.getBookInfo(bookId);

			bookInfoLabels.get("Title").setText("<html>" + (String) bookInfo.get("Title") + "</html>");
			bookInfoLabels.get("Author").setText((String) bookInfo.get("Author"));
			bookInfoLabels.get("PublishDate").setText((String) bookInfo.get("PublishDate"));
			bookInfoLabels.get("PageCount").setText(Integer.toString((Integer) bookInfo.get("PageCount")));
			bookInfoLabels.get("ISBN").setText((String) bookInfo.get("Isbn13"));

			LinkedList<String> genresList = Database.getGenresOfBook(bookId);
			String genres = String.join(",", genresList);

			bookInfoLabels.get("Genres").setText(genres);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void bookCommentWriter(int bookId, DefaultListModel<String> reviewListModel) {
		try {
			LinkedList<HashMap<String, Object>> comments = Database.getCommentsOfBook(bookId);

			for (var comment : comments) {
				HashMap<String, Object> userInfo = Database.getUserInfo((int) comment.get("UserId"));
				String userName = (String) userInfo.get("Name");
				String commentText = userName + ": " + (String) comment.get("Comment");
				reviewListModel.addElement(commentText);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

class SuggestedBookInfo implements ListSelectionListener {
	private DefaultListModel<String> reviewListModel;
	private JButton overviewHoverButton;
	private JLabel overviewTextLabel;
	private HashMap<String, JLabel> bookInfoLabels;

	public SuggestedBookInfo(JButton overviewHoverButton, JLabel overviewTextLabel,
			HashMap<String, JLabel> bookInfoLabels,
			DefaultListModel<String> reviewListModel) {
		this.overviewHoverButton = overviewHoverButton;
		this.overviewTextLabel = overviewTextLabel;
		this.bookInfoLabels = bookInfoLabels;
		this.reviewListModel = reviewListModel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		reviewListModel.clear();
		if (!e.getValueIsAdjusting()) {
			if (((JList<GeneralBook>) e.getSource()).getSelectedValue() == null) {
				return;
			}
			int bookId = ((JList<GeneralBook>) e.getSource()).getSelectedValue().getBookId();
			GeneralBook selectedBook = new GeneralBook(bookId, 494);

			overviewHoverButton.setIcon(selectedBook.getCover());
			overviewTextLabel.setText("<html>" + selectedBook.getOverview() + "</html>");

			bookInfoWriter(bookId, bookInfoLabels);
			bookCommentWriter(bookId, reviewListModel);
		}

	}

	public void bookInfoWriter(int bookId, HashMap<String, JLabel> bookInfoLabels) {

		HashMap<String, Object> bookInfo;
		try {
			bookInfo = Database.getBookInfo(bookId);

			bookInfoLabels.get("Title").setText("<html>" + (String) bookInfo.get("Title") + "</html>");
			bookInfoLabels.get("Author").setText((String) bookInfo.get("Author"));
			bookInfoLabels.get("PublishDate").setText((String) bookInfo.get("PublishDate"));
			bookInfoLabels.get("PageCount").setText(Integer.toString((Integer) bookInfo.get("PageCount")));
			bookInfoLabels.get("ISBN").setText((String) bookInfo.get("Isbn13"));

			LinkedList<String> genresList = Database.getGenresOfBook(bookId);
			String genres = String.join(",", genresList);

			bookInfoLabels.get("Genres").setText(genres);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void bookCommentWriter(int bookId, DefaultListModel<String> reviewListModel) {
		try {
			LinkedList<HashMap<String, Object>> comments = Database.getCommentsOfBook(bookId);

			for (var comment : comments) {
				HashMap<String, Object> userInfo = Database.getUserInfo((int) comment.get("UserId"));
				String userName = (String) userInfo.get("Name");
				String commentText = userName + ": " + (String) comment.get("Comment");
				reviewListModel.addElement(commentText);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

class NewCommentHandler implements ActionListener{
	private final static int userId = 1;
	private JTextArea commentArea;
	private DefaultListModel<String> reviewListModel;
	private JLabel bookInfoISBNValue;
	
	public NewCommentHandler(JTextArea commentArea, DefaultListModel<String> reviewListModel, JLabel bookInfoISBNValue) {
		this.commentArea = commentArea;
		this.reviewListModel = reviewListModel;
		this.bookInfoISBNValue = bookInfoISBNValue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newComment = commentArea.getText();
		try {
			int bookId = Database.getBookFromIsbn(bookInfoISBNValue.getText());
			HashMap<String, Object> bookInfo = Database.getBookInfo(bookId);
			Database.addNewComment(userId, bookId, newComment);
			
			HashMap<String, Object> userInfo = Database.getUserInfo(userId);
			String userName = (String) userInfo.get("Name");
			String commentText = userName + ": " + newComment;
			reviewListModel.addElement(commentText);
			commentArea.setText("");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
}