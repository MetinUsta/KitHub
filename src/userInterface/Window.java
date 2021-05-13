package userInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
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

public class Window {
	
	private JFrame frame;
	private JTextField donateFieldTitle;
	private JTextField donateFieldAuthor;
	private JTextField donateFieldPageCount;
	private JTextField donateFieldEdition;
	private JTextField donateFieldISBN;
	private JTextField donateFieldPublisher;
	private JTextField donateFieldPublishDate;
	private JLabel personValueLastName;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage loginPage = new LoginPage();
					loginPage.getFrame().setVisible(true);
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		//login();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		/*Color sideBarColor = new Color(22, 22, 24);
		Color sideBarMenuColor = new Color(49, 49, 53);
		Color sideBarSelectionColor = new Color(8, 168, 144);
		Color componentBorderColor = new Color(95, 95, 100);
		Color elevation1 = new Color(56, 56, 61);
		Color backgroundColor = new Color(37, 37, 43);*/
		
		/*Color sideBarColor = new Color(27, 38, 44);
		Color sideBarMenuColor = new Color(19, 59, 92);
		Color sideBarSelectionColor = new Color(247, 164, 0);
		Color componentBorderColor = new Color(58, 158, 253);
		Color elevation1 = new Color(47, 59, 82);
		Color backgroundColor = new Color(37, 46, 66);*/
		
		Color sideBarColor = new Color(39, 43, 47);
		Color sideBarMenuColor = new Color(33, 37, 41);
		Color sideBarSelectionColor = new Color(253, 65, 60);
		Color componentBorderColor = new Color(34, 38, 41);
		Color elevation1 = new Color(39, 43, 47);
		Color backgroundColor = new Color(33, 37, 41);
		Color textColor = new Color(253, 65, 60);
		Color textColorLight = new Color(255, 255, 255);
		Color buttonText = new Color(254, 188, 44);
		Font systemText = new Font("Arial Rounded MT Bold", Font.PLAIN, 17);
		Font info = new Font("Arial", Font.PLAIN, 15);
		
		MatteBorder bottomLine = new MatteBorder(0, 0, 2, 0, (Color) buttonText);
		MatteBorder bottomLineTextField = new MatteBorder(0, 0, 3, 0, (Color) buttonText);
		
