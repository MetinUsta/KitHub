package userInterface;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.border.LineBorder;

import javax.swing.JComboBox;
import javax.swing.ListSelectionModel;
import javax.swing.JTextArea;
import java.awt.Cursor;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class Window {
	
	private JFrame frame;
	private JTextField donateFieldTitle;
	private JTextField donateFieldAuthor;
	private JTextField donateFieldPageCount;
	private JTextField donateFieldISBN;
	private JTextField donateFieldPublishDate;
	private JLabel personValueLastName;
	private Color sideBarColor = new Color(39, 43, 47);
	private Color sideBarMenuColor = new Color(33, 37, 41);
	
	private Color componentBorderColor = new Color(34, 38, 41);
	private Color elevation1 = new Color(39, 43, 47);
	private Color backgroundColor = new Color(33, 37, 41);
	private Color textColor = new Color(54, 199, 208);
	private Color sideBarSelectionColor = textColor;
	private Color textColorLight = new Color(255, 255, 255);
	private Color buttonTextColor = new Color(255, 164, 142);
	
	/**
	 * Create the application.
	 */
	public Window(Color textColor, Color buttonTextColor) {
		this.textColor = textColor;
		this.buttonTextColor = buttonTextColor;
		this.sideBarSelectionColor = textColor;
		initialize();
	}

	private void initialize() {
		Font systemText = new Font("Arial Rounded MT Bold", Font.PLAIN, 17);
		Font info = new Font("Arial", Font.PLAIN, 15);
		
		MatteBorder bottomLine = new MatteBorder(0, 0, 2, 0, (Color) buttonTextColor);
		MatteBorder bottomLineTextField = new MatteBorder(0, 0, 3, 0, (Color) buttonTextColor);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1366, 768);
		frame.setLocation(screenSize.width/2 - 1366/2, screenSize.height/2 - 768/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		Image icon = new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/icon.png")).getImage();
		frame.setIconImage(icon);
		frame.setTitle("Test");
		
		JPanel sideBarPanel = new JPanel();
		sideBarPanel.setBounds(0, 0, 141, 739);
		sideBarPanel.setBackground(sideBarColor);
		
		JPanel contentPanel = new JPanel();
		contentPanel.setBounds(140, 0, 1220, 739);
		CardLayout card = new CardLayout(0, 0);
		contentPanel.setLayout(card);

		JPanel bookLoanPanel = new JPanel();
		bookLoanPanel.setBackground(backgroundColor);
		contentPanel.add(bookLoanPanel, "bookLoan");
		
		JPanel bookDonationPanel = new JPanel();
		contentPanel.add(bookDonationPanel, "bookDonation");
		
		JPanel mainPagePanel = new JPanel();
		contentPanel.add(mainPagePanel, "mainPage");
		card.show(contentPanel, "mainPage");
		
		JScrollPane bookListScrollPane = new JScrollPane();
		bookListScrollPane.setBorder(null);
		bookListScrollPane.setBounds(10, 57, 317, 448);
		bookListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		bookListScrollPane.setBorder(bottomLine);
		bookListScrollPane.getVerticalScrollBar().setUI(new ScrollBarColor(textColor, buttonTextColor, backgroundColor));
		bookListScrollPane.getHorizontalScrollBar().setUI(new ScrollBarColor(textColor, buttonTextColor, backgroundColor));
		bookListScrollPane.getVerticalScrollBar().setBackground(backgroundColor);
		bookListScrollPane.getHorizontalScrollBar().setBackground(backgroundColor);
		
		DefaultListModel<GeneralBook> bookList = new DefaultListModel<>();
		JList<GeneralBook> list = new JList<>(bookList);

		list.setCellRenderer(new BookNameListRenderer(textColor));
		list.setSelectionForeground(Color.WHITE);
		list.setSelectionBackground(textColor);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBackground(elevation1);
		
		JScrollPane bookSuggestionsScrollPanel = new JScrollPane();
		bookSuggestionsScrollPanel.setBorder(bottomLine);
		bookSuggestionsScrollPanel.setBounds(10, 516, 317, 200);
		
		JPanel bookReview = new JPanel();
		bookReview.setBorder(bottomLine);
		bookReview.setBounds(337, 516, 545, 200);
		bookReview.setBackground(elevation1);
		
		JPanel bookCoverPanel = new JPanel();
		bookCoverPanel.setBounds(338, 11, 310, 494);
		bookCoverPanel.setBackground(Color.GRAY);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(10, 11, 317, 34);
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		JButton bookLoanButton = new JButton("Take Book");
		bookLoanButton.setBorder(null);
		bookLoanButton.setFont(systemText);
		bookLoanButton.setForeground(buttonTextColor);
		bookLoanButton.setBackground(elevation1);
		bookLoanButton.setBounds(980, 445, 127, 60);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setBorder(null);
		searchPanel.add(menuBar);
		
		JTextField searchBar = new JTextField();
		searchBar.setBorder(new LineBorder(Color.WHITE, 4));
		searchBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.add(searchBar);
		searchBar.setColumns(10);
		
		JComboBox<String> searchTypeComboBox = new JComboBox<>();
		searchTypeComboBox.setPrototypeDisplayValue("---------------");
		searchTypeComboBox.setBorder(null);
		searchTypeComboBox.setBackground(Color.WHITE);
		searchTypeComboBox.addItem("Type");
		searchTypeComboBox.addItem("Title");
		searchTypeComboBox.addItem("Author");
		searchTypeComboBox.addItem("ISBN13");
		searchTypeComboBox.addItem("Genre");
		menuBar.add(searchTypeComboBox);
		
		DefaultListModel<GeneralBook> bookSuggestList = new DefaultListModel<>();
		JList<GeneralBook> bookSuggest = new JList<>(bookSuggestList);
		bookSuggest.setVisibleRowCount(1);
		bookSuggest.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		bookSuggest.setBorder(new LineBorder(componentBorderColor, 2));
		bookSuggest.setBackground(elevation1);
		bookSuggest.setSelectionBackground(textColor);
		bookSuggestionsScrollPanel.setViewportView(bookSuggest);
		bookSuggestionsScrollPanel.getHorizontalScrollBar().setBackground(backgroundColor);
		ScrollBarColor scrollBarBookSuggest = new ScrollBarColor(textColor, buttonTextColor, backgroundColor);
		bookSuggestionsScrollPanel.getHorizontalScrollBar().setUI(scrollBarBookSuggest);
		bookSuggestionsScrollPanel.getVerticalScrollBar().setUI(new ScrollBarColor(textColor, buttonTextColor, backgroundColor));
		bookSuggestionsScrollPanel.getVerticalScrollBar().setBackground(backgroundColor);
		
		bookSuggest.setCellRenderer(new BookListRenderer());
		
		bookListScrollPane.setViewportView(list);
		bookLoanPanel.setLayout(null);
		bookLoanPanel.add(searchPanel);
		bookLoanPanel.add(bookListScrollPane);
		bookLoanPanel.add(bookLoanButton);
		bookLoanPanel.add(bookSuggestionsScrollPanel);
		bookLoanPanel.add(bookCoverPanel);
		
		JPanel overviewShadingPanel = new JPanel();
		MatteBorder bottomLineCover = new MatteBorder(2, 2, 2, 2, (Color) buttonTextColor);
		overviewShadingPanel.setBorder(bottomLineCover);
		JButton overviewHoverButton = new JButton("");
		overviewHoverButton.setBackground(elevation1);
		overviewHoverButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				overviewShadingPanel.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				overviewShadingPanel.setVisible(false);
			}
			
			public void mousePressed(MouseEvent e) {
				e.consume();
			}
		});
		
		overviewHoverButton.setBorder(null);
		bookCoverPanel.setLayout(null);
		
		overviewShadingPanel.setVisible(false);
		overviewShadingPanel.setBackground(new Color(0, 0, 0, 102));
		overviewShadingPanel.setBounds(0, 0, 310, 494);
		bookCoverPanel.add(overviewShadingPanel);
		overviewShadingPanel.setLayout(null);
		
		JLabel overviewTextLabel = new JLabel("<html>No books selected yet.</html>");
		overviewTextLabel.setBorder(new LineBorder(new Color(0, 0, 0, 0), 10));
		overviewTextLabel.setBounds(0, 0, 310, 494);
		overviewTextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		overviewTextLabel.setVerticalAlignment(SwingConstants.TOP);
		overviewTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		overviewTextLabel.setForeground(Color.WHITE);
		overviewShadingPanel.add(overviewTextLabel);
		overviewHoverButton.setBounds(0, 0, 310, 494);
		bookCoverPanel.add(overviewHoverButton);
		bookLoanPanel.add(bookReview);
		bookReview.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(bottomLine);
		scrollPane.setBounds(0, 0, 545, 200);
		bookReview.add(scrollPane);
		scrollPane.getHorizontalScrollBar().setBackground(backgroundColor);
		ScrollBarColor scrollBarComments = new ScrollBarColor(textColor, buttonTextColor, backgroundColor);
		scrollPane.getHorizontalScrollBar().setUI(scrollBarComments);
		
		DefaultListModel<String> reviewListModel = new DefaultListModel<>();
		JList<String> reviewList = new JList<>(reviewListModel);
		reviewList.setBorder(null);
		reviewList.setSelectionForeground(Color.WHITE);
		reviewList.setSelectionBackground(textColor);
		reviewList.setForeground(Color.WHITE);
		reviewList.setBackground(elevation1);
		scrollPane.setViewportView(reviewList);
		reviewList.setCellRenderer(new CommentListRenderer(textColor));
		reviewListModel.addElement("Will hit you right in the heart. —Bustle");
		reviewListModel.addElement("Delightfully wicked fun! —Kirkus Reviews");
		reviewListModel.addElement("Gosh awful narrator. Sorry. In its favor I could only stomach 3 pages. -Washington Post");
		
		JPanel panel = new JPanel();
		panel.setBorder(bottomLine);
		panel.setBackground(elevation1);
		panel.setBounds(658, 11, 224, 494);
		bookLoanPanel.add(panel);
		panel.setLayout(null);
		
		JLabel bookInfoTitleLabel = new JLabel("Title:");
		bookInfoTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel.setForeground(textColor);
		bookInfoTitleLabel.setFont(systemText);
		bookInfoTitleLabel.setBounds(10, 11, 60, 33);
		panel.add(bookInfoTitleLabel);
		
		JLabel bookInfoTitleValue = new JLabel("Value");
		bookInfoTitleValue.setVerticalAlignment(SwingConstants.TOP);
		bookInfoTitleValue.setMaximumSize(new Dimension(194, 33));
		bookInfoTitleValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleValue.setForeground(textColorLight);
		bookInfoTitleValue.setFont(info);
		bookInfoTitleValue.setBounds(20, 46, 194, 33);
		panel.add(bookInfoTitleValue);
		
		JLabel bookInfoAuthorLabel = new JLabel("Author:");
		bookInfoAuthorLabel.setForeground(textColor);
		bookInfoAuthorLabel.setFont(systemText);
		bookInfoAuthorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorLabel.setBounds(10, 80, 60, 33);
		panel.add(bookInfoAuthorLabel);
		
		JLabel bookInfoAuthorValue = new JLabel("Value");
		bookInfoAuthorValue.setVerticalAlignment(SwingConstants.TOP);
		bookInfoAuthorValue.setFont(info);
		bookInfoAuthorValue.setForeground(textColorLight);
		bookInfoAuthorValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorValue.setBounds(20, 115, 194, 33);
		panel.add(bookInfoAuthorValue);
		
		JLabel bookInfoPublishDateLabel = new JLabel("Publish Date:");
		bookInfoPublishDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateLabel.setForeground(textColor);
		bookInfoPublishDateLabel.setFont(systemText);
		bookInfoPublishDateLabel.setBounds(10, 149, 112, 33);
		panel.add(bookInfoPublishDateLabel);
		
		JLabel bookInfoPublishDateValue = new JLabel("Value");
		bookInfoPublishDateValue.setVerticalAlignment(SwingConstants.TOP);
		bookInfoPublishDateValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateValue.setForeground(textColorLight);
		bookInfoPublishDateValue.setFont(info);
		bookInfoPublishDateValue.setBounds(20, 184, 194, 33);
		panel.add(bookInfoPublishDateValue);
		
		JLabel bookInfoPageCountLabel = new JLabel("Page Count:");
		bookInfoPageCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPageCountLabel.setForeground(textColor);
		bookInfoPageCountLabel.setFont(systemText);
		bookInfoPageCountLabel.setBounds(10, 218, 112, 33);
		panel.add(bookInfoPageCountLabel);
		
		JLabel bookInfoPageCountValue = new JLabel("Value");
		bookInfoPageCountValue.setVerticalAlignment(SwingConstants.TOP);
		bookInfoPageCountValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPageCountValue.setForeground(textColorLight);
		bookInfoPageCountValue.setFont(info);
		bookInfoPageCountValue.setBounds(20, 253, 194, 33);
		panel.add(bookInfoPageCountValue);
		
		JLabel bookInfoISBNLabel = new JLabel("ISBN:");
		bookInfoISBNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoISBNLabel.setForeground(textColor);
		bookInfoISBNLabel.setFont(systemText);
		bookInfoISBNLabel.setBounds(10, 287, 60, 33);
		panel.add(bookInfoISBNLabel);
		
		JLabel bookInfoISBNValue = new JLabel("Value");
		bookInfoISBNValue.setVerticalAlignment(SwingConstants.TOP);
		bookInfoISBNValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoISBNValue.setForeground(textColorLight);
		bookInfoISBNValue.setFont(info);
		bookInfoISBNValue.setBounds(20, 322, 194, 33);
		panel.add(bookInfoISBNValue);
		
		JLabel bookInfoGenresLabel = new JLabel("Genres:");
		bookInfoGenresLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoGenresLabel.setForeground(textColor);
		bookInfoGenresLabel.setFont(systemText);
		bookInfoGenresLabel.setBounds(10, 356, 82, 33);
		panel.add(bookInfoGenresLabel);
		
		JLabel bookInfoGenresValue = new JLabel("Value");
		bookInfoGenresValue.setVerticalAlignment(SwingConstants.TOP);
		bookInfoGenresValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoGenresValue.setForeground(Color.WHITE);
		bookInfoGenresValue.setFont(new Font("Arial", Font.PLAIN, 15));
		bookInfoGenresValue.setBounds(20, 391, 194, 33);
		panel.add(bookInfoGenresValue);
		
		JPanel libraryInfoPanel = new JPanel();
		libraryInfoPanel.setBorder(bottomLine);
		libraryInfoPanel.setBackground(elevation1);
		libraryInfoPanel.setBounds(892, 259, 303, 163);
		bookLoanPanel.add(libraryInfoPanel);
		libraryInfoPanel.setLayout(null);
		
		JLabel libraryInfoNameLabel = new JLabel("Name:");
		libraryInfoNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoNameLabel.setForeground(textColor);
		libraryInfoNameLabel.setFont(systemText);
		libraryInfoNameLabel.setBounds(10, 11, 82, 33);
		libraryInfoPanel.add(libraryInfoNameLabel);
		
		JLabel libraryInfoNameValue = new JLabel("Value");
		libraryInfoNameValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoNameValue.setForeground(textColorLight);
		libraryInfoNameValue.setFont(info);
		libraryInfoNameValue.setBounds(102, 11, 191, 33);
		libraryInfoPanel.add(libraryInfoNameValue);
		
		JLabel libraryInfoPhoneLabel = new JLabel("Phone:");
		libraryInfoPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoPhoneLabel.setForeground(textColor);
		libraryInfoPhoneLabel.setFont(systemText);
		libraryInfoPhoneLabel.setBounds(10, 44, 82, 33);
		libraryInfoPanel.add(libraryInfoPhoneLabel);
		
		JLabel libraryInfoPhoneValue = new JLabel("Value");
		libraryInfoPhoneValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoPhoneValue.setForeground(textColorLight);
		libraryInfoPhoneValue.setFont(info);
		libraryInfoPhoneValue.setBounds(102, 44, 191, 33);
		libraryInfoPanel.add(libraryInfoPhoneValue);
		
		JLabel libraryInfoAddressLabel = new JLabel("Address:");
		libraryInfoAddressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoAddressLabel.setForeground(textColor);
		libraryInfoAddressLabel.setFont(systemText);
		libraryInfoAddressLabel.setBounds(10, 77, 82, 33);
		libraryInfoPanel.add(libraryInfoAddressLabel);
		
		JLabel libraryInfoAddressValue = new JLabel("Value");
		libraryInfoAddressValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoAddressValue.setForeground(textColorLight);
		libraryInfoAddressValue.setFont(info);
		libraryInfoAddressValue.setBounds(102, 77, 191, 33);
		libraryInfoPanel.add(libraryInfoAddressValue);
		
		JLabel libraryInfoEmailLabel = new JLabel("Email:");
		libraryInfoEmailLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoEmailLabel.setForeground(textColor);
		libraryInfoEmailLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		libraryInfoEmailLabel.setBounds(10, 110, 82, 33);
		libraryInfoPanel.add(libraryInfoEmailLabel);
		
		JLabel libraryInfoEmailValue = new JLabel("Value");
		libraryInfoEmailValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoEmailValue.setForeground(Color.WHITE);
		libraryInfoEmailValue.setFont(new Font("Arial", Font.PLAIN, 15));
		libraryInfoEmailValue.setBounds(102, 110, 191, 33);
		libraryInfoPanel.add(libraryInfoEmailValue);
		
		JScrollPane librarySelectionScrollPanel = new JScrollPane();
		librarySelectionScrollPanel.setBorder(bottomLine);
		librarySelectionScrollPanel.setBounds(892, 34, 303, 214);
		bookLoanPanel.add(librarySelectionScrollPanel);
		librarySelectionScrollPanel.getVerticalScrollBar().setUI(new ScrollBarColor(textColor, buttonTextColor, backgroundColor));
		librarySelectionScrollPanel.getHorizontalScrollBar().setUI(new ScrollBarColor(textColor, buttonTextColor, backgroundColor));
		librarySelectionScrollPanel.getVerticalScrollBar().setBackground(backgroundColor);
		librarySelectionScrollPanel.getHorizontalScrollBar().setBackground(backgroundColor);
		
		DefaultListModel<Library> libraryList = new DefaultListModel<>();
		JList<Library> librarySelectionList = new JList<>(libraryList);
		librarySelectionList.setCellRenderer(new LibraryListRenderer(textColor));
		librarySelectionList.setSelectionForeground(Color.WHITE);
		librarySelectionList.setSelectionBackground(textColor);
		librarySelectionList.setBackground(elevation1);
		librarySelectionScrollPanel.setViewportView(librarySelectionList);
		
		JLabel librarySelectLabel = new JLabel("Choose a library from the below list:");
		librarySelectLabel.setFont(systemText);
		librarySelectLabel.setForeground(Color.WHITE);
		librarySelectLabel.setBounds(892, 9, 303, 24);
		bookLoanPanel.add(librarySelectLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setCaretColor(Color.WHITE);
		textArea.setLineWrap(true);
		textArea.setFont(info);
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(bottomLine);
		textArea.setBackground(elevation1);
		textArea.setBounds(892, 516, 303, 151);
		bookLoanPanel.add(textArea);
		
		JButton commentButton = new JButton("Comment");
		commentButton.setForeground(buttonTextColor);
		commentButton.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		commentButton.setBorder(bottomLine);
		commentButton.setBackground(new Color(39, 43, 47));
		commentButton.setBounds(994, 672, 98, 44);
		bookLoanPanel.add(commentButton);
		frame.getContentPane().setLayout(null);
		sideBarPanel.setLayout(null);
		frame.getContentPane().add(sideBarPanel);
		
		JPanel bookLoanSidemenuBar = new JPanel();
		bookLoanSidemenuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookLoanSidemenuBar.setBackground(sideBarMenuColor);
		bookLoanSidemenuBar.setBounds(16, 214, 125, 89);
		sideBarPanel.add(bookLoanSidemenuBar);
		bookLoanSidemenuBar.setLayout(null);
		
		JLabel bookLoanSidemenuLabel = new JLabel("");
		bookLoanSidemenuLabel.setBounds(33, 11, 64, 64);
		bookLoanSidemenuBar.add(bookLoanSidemenuLabel);
		bookLoanSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/bookIcon.png")));
		bookLoanSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel bookDonationSidemenuBar = new JPanel();
		bookDonationSidemenuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bookDonationSidemenuBar.setLayout(null);
		bookDonationSidemenuBar.setBackground(sideBarMenuColor);
		bookDonationSidemenuBar.setBounds(16, 314, 125, 89);
		sideBarPanel.add(bookDonationSidemenuBar);
		
		JLabel bookDonationSidemenuLabel = new JLabel("");
		bookDonationSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/bookDonationIcon.png")));
		bookDonationSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookDonationSidemenuLabel.setBounds(33, 11, 64, 64);
		bookDonationSidemenuBar.add(bookDonationSidemenuLabel);
		
		JPanel profileSidemenuBar = new JPanel();
		profileSidemenuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		profileSidemenuBar.setLayout(null);
		profileSidemenuBar.setBackground(sideBarMenuColor);
		profileSidemenuBar.setBounds(16, 613, 125, 89);
		sideBarPanel.add(profileSidemenuBar);
		
		JLabel profileSidemenuLabel = new JLabel("");
		profileSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/userMenuIcon.png")));
		profileSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSidemenuLabel.setBounds(33, 11, 64, 64);
		profileSidemenuBar.add(profileSidemenuLabel);
		
		JPanel bookLoanSidemenuSelection = new JPanel();
		bookLoanSidemenuSelection.setBackground(sideBarMenuColor);
		bookLoanSidemenuSelection.setBounds(0, 214, 16, 89);
		sideBarPanel.add(bookLoanSidemenuSelection);
		
		JPanel bookDonationSidemenuSelection = new JPanel();
		bookDonationSidemenuSelection.setBackground(sideBarMenuColor);
		bookDonationSidemenuSelection.setBounds(0, 314, 16, 89);
		sideBarPanel.add(bookDonationSidemenuSelection);
		
		JPanel profileSidemenuSelection = new JPanel();
		profileSidemenuSelection.setBackground(sideBarMenuColor);
		profileSidemenuSelection.setBounds(0, 613, 16, 89);
		sideBarPanel.add(profileSidemenuSelection);
		
		JPanel mainPageSidemenuBar = new JPanel();
		mainPageSidemenuBar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mainPageSidemenuBar.setLayout(null);
		mainPageSidemenuBar.setBackground(new Color(33, 37, 41));
		mainPageSidemenuBar.setBounds(16, 114, 125, 89);
		sideBarPanel.add(mainPageSidemenuBar);
		
		JLabel mainPageSidemenuLabel = new JLabel("");
		mainPageSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		mainPageSidemenuLabel.setBounds(33, 11, 64, 64);
		mainPageSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/mainPageIcon.png")));
		mainPageSidemenuBar.add(mainPageSidemenuLabel);
		
		JPanel mainPageSidemenuSelection = new JPanel();
		mainPageSidemenuSelection.setBackground(sideBarSelectionColor);
		mainPageSidemenuSelection.setBounds(0, 114, 16, 89);
		sideBarPanel.add(mainPageSidemenuSelection);
		frame.getContentPane().add(contentPanel);
		
		bookDonationPanel.setBackground(backgroundColor);
		
		bookDonationPanel.setLayout(null);
		
		JPanel panelMiddle = new JPanel();
		panelMiddle.setBorder(bottomLine);
		panelMiddle.setBackground(elevation1);
		panelMiddle.setBounds(247, 190, 726, 358);
		bookDonationPanel.add(panelMiddle);
		panelMiddle.setLayout(null);
		
		JScrollPane libraryDonationScrollPanel = new JScrollPane();
		libraryDonationScrollPanel.setBounds(413, 38, 303, 214);
		panelMiddle.add(libraryDonationScrollPanel);
		libraryDonationScrollPanel.setBorder(bottomLine);
		
		DefaultListModel<Library> librarySelectionForm = new DefaultListModel<>();
		JList<Library> librarySelectionListDonation = new JList<>(librarySelectionForm);
		librarySelectionListDonation.setBackground(new Color(56, 56, 61));
		libraryDonationScrollPanel.setViewportView(librarySelectionListDonation);
		libraryDonationScrollPanel.getVerticalScrollBar().setBackground(backgroundColor);
		ScrollBarColor scrollBarDonation = new ScrollBarColor(textColor, buttonTextColor, backgroundColor);
		libraryDonationScrollPanel.getVerticalScrollBar().setUI(scrollBarDonation);
		librarySelectionListDonation.setCellRenderer(new LibraryListRenderer(textColor));
		
		JLabel infoText = new JLabel("Choose a library from the below list:");
		infoText.setBounds(413, 11, 303, 24);
		panelMiddle.add(infoText);
		infoText.setForeground(Color.WHITE);
		infoText.setFont(systemText);
		
		JButton bookDonateButton = new JButton("Donate Book");
		bookDonateButton.setBounds(515, 263, 127, 60);
		panelMiddle.add(bookDonateButton);
		bookDonateButton.setForeground(buttonTextColor);
		bookDonateButton.setFont(systemText);
		bookDonateButton.setBorder(bottomLine);
		bookDonateButton.setBackground(backgroundColor);
		
		JLabel bookDonateTitleLabel = new JLabel("Title:");
		bookDonateTitleLabel.setBounds(10, 43, 97, 33);
		panelMiddle.add(bookDonateTitleLabel);
		bookDonateTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateTitleLabel.setForeground(textColor);
		bookDonateTitleLabel.setFont(systemText);
		
		donateFieldTitle = new JTextField();
		donateFieldTitle.setBorder(bottomLineTextField);
		donateFieldTitle.setBounds(171, 51, 183, 25);
		panelMiddle.add(donateFieldTitle);
		donateFieldTitle.setColumns(10);
		
		JLabel bookDonateAuthorLabel = new JLabel("Author:");
		bookDonateAuthorLabel.setBounds(10, 87, 97, 33);
		panelMiddle.add(bookDonateAuthorLabel);
		bookDonateAuthorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateAuthorLabel.setForeground(textColor);
		bookDonateAuthorLabel.setFont(systemText);
		
		donateFieldAuthor = new JTextField();
		donateFieldAuthor.setBorder(bottomLineTextField);
		donateFieldAuthor.setBounds(171, 95, 183, 25);
		panelMiddle.add(donateFieldAuthor);
		donateFieldAuthor.setColumns(10);
		
		JLabel bookDonatePageLabel = new JLabel("Page Count:");
		bookDonatePageLabel.setBounds(10, 175, 118, 33);
		panelMiddle.add(bookDonatePageLabel);
		bookDonatePageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonatePageLabel.setForeground(textColor);
		bookDonatePageLabel.setFont(systemText);
		
		JLabel bookDonatePublishDateLabel = new JLabel("Publish Date:");
		bookDonatePublishDateLabel.setBounds(10, 131, 118, 33);
		panelMiddle.add(bookDonatePublishDateLabel);
		bookDonatePublishDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonatePublishDateLabel.setForeground(textColor);
		bookDonatePublishDateLabel.setFont(systemText);
		
		donateFieldPublishDate = new JTextField();
		donateFieldPublishDate.setBorder(bottomLineTextField);
		donateFieldPublishDate.setBounds(171, 139, 183, 25);
		panelMiddle.add(donateFieldPublishDate);
		donateFieldPublishDate.setColumns(10);
		
		donateFieldPageCount = new JTextField();
		donateFieldPageCount.setBorder(bottomLineTextField);
		donateFieldPageCount.setBounds(171, 183, 183, 25);
		panelMiddle.add(donateFieldPageCount);
		donateFieldPageCount.setColumns(10);
		
		donateFieldISBN = new JTextField();
		donateFieldISBN.setBorder(bottomLineTextField);
		donateFieldISBN.setBounds(171, 227, 183, 25);
		panelMiddle.add(donateFieldISBN);
		donateFieldISBN.setColumns(10);
		
		JLabel bookDonateISBNLabel = new JLabel("ISBN:");
		bookDonateISBNLabel.setBounds(10, 219, 97, 33);
		panelMiddle.add(bookDonateISBNLabel);
		bookDonateISBNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateISBNLabel.setForeground(textColor);
		bookDonateISBNLabel.setFont(systemText);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBackground(backgroundColor);
		contentPanel.add(profilePanel, "profile");
		profilePanel.setLayout(null);
		
		JPanel profilePanelMiddle = new JPanel();
		profilePanelMiddle.setBackground(elevation1);
		profilePanelMiddle.setBounds(279, 15, 565, 703);
		profilePanel.add(profilePanelMiddle);
		profilePanelMiddle.setLayout(null);
		profilePanelMiddle.setBorder(bottomLine);
		
		JPanel personalInfoPanel = new JPanel();
		personalInfoPanel.setBounds(122, 35, 320, 152);
		profilePanelMiddle.add(personalInfoPanel);
		personalInfoPanel.setBackground(elevation1);
		personalInfoPanel.setLayout(null);
		
		JLabel personLabelName = new JLabel("Name:");
		personLabelName.setBounds(10, 11, 99, 33);
		personalInfoPanel.add(personLabelName);
		personLabelName.setHorizontalAlignment(SwingConstants.LEFT);
		personLabelName.setForeground(textColor);
		personLabelName.setFont(systemText);
		
		JLabel personValueName = new JLabel("Value");
		personValueName.setBounds(119, 11, 82, 33);
		personalInfoPanel.add(personValueName);
		personValueName.setHorizontalAlignment(SwingConstants.LEFT);
		personValueName.setForeground(textColorLight);
		personValueName.setFont(info);
		personValueName.setText(UserProfileHandler.GetName());
		
		JLabel personLabelLastName = new JLabel("Last Name:");
		personLabelLastName.setBounds(10, 55, 112, 33);
		personalInfoPanel.add(personLabelLastName);
		personLabelLastName.setHorizontalAlignment(SwingConstants.LEFT);
		personLabelLastName.setForeground(textColor);
		personLabelLastName.setFont(systemText);
		
		
		personValueLastName = new JLabel("Value");
		personValueLastName.setBounds(119, 55, 82, 33);
		personalInfoPanel.add(personValueLastName);
		personValueLastName.setHorizontalAlignment(SwingConstants.LEFT);
		personValueLastName.setForeground(textColorLight);
		personValueLastName.setFont(info);
		personValueLastName.setText(UserProfileHandler.GetLastName());
		
		JLabel personLabelEmail = new JLabel("Email:");
		personLabelEmail.setBounds(10, 99, 112, 33);
		personalInfoPanel.add(personLabelEmail);
		personLabelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		personLabelEmail.setForeground(textColor);
		personLabelEmail.setFont(systemText);
		
		JLabel personValueEmail = new JLabel("Value");
		personValueEmail.setBounds(119, 99, 191, 33);
		personalInfoPanel.add(personValueEmail);
		personValueEmail.setHorizontalAlignment(SwingConstants.LEFT);
		personValueEmail.setForeground(textColorLight);
		personValueEmail.setFont(info);
		personValueEmail.setText(UserProfileHandler.GetEmail());
		
		JScrollPane takenBooksScroll = new JScrollPane();
		takenBooksScroll.setBounds(122, 210, 320, 200);
		profilePanelMiddle.add(takenBooksScroll);
		takenBooksScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		takenBooksScroll.setBorder(null);
		takenBooksScroll.getHorizontalScrollBar().setBackground(backgroundColor);
		ScrollBarColor scrollBarTakenBooks = new ScrollBarColor(textColor, buttonTextColor, backgroundColor);
		takenBooksScroll.getHorizontalScrollBar().setUI(scrollBarTakenBooks);
		
		DefaultListModel<LibraryBook> takenBooks = UserProfileHandler.GetBooks();
		JList<LibraryBook> takenBooksList = new JList<>(takenBooks);
		takenBooksList.setSelectionBackground(textColor);
		takenBooksScroll.setViewportView(takenBooksList);
		takenBooksList.setVisibleRowCount(1);
		takenBooksList.setCellRenderer(new BookListRenderer());
		takenBooksList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		takenBooksList.setBackground(elevation1);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.setBounds(218, 421, 127, 60);
		profilePanelMiddle.add(btnReturnBook);
		btnReturnBook.setForeground(buttonTextColor);
		btnReturnBook.setFont(systemText);
		btnReturnBook.setBorder(bottomLine);
		btnReturnBook.setBackground(backgroundColor);
		
		JScrollPane preCommentsScrollPane = new JScrollPane();
		preCommentsScrollPane.setBounds(10, 513, 545, 167);
		preCommentsScrollPane.setBorder(bottomLine);
		profilePanelMiddle.add(preCommentsScrollPane);
		preCommentsScrollPane.getHorizontalScrollBar().setBackground(backgroundColor);
		ScrollBarColor scrollBarPreComment = new ScrollBarColor(textColor, buttonTextColor, backgroundColor);
		preCommentsScrollPane.getHorizontalScrollBar().setUI(scrollBarPreComment);
		
		DefaultListModel<String> takenBooksModel = UserProfileHandler.GetPreComments();
		JList<String> preCommentsList = new JList<String>(takenBooksModel);
		preCommentsList.setSelectionForeground(Color.WHITE);
		preCommentsList.setSelectionBackground(textColor);
		preCommentsList.setForeground(Color.WHITE);
		preCommentsList.setBackground(new Color(56, 56, 61));
		preCommentsList.setCellRenderer(new CommentListRenderer(textColor));
		preCommentsScrollPane.setViewportView(preCommentsList);
		
		/*takenBooksModel.addElement("A tensely written, shocking book that will hold readers on the edge of their seats to the very last page.");
		takenBooksModel.addElement("Both compelling and addictive, The Favorite Daughter is a roller coaster of a ride that will have you twisting and turning.");
		takenBooksModel.addElement("Will hit you right in the heart.");*/
		
		JLabel lblNewLabel = new JLabel("Personal Info:");
		lblNewLabel.setFont(systemText);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(122, 11, 320, 23);
		profilePanelMiddle.add(lblNewLabel);
		
		JLabel infoTextTakenBooks = new JLabel("Taken Books:");
		infoTextTakenBooks.setForeground(Color.WHITE);
		infoTextTakenBooks.setFont(systemText);
		infoTextTakenBooks.setBounds(122, 187, 320, 23);
		profilePanelMiddle.add(infoTextTakenBooks);
		
		JLabel infoTextPreComments = new JLabel("Previous Comments:");
		infoTextPreComments.setForeground(Color.WHITE);
		infoTextPreComments.setFont(systemText);
		infoTextPreComments.setBounds(10, 481, 320, 23);
		profilePanelMiddle.add(infoTextPreComments);
		
		
		mainPagePanel.setBackground(backgroundColor);
		mainPagePanel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(buttonTextColor);
		panel_1.setBounds(0, 536, 1220, 165);
		mainPagePanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel openQuoteLabel = new JLabel("");
		openQuoteLabel.setOpaque(true);
		openQuoteLabel.setBackground(buttonTextColor);
		openQuoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		openQuoteLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/quoteIcon/colorlessQuoteOpen.png")));
		openQuoteLabel.setBounds(47, 28, 49, 32);
		panel_1.add(openQuoteLabel);
		
		JLabel closeQuoteLabel = new JLabel("");
		closeQuoteLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/quoteIcon/colorlessQuoteClose.png")));
		closeQuoteLabel.setOpaque(true);
		closeQuoteLabel.setHorizontalAlignment(SwingConstants.CENTER);
		closeQuoteLabel.setBackground(buttonTextColor);
		closeQuoteLabel.setBounds(1117, 112, 49, 32);
		panel_1.add(closeQuoteLabel);
		
		JLabel quoteLabel = new JLabel("The two most important days in your life are the day you are born and the day you find out why");
		quoteLabel.setForeground(Color.WHITE);
		quoteLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 24));
		quoteLabel.setBounds(54, 64, 1112, 42);
		panel_1.add(quoteLabel);
		
		JLabel quoteBackgroundLabel = new JLabel("");
		quoteBackgroundLabel.setBorder(new MatteBorder(4, 4, 4, 4, (Color) new Color(255, 255, 255)));
		quoteBackgroundLabel.setOpaque(true);
		quoteBackgroundLabel.setBackground(buttonTextColor);
		quoteBackgroundLabel.setIcon(null);
		quoteBackgroundLabel.setBounds(35, 40, 1150, 90);
		panel_1.add(quoteBackgroundLabel);
		
		JLabel infoTextOldBooks = new JLabel("Books you have read so far:");
		infoTextOldBooks.setFont(systemText);
		infoTextOldBooks.setForeground(Color.WHITE);
		infoTextOldBooks.setBounds(10, 116, 232, 49);
		mainPagePanel.add(infoTextOldBooks);
		
		JScrollPane oldBooksScrollPane = new JScrollPane();
		oldBooksScrollPane.setBounds(10, 163, 1195, 362);
		oldBooksScrollPane.setBorder(bottomLine);
		oldBooksScrollPane.setBackground(elevation1);
		mainPagePanel.add(oldBooksScrollPane);
		
		DefaultListModel<GeneralBook> oldBooksListModel = new DefaultListModel<>();
		JList<GeneralBook> oldBooksList = new JList<>(oldBooksListModel);
		oldBooksList.setSelectionBackground(textColor);
		oldBooksList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		oldBooksList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		oldBooksList.setVisibleRowCount(1);
		oldBooksList.setBackground(elevation1);
		oldBooksScrollPane.setViewportView(oldBooksList);
		oldBooksList.setCellRenderer(new BookListRenderer());
		
		JLabel appNameLabel = new JLabel("Library Management App");
		appNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		appNameLabel.setForeground(Color.WHITE);
		appNameLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 32));
		appNameLabel.setBounds(10, 11, 1200, 80);
		appNameLabel.setBorder(bottomLine);
		mainPagePanel.add(appNameLabel);
		oldBooksScrollPane.getHorizontalScrollBar().setBackground(backgroundColor);
		ScrollBarColor scrollBaroldBooks = new ScrollBarColor(textColor, buttonTextColor, backgroundColor);
		oldBooksScrollPane.getHorizontalScrollBar().setUI(scrollBaroldBooks);
		
		JPanel[] selectionList = {mainPageSidemenuSelection, bookLoanSidemenuSelection, bookDonationSidemenuSelection, profileSidemenuSelection};
		JPanel[] barList = {mainPageSidemenuBar, bookLoanSidemenuBar, bookDonationSidemenuBar, profileSidemenuBar};
		
		MouseAdapter select = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(e.getSource() == mainPageSidemenuBar) {
					card.show(contentPanel, "mainPage");
					mainPageSidemenuSelection.setBackground(sideBarSelectionColor);
				}
				if(e.getSource() == bookLoanSidemenuBar) {
					card.show(contentPanel, "bookLoan");
					bookLoanSidemenuSelection.setBackground(sideBarSelectionColor);
				}
				if(e.getSource() == bookDonationSidemenuBar) {
					card.show(contentPanel, "bookDonation");
					bookDonationSidemenuSelection.setBackground(sideBarSelectionColor);
				}
				if(e.getSource() == profileSidemenuBar) {
					card.show(contentPanel, "profile");
					profileSidemenuSelection.setBackground(sideBarSelectionColor);
				}
				for(int i = 0;i<4;i++) {
					if(e.getSource() != barList[i]) {
						selectionList[i].setBackground(sideBarMenuColor);
					}
				}
			}
		};
		
		HashMap<String, JLabel> bookInfoLabels = new HashMap<>();
		
		bookInfoLabels.put("Title", bookInfoTitleValue);
		bookInfoLabels.put("Author", bookInfoAuthorValue);
		bookInfoLabels.put("Genres", bookInfoGenresValue);
		bookInfoLabels.put("ISBN", bookInfoISBNValue);
		bookInfoLabels.put("PageCount", bookInfoPageCountValue);
		bookInfoLabels.put("PublishDate", bookInfoPublishDateValue);
		
		HashMap<String, JLabel> libraryInfoLabels = new HashMap<>();
		libraryInfoLabels.put("Name", libraryInfoNameValue);
		libraryInfoLabels.put("Address", libraryInfoAddressValue);
		libraryInfoLabels.put("PhoneNumber", libraryInfoPhoneValue);
		libraryInfoLabels.put("Email", libraryInfoEmailValue);
		
		System.out.println(bookInfoLabels.get("Title").getText());
		mainPageSidemenuBar.addMouseListener(select);
		bookLoanSidemenuBar.addMouseListener(select);
		bookDonationSidemenuBar.addMouseListener(select);
		profileSidemenuBar.addMouseListener(select);
		
		btnReturnBook.addActionListener(new UserProfileHandler(takenBooks,takenBooksList,takenBooksScroll,frame));
		//TODO
		searchBar.addActionListener(new BookSearchListHandler(searchBar, searchTypeComboBox, bookList, list));
		list.addListSelectionListener(new BookInfoListHandler(bookSuggestList, overviewHoverButton, overviewTextLabel, bookInfoLabels, reviewListModel));
		bookSuggest.addListSelectionListener(new SuggestedBookInfo(overviewHoverButton, overviewTextLabel, bookInfoLabels, reviewListModel));
		commentButton.addActionListener(new NewCommentHandler(textArea, reviewListModel, bookInfoISBNValue));
		bookInfoISBNValue.addPropertyChangeListener(new LibraryListHandler(bookInfoISBNValue, libraryList, librarySelectionList));
		librarySelectionList.addListSelectionListener(new libraryListListener(libraryInfoLabels));
		//button.addActionListener(new libraryListListener());
		frame.setVisible(false);
	}

	public Color getTextColor() {
		return textColor;
	}

	public Color getbuttonTextColor() {
		return buttonTextColor;
	}
	
	
	
	public void setTextColor(int r, int g, int b) {
		Color newColor = new Color(r, g, b);
		this.textColor = newColor;
	}

	public void setbuttonTextColor(int r, int g, int b) {
		Color newColor = new Color(r, g, b);
		this.buttonTextColor = newColor;
	}
	
	public JFrame getFrame() {
		return frame;
	}

	public ImageIcon scaleColorOption(String path) {
		Image colorImage = new ImageIcon(Window.class.getResource(path)).getImage();
		Image scaledColorImage = colorImage.getScaledInstance((int) (28*1.91), 28, Image.SCALE_SMOOTH);
		ImageIcon scaledColorImageIcon = new ImageIcon(scaledColorImage);
		return scaledColorImageIcon;
	}
}

