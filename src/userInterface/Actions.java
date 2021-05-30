package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Actions {

}

class SideBarSelectionHandler extends MouseAdapter {
	private CardLayout card;
	private JPanel contentPanel;
	private JPanel[] selectionList;
	private JPanel[] barList;
	private Color sideBarSelectionColor;
	private Color sideBarMenuColor;
	private DefaultListModel<LibraryBook> takenBooks;
	private DefaultListModel<String> reviewListProfileModel;
	private DefaultListModel<GeneralBook> oldBooksListModel;
	private DefaultListModel<Library> librarySelectionForm;

	public SideBarSelectionHandler(CardLayout card, JPanel contentPanel, JPanel[] selectionList, JPanel[] barList,
			Color sideBarSelectionColor, Color sideBarMenuColor, DefaultListModel<LibraryBook> takenBooks,
			DefaultListModel<String> reviewListProfileModel, DefaultListModel<GeneralBook> oldBooksListModel, DefaultListModel<Library> librarySelectionForm) {
		this.card = card;
		this.contentPanel = contentPanel;
		this.selectionList = selectionList;
		this.barList = barList;
		this.sideBarSelectionColor = sideBarSelectionColor;
		this.sideBarMenuColor = sideBarMenuColor;
		this.takenBooks = takenBooks;
		this.reviewListProfileModel = reviewListProfileModel;
		this.oldBooksListModel = oldBooksListModel;
		this.librarySelectionForm = librarySelectionForm;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() == barList[0]) {
			oldBooksListModel.clear();
			card.show(contentPanel, "mainPage");
			selectionList[0].setBackground(sideBarSelectionColor);
			LinkedList<Integer> returnedBooks;
			try {
				returnedBooks = Database.getReturnedBooks(Window.getLoggedUser().getUserId());
				for (var returnedBook : returnedBooks) {
					oldBooksListModel.addElement(new GeneralBook(returnedBook, Book.largeIcon));
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			// oldBooksListModel.
		}
		if (e.getSource() == barList[1]) {
			card.show(contentPanel, "bookLoan");
			selectionList[1].setBackground(sideBarSelectionColor);
		}
		if (e.getSource() == barList[2]) {
			librarySelectionForm.clear();
			card.show(contentPanel, "bookDonation");
			selectionList[2].setBackground(sideBarSelectionColor);
			
			try {
				LinkedList<Integer> allLibraries = Database.getLibraries();
				for(var library : allLibraries) {
					librarySelectionForm.addElement(new Library(library));
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		if (e.getSource() == barList[3]) {
			card.show(contentPanel, "profile");
			takenBooks.clear();
			reviewListProfileModel.clear();
			selectionList[3].setBackground(sideBarSelectionColor);

			try {

				LinkedList<Integer> takenBooksList = Database.getLoanedBooks(Window.getLoggedUser().getUserId());

				for (var takenBook : takenBooksList) {
					takenBooks.addElement(new LibraryBook(takenBook, Book.smallIcon));
				}

				LinkedList<String> userComments = Database.getUserComments(Window.getLoggedUser().getUserId());

				for (var comment : userComments) {
					reviewListProfileModel.addElement(comment);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		for (int i = 0; i < 4; i++) {
			if (e.getSource() != barList[i]) {
				selectionList[i].setBackground(sideBarMenuColor);
			}
		}
	}
}

class ReturnBookButtonAction implements ActionListener {

	private JList<LibraryBook> takenBooksList;
	private DefaultListModel<LibraryBook> takenBooks;

	public ReturnBookButtonAction(JList<LibraryBook> takenBooksList, DefaultListModel<LibraryBook> takenBooks) {
		this.takenBooksList = takenBooksList;
		this.takenBooks = takenBooks;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Clock clock = Clock.systemUTC();
		String returnDate = clock.instant().toString();
		if (takenBooks.getSize() != 0) {
			int bookCopyId = takenBooksList.getSelectedValue().getBookCopyId();
			try {
				Database.userReturnBook(Window.getLoggedUser().getUserId(), bookCopyId, returnDate);
				takenBooks.remove(takenBooksList.getSelectedIndex());
				JOptionPane.showMessageDialog(null, "Book returned successfully!", "Information Panel",
						JOptionPane.INFORMATION_MESSAGE);

			} catch (Exception ex) {
				if (ex instanceof LateBookReturnException) {
					JOptionPane.showMessageDialog(null,
							"Book returned succesfully. \\nYou will not be able to buy new books for "
									+ Database.LATE_RETURN_PENALTY + "days because you bring the book late",
							"Information Panel",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}

class UserProfileHandler implements ActionListener {
	static int testUserId = Window.getLoggedUser().getUserId();
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

	public BookInfoListHandler(DefaultListModel<GeneralBook> bookSuggestList, JButton overviewHoverButton,
			JLabel overviewTextLabel, HashMap<String, JLabel> bookInfoLabels,
			DefaultListModel<String> reviewListModel) {
		this.bookSuggestList = bookSuggestList;
		this.overviewHoverButton = overviewHoverButton;
		this.overviewTextLabel = overviewTextLabel;
		this.bookInfoLabels = bookInfoLabels;
		this.reviewListModel = reviewListModel;
	}

	@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
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

class NewCommentHandler implements ActionListener {
	private final static int userId = Window.getLoggedUser().getUserId();
	private JTextArea commentArea;
	private DefaultListModel<String> reviewListModel;
	private JLabel bookInfoISBNValue;

	public NewCommentHandler(JTextArea commentArea, DefaultListModel<String> reviewListModel,
			JLabel bookInfoISBNValue) {
		this.commentArea = commentArea;
		this.reviewListModel = reviewListModel;
		this.bookInfoISBNValue = bookInfoISBNValue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newComment = commentArea.getText();
		if (newComment.isBlank() || newComment.isEmpty()) {
			return;
		}
		try {
			int bookId = Database.getBookFromIsbn(bookInfoISBNValue.getText());
			Database.addNewComment(userId, bookId, newComment);

			HashMap<String, Object> userInfo = Database.getUserInfo(userId);
			String userName = (String) userInfo.get("Name");
			String commentText = userName + ": " + newComment;
			reviewListModel.addElement(commentText);
			commentArea.setText("");
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "You have already made a comment for this book", "!Warning!",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();// 19
		}
	}

}

class LibraryListHandler implements PropertyChangeListener {
	private JLabel bookISBN;
	private DefaultListModel<Library> libraryList;
	private JList<Library> librarySelectionList;

	public LibraryListHandler(JLabel bookISBN, DefaultListModel<Library> libraryList,
			JList<Library> librarySelectionList) {
		this.bookISBN = bookISBN;
		this.libraryList = libraryList;
		this.librarySelectionList = librarySelectionList;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		libraryList.clear();
		librarySelectionList.clearSelection();
		String Isbn13 = bookISBN.getText();
		if (Isbn13.compareTo("Value") == 0) {
			return;
		}
		try {
			int bookId = Database.getBookFromIsbn(Isbn13);
			LinkedList<HashMap<String, Integer>> libraries = Database.getLibrariesOfBook(bookId);
			if (libraries.size() == 0) {
				return;
			}
			for (var library : libraries) {
				libraryList.addElement(new Library(library.get("LibraryId"), library.get("StockCount"), bookId));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

class libraryListListener implements ListSelectionListener {
	private HashMap<String, JLabel> bookInfoLabels;

	public libraryListListener(HashMap<String, JLabel> bookInfoLabels) {
		this.bookInfoLabels = bookInfoLabels;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
			if (((JList<Library>) e.getSource()).getSelectedValue() != null) {
				Library selectedLibrary = ((JList<Library>) e.getSource()).getSelectedValue();
				bookInfoLabels.get("Name").setText(selectedLibrary.getName());
				bookInfoLabels.get("PhoneNumber").setText(selectedLibrary.getPhoneNumber());
				bookInfoLabels.get("Address").setText(selectedLibrary.getAddress());
				bookInfoLabels.get("Email").setText(selectedLibrary.getEmail());

				bookInfoLabels.get("Name").setToolTipText(selectedLibrary.getName());
				bookInfoLabels.get("PhoneNumber").setToolTipText(selectedLibrary.getPhoneNumber());
				bookInfoLabels.get("Address").setToolTipText(selectedLibrary.getAddress());
				bookInfoLabels.get("Email").setToolTipText(selectedLibrary.getEmail());
			}
		}
	}

}

class LoanBookAction implements ActionListener {
	private int userId;
	private JList<Library> librarySelectionList;
	private int bookId;
	private int libraryId;
	private JLabel bookInfoISBNValue;

	public LoanBookAction(int userId, JList<Library> librarySelectionList, JLabel bookInfoISBNValue) {
		this.userId = userId;
		this.librarySelectionList = librarySelectionList;
		this.bookInfoISBNValue = bookInfoISBNValue;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		bookId = Library.getBookId();
		libraryId = librarySelectionList.getSelectedValue().getlibraryId();
		if (librarySelectionList.getSelectedValue() != null) {
			try {
				Clock clock = Clock.systemUTC();
				Database.userLoanBook(userId, bookId, libraryId, clock.instant().toString());
				JOptionPane.showMessageDialog(null, "You have successfully loaned the book.",
						"Information Panel",
						JOptionPane.INFORMATION_MESSAGE);
				Database.callPython("src/scripts/BookLocationFinder.py", String.valueOf(libraryId),
						bookInfoISBNValue.getText());
				Database.callPython("src/scripts/emailSending.py", bookInfoISBNValue.getText(),
						Window.getLoggedUser().getEmail());
			} catch (SQLException e1) {
				e1.printStackTrace();
			} catch (LateBookReturnException e1) {
				JOptionPane.showMessageDialog(null, "You can not loan book until you've served your penalties",
						"!Warning!",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			} catch (CurrentlyLoanedException e1) {
				JOptionPane.showMessageDialog(null, "You already have a copy of this book", "!Warning!",
						JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
	}

}

class SignInHandler extends MouseAdapter {
	private JTextField emailLoginInput;
	private JPasswordField passwordFieldLogin;
	private LoginPage loginPage;

	public SignInHandler(JTextField emailLoginInput, JPasswordField passwordFieldLogin, JFrame frame,
			LoginPage loginPage) {
		this.emailLoginInput = emailLoginInput;
		this.passwordFieldLogin = passwordFieldLogin;
		this.loginPage = loginPage;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		HashMap<String, Object> information;

		try {
			information = Database.getUserInfo(emailLoginInput.getText(),
					Security.getPasswordHash(String.valueOf(passwordFieldLogin.getPassword())));
			if (information.get("Surname") != null) {
				loginPage.createWindow((int) information.get("UserId"));
			} else {
				JOptionPane.showMessageDialog(null, "Kullanıcı adı veya şifre hatalı");
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}
}

class SignUpHandler extends MouseAdapter {
	private JTextField name;
	private JTextField lastName;
	private JTextField email;
	private JPasswordField password;
	private LoginPage loginPage;

	public SignUpHandler(JTextField NameTextField, JTextField LastNameTextField, JTextField EmailTextField,
			JPasswordField passwordField, LoginPage loginPage) {
		super();
		this.name = NameTextField;
		this.lastName = LastNameTextField;
		this.email = EmailTextField;
		this.password = passwordField;
		this.loginPage = loginPage;
	}

	public void mousePressed(MouseEvent e) {
		HashMap<String, Object> information;

		try {
			Database.addNewUser(name.getText(), lastName.getText(), email.getText(),
					Security.getPasswordHash(String.valueOf(password.getPassword())));
			information = Database.getUserInfo(email.getText(),
					Security.getPasswordHash(String.valueOf(password.getPassword())));
			JOptionPane.showMessageDialog(null, "Kaydedildi");

			loginPage.createWindow((int) information.get("UserId"));
		} catch (SQLException e1) {
			JOptionPane.showMessageDialog(null, "Bu email kullanılıyor");
		}

	}
}

class bookDonationHandler implements ActionListener {

	private JTextField donateFieldAuthor;
	private JTextField donateFieldISBN;
	private JTextField donateFieldPageCount;
	private JTextField donateFieldPublishDate;
	private JTextField donateFieldTitle;
	private JTextField donateFieldGenres;
	private JTextField donateFieldOverview;
	private JList<Library> librarySelectionListDonation;

	public bookDonationHandler(JTextField donateFieldAuthor, JTextField donateFieldISBN,
			JTextField donateFieldPageCount, JTextField donateFieldPublishDate, JTextField donateFieldTitle,
			JTextField donateFieldGenres, JTextField donateFieldOverview, JList<Library> librarySelectionListDonation) {
		this.donateFieldAuthor = donateFieldAuthor;
		this.donateFieldISBN = donateFieldISBN;
		this.donateFieldPageCount = donateFieldPageCount;
		this.donateFieldPublishDate = donateFieldPublishDate;
		this.donateFieldTitle = donateFieldTitle;
		this.donateFieldGenres = donateFieldGenres;
		this.donateFieldOverview = donateFieldOverview;
		this.librarySelectionListDonation = librarySelectionListDonation;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			Database.donateBook(donateFieldTitle.getText(), donateFieldAuthor.getText(),
					donateFieldPublishDate.getText(), Integer.valueOf(donateFieldPageCount.getText()),
					donateFieldISBN.getText(), donateFieldOverview.getText(), donateFieldGenres.getText(),
					librarySelectionListDonation.getSelectedValue().getlibraryId());
			JOptionPane.showMessageDialog(null, "Thanks for donating a book!", "Information Panel",
					JOptionPane.INFORMATION_MESSAGE);
			donateFieldAuthor.setText("");
			donateFieldISBN.setText("");
			donateFieldPageCount.setText("");
			donateFieldPublishDate.setText("");
			donateFieldTitle.setText("");
			donateFieldGenres.setText("");
			donateFieldOverview.setText("");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

}