		Toolkit kit = Toolkit.getDefaultToolkit();
		Dimension screenSize = kit.getScreenSize();
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 1366, 768);
		frame.setLocation(screenSize.width/2 - 1366/2, screenSize.height/2 - 768/2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
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
		//a.show(contentPanel, "name_180929098874800");
		//contentPanel.show(true);
		//bookLoanPanel.setVisible(false);
		//bookDonation.setVisible(true);
		//bookLoanPanel.setVisible(false);
		
		JScrollPane bookListScrollPane = new JScrollPane();
		bookListScrollPane.setBorder(null);
		bookListScrollPane.setBounds(10, 57, 317, 448);
		bookListScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		bookListScrollPane.setBorder(bottomLine);
		
		DefaultListModel<String> bookList = new DefaultListModel<>();
		JList<String> list = new JList<>(bookList);
		list.setSelectionBackground(Color.YELLOW);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//list.setBorder(bottomLine);
		list.setBackground(elevation1);
		
		JScrollPane bookSuggestionsScrollPanel = new JScrollPane();
		bookSuggestionsScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bookSuggestionsScrollPanel.setBorder(bottomLine);
		bookSuggestionsScrollPanel.setBounds(10, 516, 317, 200);
		
		JPanel bookReview = new JPanel();
		bookReview.setBorder(bottomLine);
		bookReview.setBounds(337, 516, 545, 200);
		bookReview.setBackground(elevation1);
		
		JPanel bookCoverPanel = new JPanel();
		bookCoverPanel.setBounds(338, 11, 310, 494);
		bookCoverPanel.setBackground(Color.GRAY);
		//bookCoverPanel.setBorder(bottomLine);
		
		JPanel searchPanel = new JPanel();
		searchPanel.setBounds(10, 11, 317, 34);
		searchPanel.setLayout(new BorderLayout(0, 0));
		
		JButton bookLoanButton = new JButton("Take Book");
		bookLoanButton.setBorder(null);
		bookLoanButton.setFont(systemText);
		bookLoanButton.setForeground(buttonText);
		bookLoanButton.setBackground(elevation1);
		bookLoanButton.setBounds(892, 445, 127, 60);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLACK);
		menuBar.setBorder(null);
		searchPanel.add(menuBar);
		
		
		
		JTextField searchBar = new JTextField();
		searchBar.setBorder(new LineBorder(Color.WHITE, 4));
		searchBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.add(searchBar);
		searchBar.setColumns(10);
		
		JComboBox<String> selectGenreList = new JComboBox<>();
		selectGenreList.setPrototypeDisplayValue("---------------");
		selectGenreList.setBorder(null);
		selectGenreList.setBackground(Color.WHITE);
		selectGenreList.addItem("India");
		menuBar.add(selectGenreList);
		
		DefaultListModel<Book> bookSuggestList = new DefaultListModel<>();
		JList<Book> bookSuggest = new JList<>(bookSuggestList);
		bookSuggest.setVisibleRowCount(1);
		bookSuggest.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		bookSuggest.setBorder(new LineBorder(componentBorderColor, 2));
		bookSuggest.setBackground(elevation1);
		bookSuggest.setSelectionBackground(sideBarSelectionColor);
		bookSuggestionsScrollPanel.setViewportView(bookSuggest);
		//bookSuggest.setCellRenderer(new BookRenderer());
		
		Image goodbye = new ImageIcon(getClass().getResource("/bookCovers/kopya.png")).getImage();
		Image kissing = new ImageIcon(getClass().getResource("/bookCovers/kissingCouple.png")).getImage();
		Image murder = new ImageIcon(getClass().getResource("/bookCovers/murder.png")).getImage();
		Image umbrella = new ImageIcon(getClass().getResource("/bookCovers/umbrella.png")).getImage();
		Image colorful = new ImageIcon(getClass().getResource("/bookCovers/colorful.png")).getImage();
		bookSuggestList.addElement(new Book(1, "Elveda", goodbye));
		bookSuggestList.addElement(new Book(1, "Mmhm...", kissing));
		bookSuggestList.addElement(new Book(1, "Epstein", murder));
		bookSuggestList.addElement(new Book(1, "Raindrops", umbrella));
		bookSuggestList.addElement(new Book(1, "Lily", colorful));
		
		bookSuggest.setCellRenderer(new BookListRenderer());
		/*bookSuggestList.addElement(new Book(2, "1984", "/AppIcons/kissingCouple.png"));
		bookSuggestList.addElement(new Book(2, "1984", "/AppIcons/kissingCouple.png"));
		bookSuggestList.addElement(new Book(2, "1984", "/AppIcons/kissingCouple.png"));
		bookSuggestList.addElement(new Book(2, "1984", "/AppIcons/kissingCouple.png"));
		bookSuggestList.addElement(new Book(2, "1984", "/AppIcons/kissingCouple.png"));
		bookSuggestList.addElement(new Book(2, "1984", "/AppIcons/kissingCouple.png"));*/
		
		bookListScrollPane.setViewportView(list);
		bookLoanPanel.setLayout(null);
		bookLoanPanel.add(searchPanel);
		bookLoanPanel.add(bookListScrollPane);
		bookLoanPanel.add(bookLoanButton);
		bookLoanPanel.add(bookSuggestionsScrollPanel);
		bookLoanPanel.add(bookCoverPanel);
		
		JPanel overviewShadingPanel = new JPanel();
		overviewShadingPanel.setBorder(bottomLine);
		JButton overviewHoverButton = new JButton("");
		overviewHoverButton.setBorder(bottomLine);
		overviewHoverButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				overviewShadingPanel.setVisible(true);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				overviewShadingPanel.setVisible(false);
			}
			
			public void mouseClicked(MouseEvent e) {
				e.consume();
			}
		});
		
		overviewHoverButton.setBorder(null);
		Image original = new ImageIcon(Window.class.getResource("/bookCovers/kissingCouple.png")).getImage();
		Image dimg = original.getScaledInstance(bookCoverPanel.getWidth(), bookCoverPanel.getHeight(), Image.SCALE_SMOOTH);
		bookCoverPanel.setLayout(null);
		
		overviewShadingPanel.setVisible(false);
		overviewShadingPanel.setBackground(new Color(0, 0, 0, 102));
		overviewShadingPanel.setBounds(0, 0, 310, 494);
		bookCoverPanel.add(overviewShadingPanel);
		overviewShadingPanel.setLayout(null);
		
		JLabel overviewTextLabel = new JLabel("<html>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi sollicitudin sed libero in sagittis. Sed venenatis laoreet est, sed sollicitudin sapien porttitor sit amet. Aliquam quis sem sit amet ex molestie tristique tristique nec urna. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Ut vel dapibus.</html>");
		overviewTextLabel.setBorder(new LineBorder(new Color(0, 0, 0, 0), 10));
		overviewTextLabel.setBounds(0, 0, 310, 494);
		overviewTextLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		overviewTextLabel.setVerticalAlignment(SwingConstants.TOP);
		overviewTextLabel.setHorizontalAlignment(SwingConstants.CENTER);
		overviewTextLabel.setForeground(Color.WHITE);
		overviewShadingPanel.add(overviewTextLabel);
		overviewHoverButton.setIcon(new ImageIcon(dimg));
		overviewHoverButton.setBounds(0, 0, 310, 494);
		bookCoverPanel.add(overviewHoverButton);
		bookLoanPanel.add(bookReview);
		bookReview.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(bottomLine);
		scrollPane.setBounds(0, 0, 545, 200);
		bookReview.add(scrollPane);
		
		DefaultListModel<String> reviewListModel = new DefaultListModel<>();
		JList<String> reviewList = new JList<>(reviewListModel);
		reviewList.setBorder(null);
		reviewList.setSelectionForeground(Color.WHITE);
		reviewList.setSelectionBackground(textColor);
		reviewList.setForeground(Color.WHITE);
		reviewList.setBackground(elevation1);
		scrollPane.setViewportView(reviewList);
		reviewList.setCellRenderer(new CommentListRenderer());
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
		bookInfoTitleValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoTitleValue.setForeground(textColorLight);
		bookInfoTitleValue.setFont(info);
		bookInfoTitleValue.setBounds(119, 11, 82, 33);
		panel.add(bookInfoTitleValue);
		
		JLabel bookInfoAuthorLabel = new JLabel("Author:");
		bookInfoAuthorLabel.setForeground(textColor);
		bookInfoAuthorLabel.setFont(systemText);
		bookInfoAuthorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorLabel.setBounds(10, 55, 60, 33);
		panel.add(bookInfoAuthorLabel);
		
		JLabel bookInfoAuthorValue = new JLabel("Value");
		bookInfoAuthorValue.setFont(info);
		bookInfoAuthorValue.setForeground(textColorLight);
		bookInfoAuthorValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoAuthorValue.setBounds(119, 55, 82, 33);
		panel.add(bookInfoAuthorValue);
		
		JLabel bookInfoPublishDateLabel = new JLabel("Publish Date:");
		bookInfoPublishDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateLabel.setForeground(textColor);
		bookInfoPublishDateLabel.setFont(systemText);
		bookInfoPublishDateLabel.setBounds(10, 99, 112, 33);
		panel.add(bookInfoPublishDateLabel);
		
		JLabel bookInfoPublishDateValue = new JLabel("Value");
		bookInfoPublishDateValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoPublishDateValue.setForeground(textColorLight);
		bookInfoPublishDateValue.setFont(info);
		bookInfoPublishDateValue.setBounds(119, 99, 82, 33);
		panel.add(bookInfoPublishDateValue);
		
		JLabel bookInfoPageCountLabel = new JLabel("Page Count:");
		bookInfoPageCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPageCountLabel.setForeground(textColor);
		bookInfoPageCountLabel.setFont(systemText);
		bookInfoPageCountLabel.setBounds(10, 143, 112, 33);
		panel.add(bookInfoPageCountLabel);
		
		JLabel bookInfoPageCountValue = new JLabel("Value");
		bookInfoPageCountValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoPageCountValue.setForeground(textColorLight);
		bookInfoPageCountValue.setFont(info);
		bookInfoPageCountValue.setBounds(119, 143, 82, 33);
		panel.add(bookInfoPageCountValue);
		
		JLabel bookInfoEditionLabel = new JLabel("Edition:");
		bookInfoEditionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoEditionLabel.setForeground(textColor);
		bookInfoEditionLabel.setFont(systemText);
		bookInfoEditionLabel.setBounds(10, 187, 82, 33);
		panel.add(bookInfoEditionLabel);
		
		JLabel bookInfoEditiorValue = new JLabel("Value");
		bookInfoEditiorValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoEditiorValue.setForeground(textColorLight);
		bookInfoEditiorValue.setFont(info);
		bookInfoEditiorValue.setBounds(119, 187, 82, 33);
		panel.add(bookInfoEditiorValue);
		
		JLabel bookInfoISBNLabel = new JLabel("ISBN:");
		bookInfoISBNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoISBNLabel.setForeground(textColor);
		bookInfoISBNLabel.setFont(systemText);
		bookInfoISBNLabel.setBounds(10, 231, 60, 33);
		panel.add(bookInfoISBNLabel);
		
		JLabel bookInfoISBNValue = new JLabel("Value");
		bookInfoISBNValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoISBNValue.setForeground(textColorLight);
		bookInfoISBNValue.setFont(info);
		bookInfoISBNValue.setBounds(119, 231, 82, 33);
		panel.add(bookInfoISBNValue);
		
		JLabel bookInfoGenresLabel = new JLabel("Genres:");
		bookInfoGenresLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoGenresLabel.setForeground(new Color(253, 65, 60));
		bookInfoGenresLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		bookInfoGenresLabel.setBounds(10, 275, 82, 33);
		panel.add(bookInfoGenresLabel);
		
		JLabel bookInfoGenresValue = new JLabel("Value");
		bookInfoGenresValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoGenresValue.setForeground(Color.WHITE);
		bookInfoGenresValue.setFont(new Font("Arial", Font.PLAIN, 15));
		bookInfoGenresValue.setBounds(119, 275, 82, 33);
		panel.add(bookInfoGenresValue);
		
		JLabel bookInfoPublisherLabel = new JLabel("Publisher:");
		bookInfoPublisherLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublisherLabel.setForeground(new Color(253, 65, 60));
		bookInfoPublisherLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
		bookInfoPublisherLabel.setBounds(10, 319, 99, 33);
		panel.add(bookInfoPublisherLabel);
		
		JLabel bookInfoPublisherValue = new JLabel("Value");
		bookInfoPublisherValue.setHorizontalAlignment(SwingConstants.RIGHT);
		bookInfoPublisherValue.setForeground(Color.WHITE);
		bookInfoPublisherValue.setFont(new Font("Arial", Font.PLAIN, 15));
		bookInfoPublisherValue.setBounds(119, 319, 82, 33);
		panel.add(bookInfoPublisherValue);
		
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
		libraryInfoNameLabel.setBounds(10, 11, 60, 33);
		libraryInfoPanel.add(libraryInfoNameLabel);
		
		JLabel libraryInfoNameValue = new JLabel("Value");
		libraryInfoNameValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoNameValue.setForeground(textColorLight);
		libraryInfoNameValue.setFont(info);
		libraryInfoNameValue.setBounds(119, 11, 82, 33);
		libraryInfoPanel.add(libraryInfoNameValue);
		
		JLabel libraryInfoPhoneLabel = new JLabel("Phone:");
		libraryInfoPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoPhoneLabel.setForeground(textColor);
		libraryInfoPhoneLabel.setFont(systemText);
		libraryInfoPhoneLabel.setBounds(10, 55, 60, 33);
		libraryInfoPanel.add(libraryInfoPhoneLabel);
		
		JLabel libraryInfoPhoneValue = new JLabel("Value");
		libraryInfoPhoneValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoPhoneValue.setForeground(textColorLight);
		libraryInfoPhoneValue.setFont(info);
		libraryInfoPhoneValue.setBounds(119, 55, 82, 33);
		libraryInfoPanel.add(libraryInfoPhoneValue);
		
		JLabel libraryInfoAddressLabel = new JLabel("Address");
		libraryInfoAddressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoAddressLabel.setForeground(textColor);
		libraryInfoAddressLabel.setFont(systemText);
		libraryInfoAddressLabel.setBounds(10, 99, 112, 33);
		libraryInfoPanel.add(libraryInfoAddressLabel);
		
		JLabel libraryInfoAddressValue = new JLabel("Value");
		libraryInfoAddressValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoAddressValue.setForeground(textColorLight);
		libraryInfoAddressValue.setFont(info);
		libraryInfoAddressValue.setBounds(119, 99, 82, 33);
		libraryInfoPanel.add(libraryInfoAddressValue);
		
		JButton bookLocationButton = new JButton("Find Location");
		bookLocationButton.setBorder(null);
		bookLocationButton.setForeground(buttonText);
		bookLocationButton.setFont(systemText);
		bookLocationButton.setBounds(1068, 445, 127, 60);
		bookLocationButton.setBackground(elevation1);
		bookLoanPanel.add(bookLocationButton);
		
		JScrollPane librarySelectionScrollPanel = new JScrollPane();
		librarySelectionScrollPanel.setBorder(bottomLine);
		librarySelectionScrollPanel.setBounds(892, 34, 303, 214);
		bookLoanPanel.add(librarySelectionScrollPanel);
		
		DefaultListModel<String> libraryList = new DefaultListModel<>();
		JList<String> librarySelectionList = new JList<>(libraryList);
		//librarySelectionList.setBorder(new LineBorder(componentBorderColor, 2));
		librarySelectionList.setBackground(elevation1);
		librarySelectionScrollPanel.setViewportView(librarySelectionList);
		
		JLabel librarySelectLabel = new JLabel("Choose a library from the below list:");
		librarySelectLabel.setFont(systemText);
		librarySelectLabel.setForeground(Color.WHITE);
		librarySelectLabel.setBounds(892, 9, 303, 24);
		bookLoanPanel.add(librarySelectLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(info);
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(bottomLine);
		textArea.setBackground(elevation1);
		textArea.setBounds(892, 516, 303, 151);
		bookLoanPanel.add(textArea);
		
		JButton commentButton = new JButton("Comment");
		commentButton.setForeground(new Color(254, 188, 44));
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
		bookLoanSidemenuBar.setBounds(16, 158, 125, 89);
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
		bookDonationSidemenuBar.setBounds(16, 258, 125, 89);
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
		profileSidemenuBar.setBounds(16, 626, 125, 89);
		sideBarPanel.add(profileSidemenuBar);
		
		JLabel profileSidemenuLabel = new JLabel("");
		profileSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/userMenuIcon.png")));
		profileSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSidemenuLabel.setBounds(33, 11, 64, 64);
		profileSidemenuBar.add(profileSidemenuLabel);
		
		JPanel bookLoanSidemenuSelection = new JPanel();
		bookLoanSidemenuSelection.setBackground(sideBarSelectionColor);
		bookLoanSidemenuSelection.setBounds(0, 158, 16, 89);
		sideBarPanel.add(bookLoanSidemenuSelection);
		
		JPanel bookDonationSidemenuSelection = new JPanel();
		bookDonationSidemenuSelection.setBackground(sideBarMenuColor);
		bookDonationSidemenuSelection.setBounds(0, 258, 16, 89);
		sideBarPanel.add(bookDonationSidemenuSelection);
		
		JPanel profileSidemenuSelection = new JPanel();
		profileSidemenuSelection.setBackground(sideBarMenuColor);
		profileSidemenuSelection.setBounds(0, 626, 16, 89);
		sideBarPanel.add(profileSidemenuSelection);
		frame.getContentPane().add(contentPanel);
		
		
		bookDonationPanel.setBackground(backgroundColor);
		
		bookDonationPanel.setLayout(null);
		
		DefaultListModel<String> librarySelectionForm = new DefaultListModel<>();
		
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
		JList<String> librarySelectionList_1 = new JList<String>(librarySelectionForm);
		librarySelectionList_1.setBackground(new Color(56, 56, 61));
		libraryDonationScrollPanel.setViewportView(librarySelectionList_1);
		
		JLabel infoText = new JLabel("Choose a library from the below list:");
		infoText.setBounds(413, 11, 303, 24);
		panelMiddle.add(infoText);
		infoText.setForeground(Color.WHITE);
		infoText.setFont(systemText);
		
		JButton bookDonateButton = new JButton("Donate Book");
		bookDonateButton.setBounds(515, 263, 127, 60);
		panelMiddle.add(bookDonateButton);
		bookDonateButton.setForeground(buttonText);
		bookDonateButton.setFont(systemText);
		bookDonateButton.setBorder(bottomLine);
		bookDonateButton.setBackground(backgroundColor);
		
		JLabel bookDonateTitleLabel = new JLabel("Title:");
		bookDonateTitleLabel.setBounds(10, 11, 97, 33);
		panelMiddle.add(bookDonateTitleLabel);
		bookDonateTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateTitleLabel.setForeground(textColor);
		bookDonateTitleLabel.setFont(systemText);
		
		donateFieldTitle = new JTextField();
		donateFieldTitle.setBorder(bottomLineTextField);
		donateFieldTitle.setBounds(171, 19, 183, 25);
		panelMiddle.add(donateFieldTitle);
		donateFieldTitle.setColumns(10);
		
		JLabel bookDonateAuthorLabel = new JLabel("Author:");
		bookDonateAuthorLabel.setBounds(10, 55, 97, 33);
		panelMiddle.add(bookDonateAuthorLabel);
		bookDonateAuthorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateAuthorLabel.setForeground(textColor);
		bookDonateAuthorLabel.setFont(systemText);
		
		donateFieldAuthor = new JTextField();
		donateFieldAuthor.setBorder(bottomLineTextField);
		donateFieldAuthor.setBounds(171, 63, 183, 25);
		panelMiddle.add(donateFieldAuthor);
		donateFieldAuthor.setColumns(10);
		
		JLabel bookDonatePageLabel = new JLabel("Page Count:");
		bookDonatePageLabel.setBounds(10, 143, 118, 33);
		panelMiddle.add(bookDonatePageLabel);
		bookDonatePageLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonatePageLabel.setForeground(textColor);
		bookDonatePageLabel.setFont(systemText);
		
		JLabel bookDonatePublishDateLabel = new JLabel("Publish Date:");
		bookDonatePublishDateLabel.setBounds(10, 99, 118, 33);
		panelMiddle.add(bookDonatePublishDateLabel);
		bookDonatePublishDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonatePublishDateLabel.setForeground(textColor);
		bookDonatePublishDateLabel.setFont(systemText);
		
		donateFieldPublishDate = new JTextField();
		donateFieldPublishDate.setBorder(bottomLineTextField);
		donateFieldPublishDate.setBounds(171, 107, 183, 25);
		panelMiddle.add(donateFieldPublishDate);
		donateFieldPublishDate.setColumns(10);
		
		donateFieldPageCount = new JTextField();
		donateFieldPageCount.setBorder(bottomLineTextField);
		donateFieldPageCount.setBounds(171, 151, 183, 25);
		panelMiddle.add(donateFieldPageCount);
		donateFieldPageCount.setColumns(10);
		
		JLabel bookDonateEditionLabel = new JLabel("Edition:");
		bookDonateEditionLabel.setBounds(10, 187, 97, 33);
		panelMiddle.add(bookDonateEditionLabel);
		bookDonateEditionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateEditionLabel.setForeground(textColor);
		bookDonateEditionLabel.setFont(systemText);
		
		donateFieldEdition = new JTextField();
		donateFieldEdition.setBorder(bottomLineTextField);
		donateFieldEdition.setBounds(171, 195, 183, 25);
		panelMiddle.add(donateFieldEdition);
		donateFieldEdition.setColumns(10);
		
		donateFieldISBN = new JTextField();
		donateFieldISBN.setBorder(bottomLineTextField);
		donateFieldISBN.setBounds(171, 239, 183, 25);
		panelMiddle.add(donateFieldISBN);
		donateFieldISBN.setColumns(10);
		
		JLabel bookDonateISBNLabel = new JLabel("ISBN:");
		bookDonateISBNLabel.setBounds(10, 231, 97, 33);
		panelMiddle.add(bookDonateISBNLabel);
		bookDonateISBNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonateISBNLabel.setForeground(textColor);
		bookDonateISBNLabel.setFont(systemText);
		
		JLabel bookDonatePublisherLabel = new JLabel("Publisher:");
		bookDonatePublisherLabel.setBounds(10, 275, 97, 33);
		panelMiddle.add(bookDonatePublisherLabel);
		bookDonatePublisherLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookDonatePublisherLabel.setForeground(textColor);
		bookDonatePublisherLabel.setFont(systemText);
		
		donateFieldPublisher = new JTextField();
		donateFieldPublisher.setBorder(bottomLineTextField);
		donateFieldPublisher.setBounds(171, 283, 183, 25);
		panelMiddle.add(donateFieldPublisher);
		donateFieldPublisher.setColumns(10);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBackground(backgroundColor);
		contentPanel.add(profilePanel, "profile");
		profilePanel.setLayout(null);
		
		DefaultListModel<Book> takenBooks = new DefaultListModel<>();
		
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
		
		JLabel personLabelEmail = new JLabel("Email:");
		personLabelEmail.setBounds(10, 99, 112, 33);
		personalInfoPanel.add(personLabelEmail);
		personLabelEmail.setHorizontalAlignment(SwingConstants.LEFT);
		personLabelEmail.setForeground(textColor);
		personLabelEmail.setFont(systemText);
		
		JLabel personValueEmail = new JLabel("Value");
		personValueEmail.setBounds(119, 99, 82, 33);
		personalInfoPanel.add(personValueEmail);
		personValueEmail.setHorizontalAlignment(SwingConstants.LEFT);
		personValueEmail.setForeground(textColorLight);
		personValueEmail.setFont(info);
		
		JScrollPane takenBooksScroll = new JScrollPane();
		takenBooksScroll.setBounds(122, 210, 320, 200);
		profilePanelMiddle.add(takenBooksScroll);
		takenBooksScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		takenBooksScroll.setBorder(null);
		JList<Book> takenBooksList = new JList<>(takenBooks);
		takenBooksList.setSelectionBackground(sideBarSelectionColor);
		takenBooksScroll.setViewportView(takenBooksList);
		takenBooksList.setVisibleRowCount(1);
		takenBooksList.setCellRenderer(new BookListRenderer());
		takenBooksList.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		takenBooksList.setBackground(elevation1);
		
		JButton btnReturnBook = new JButton("Return Book");
		btnReturnBook.setBounds(218, 421, 127, 60);
		profilePanelMiddle.add(btnReturnBook);
		btnReturnBook.setForeground(buttonText);
		btnReturnBook.setFont(systemText);
		btnReturnBook.setBorder(bottomLine);
		btnReturnBook.setBackground(backgroundColor);
		
		JScrollPane preCommetsScrollPane = new JScrollPane();
		preCommetsScrollPane.setBounds(10, 513, 545, 167);
		preCommetsScrollPane.setBorder(bottomLine);
		profilePanelMiddle.add(preCommetsScrollPane);
		
		DefaultListModel<String> takenBooksModel = new DefaultListModel<>();
		JList<String> reviewList_1 = new JList<String>(takenBooksModel);
		reviewList_1.setSelectionForeground(Color.WHITE);
		reviewList_1.setSelectionBackground(textColor);
		reviewList_1.setForeground(Color.WHITE);
		reviewList_1.setBackground(new Color(56, 56, 61));
		reviewList_1.setCellRenderer(new CommentListRenderer());
		preCommetsScrollPane.setViewportView(reviewList_1);
		
		takenBooksModel.addElement("A tensely written, shocking book that will hold readers on the edge of their seats to the very last page.");
		takenBooksModel.addElement("Both compelling and addictive, The Favorite Daughter is a roller coaster of a ride that will have you twisting and turning.");
		takenBooksModel.addElement("Will hit you right in the heart.");
		
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
		takenBooks.addElement(new Book(3, "1984", goodbye));
		takenBooks.addElement(new Book(3, "1985", colorful));
		takenBooks.addElement(new Book(3, "1986", murder));
		takenBooks.addElement(new Book(3, "1987", umbrella));
		takenBooks.addElement(new Book(3, "1988", kissing));
		takenBooks.addElement(new Book(3, "1989", goodbye));
		takenBooks.addElement(new Book(3, "1990", colorful));
		takenBooks.addElement(new Book(3, "1991", murder));
		takenBooks.addElement(new Book(3, "1992", umbrella));
		
		JPanel[] selectionList = {bookLoanSidemenuSelection, bookDonationSidemenuSelection, profileSidemenuSelection};
		JPanel[] barList = {bookLoanSidemenuBar, bookDonationSidemenuBar, profileSidemenuBar};
		
		MouseAdapter select = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("Clicked");
				System.out.println(e.getSource().toString());
				if(e.getSource() == bookLoanSidemenuBar) {
					System.out.println("deneme");
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
				for(int i = 0;i<3;i++) {
					//System.out.println(selectionList[i]);
					if(e.getSource() != barList[i]) {
						System.out.println("Not equal" + selectionList[i]);
						selectionList[i].setBackground(sideBarMenuColor);
					}
				}
			}
		};
		bookLoanSidemenuBar.addMouseListener(select);
		bookDonationSidemenuBar.addMouseListener(select);
		profileSidemenuBar.addMouseListener(select);
	}
}