class BookListRenderer extends DefaultListCellRenderer {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("helvetica", Font.BOLD, 14);

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setText("<html><body style='width:80px'>" + ((Book) value).getName() + "</html>");
        label.setIcon(((Book) value).getCover());
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));;
        label.setBorder(new LineBorder(new Color(39, 43, 47), 4));
        //label.setBorder(new MatteBorder(0, 4, 0, 4, new Color(39, 43, 47)));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setFont(font);
        /*Dimension d = label.getSize();
        d.width = 150;
        d.height = 150;
        label.setPreferredSize(d);*/
        label.setForeground(Color.white);
        return label;
    }
}

class LibraryListRenderer extends DefaultListCellRenderer {
	private Color textColor;
	private MatteBorder bottomLine;// = new MatteBorder(0, 0, 2, 0, new Color(54, 199, 208));
	
	private static final long serialVersionUID = 1L;
	Font font = new Font("helvetica", Font.BOLD, 14);
	
	
	
    public LibraryListRenderer(Color textColor) {
		this.textColor = textColor;
		this.bottomLine = new MatteBorder(0, 0, 2, 0, textColor);
	}



	@Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        String labelText = String.format("%3d - %s",((Library) value).getStockCount(), ((Library) value).getName());
        label.setText(labelText);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        //list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));;
        label.setBorder(bottomLine);
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }
}

class BookNameListRenderer extends DefaultListCellRenderer {
	private Color bottomLineColor;
	MatteBorder bottomLine;
	
	private static final long serialVersionUID = 1L;
	Font font = new Font("helvetica", Font.BOLD, 14);
	
	public BookNameListRenderer(Color bottomLineColor) {
		this.bottomLineColor = bottomLineColor;
		this.bottomLine = new MatteBorder(0, 0, 2, 0, this.bottomLineColor);
	}
	
    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setText(((GeneralBook) value).getName());
        //label.setHorizontalTextPosition(JLabel.CENTER);
        //label.setVerticalTextPosition(JLabel.BOTTOM);
        //list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));;
        label.setBorder(bottomLine);
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }
}

class CommentListRenderer extends DefaultListCellRenderer {
	
	Color commentLine;
	MatteBorder bottomLine = new MatteBorder(0, 0, 2, 0, (Color) commentLine);
	
	public CommentListRenderer(Color textColor) {
		commentLine = textColor;
		bottomLine = new MatteBorder(0, 0, 2, 0, (Color) commentLine);
	}
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font font = new Font("helvitica", Font.BOLD, 14);

    @Override
    public Component getListCellRendererComponent(
            JList<?> list, Object value, int index,
            boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(
                list, value, index, isSelected, cellHasFocus);
        label.setText((String) value);
        label.setHorizontalTextPosition(JLabel.LEFT);
        label.setBorder(bottomLine);
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }
}

class ScrollBarColor extends BasicScrollBarUI{
		Color textColor;
		Color buttonTextColor;
		Color backgroundColor;
		
		ScrollBarColor(Color textColor, Color buttonTextColor, Color backgroundColor){
			this.textColor = textColor;
			this.buttonTextColor = buttonTextColor;
			this.backgroundColor = backgroundColor;
		}
	
		protected JButton createDecreaseButton(int orientation) {
        	JButton button = super.createDecreaseButton(orientation);
        	button.setBackground(buttonTextColor);
        	button.setForeground(backgroundColor);
        	button.setBorder(null);
        	return button;
    	}

    	@Override
    	protected JButton createIncreaseButton(int orientation) {
        	JButton button = super.createIncreaseButton(orientation);
        	button.setBackground(buttonTextColor);
        	button.setForeground(backgroundColor);
        	button.setBorder(null);
        	return button;
    	}
	
		@Override
		protected void configureScrollBarColors() {
			this.thumbColor = textColor;
			this.scrollBarWidth = 8;
		}
}