class Book{
	private int bookId;
	private String bookName;
	private ImageIcon bookCover;
	
	public Book(int bookId, String bookName, Image original) {
		this.bookId = bookId;
		this.bookName = bookName;
		//Image original = new ImageIcon(getClass().getResource(path)).getImage();
		Image dimg = original.getScaledInstance((int) (150*0.62), 150, Image.SCALE_SMOOTH);
		//this.bookCover = new ImageIcon(getClass().getResource(path));
		this.bookCover = new ImageIcon(dimg);
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public ImageIcon getBookCover() {
		return bookCover;
	}
	
}

class BookListRenderer extends DefaultListCellRenderer {
	//private Book book;
	
	/*public MarioListRenderer(Book book) {
		this.book = book;
	}*/
	
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
        label.setText(((Book) value).getBookName());
        label.setIcon(((Book) value).getBookCover());
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.BOTTOM);
        list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));;
        label.setBorder(new LineBorder(new Color(39, 43, 47), 4));
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }
}

class CommentListRenderer extends DefaultListCellRenderer {
	Color commentLine = new Color(253, 65, 60);
	MatteBorder bottomLine = new MatteBorder(0, 0, 2, 0, (Color) commentLine);
	//private Book book;
	
	/*public MarioListRenderer(Book book) {
		this.book = book;
	}*/
	
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
        //label.setIcon(((Book) value).getBookCover());
        label.setHorizontalTextPosition(JLabel.LEFT);
        //label.setVerticalTextPosition(JLabel.BOTTOM);
        //list.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));;
        label.setBorder(bottomLine);
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }
}