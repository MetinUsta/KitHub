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

public class Window {
	
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_2;
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
		Color sideBarColor = new Color(22, 22, 24);
		Color sideBarMenuColor = new Color(49, 49, 53);
		Color sideBarSelectionColor = new Color(8, 168, 144);
		Color componentBorderColor = new Color(95, 95, 100);
		Color elevation1 = new Color(56, 56, 61);
		Color backgroundColor = new Color(37, 37, 43);
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
		
		DefaultListModel<String> bookList = new DefaultListModel<>();
		JList<String> list = new JList<>(bookList);
		list.setSelectionBackground(Color.YELLOW);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new LineBorder(componentBorderColor, 2));
		list.setBackground(elevation1);
		
		JScrollPane bookSuggestionsScrollPanel = new JScrollPane();
		bookSuggestionsScrollPanel.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		bookSuggestionsScrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		bookSuggestionsScrollPanel.setBorder(null);
		bookSuggestionsScrollPanel.setBounds(10, 516, 317, 200);
		
		JPanel bookReview = new JPanel();
		bookReview.setBorder(new LineBorder(componentBorderColor, 2));
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
		bookLoanButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookLoanButton.setForeground(Color.WHITE);
		bookLoanButton.setBackground(elevation1);
		bookLoanButton.setBounds(892, 445, 127, 60);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorder(null);
		searchPanel.add(menuBar);
		
		
		
		JTextField searchBar = new JTextField();
		searchBar.setBorder(new LineBorder(Color.WHITE, 4));
		searchBar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		menuBar.add(searchBar);
		searchBar.setColumns(10);
		
		JComboBox<String> selectGenreList = new JComboBox<>();
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
		
		Image original1 = new ImageIcon(getClass().getResource("/bookCovers/kopya.png")).getImage();
		bookSuggestList.addElement(new Book(1, "calikusu", original1));
		bookSuggestList.addElement(new Book(1, "calikusu", original1));
		bookSuggestList.addElement(new Book(1, "calikusu", original1));
		bookSuggestList.addElement(new Book(1, "calikusu", original1));
		bookSuggestList.addElement(new Book(1, "calikusu", original1));
		
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
		JButton overviewHoverButton = new JButton("");
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
		scrollPane.setBounds(0, 0, 545, 200);
		bookReview.add(scrollPane);
		
		DefaultListModel<String> reviewListModel = new DefaultListModel<>();
		JList<String> reviewList = new JList<>(reviewListModel);
		reviewList.setSelectionForeground(Color.WHITE);
		reviewList.setSelectionBackground(Color.DARK_GRAY);
		reviewList.setForeground(Color.WHITE);
		reviewList.setBackground(elevation1);
		scrollPane.setViewportView(reviewList);
		reviewListModel.addElement("AAA");
		reviewListModel.addElement("AAA");
		reviewListModel.addElement("AAA");
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(componentBorderColor, 2));
		panel.setBackground(elevation1);
		panel.setBounds(658, 11, 224, 494);
		bookLoanPanel.add(panel);
		panel.setLayout(null);
		
		JLabel bookInfoTitleLabel = new JLabel("Title:");
		bookInfoTitleLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookInfoTitleLabel.setBounds(10, 11, 60, 33);
		panel.add(bookInfoTitleLabel);
		
		JLabel bookInfoTitleValue = new JLabel("Value");
		bookInfoTitleValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleValue.setForeground(Color.WHITE);
		bookInfoTitleValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookInfoTitleValue.setBounds(119, 11, 82, 33);
		panel.add(bookInfoTitleValue);
		
		JLabel bookInfoAuthorLabel = new JLabel("Author:");
		bookInfoAuthorLabel.setForeground(Color.LIGHT_GRAY);
		bookInfoAuthorLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookInfoAuthorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorLabel.setBounds(10, 55, 60, 33);
		panel.add(bookInfoAuthorLabel);
		
		JLabel bookInfoAuthorValue = new JLabel("Value");
		bookInfoAuthorValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookInfoAuthorValue.setForeground(Color.WHITE);
		bookInfoAuthorValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorValue.setBounds(119, 55, 82, 33);
		panel.add(bookInfoAuthorValue);
		
		JLabel bookInfoPublishDateLabel = new JLabel("Publish Date:");
		bookInfoPublishDateLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateLabel.setForeground(Color.LIGHT_GRAY);
		bookInfoPublishDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookInfoPublishDateLabel.setBounds(10, 99, 112, 33);
		panel.add(bookInfoPublishDateLabel);
		
		JLabel bookInfoPublishDateValue = new JLabel("Value");
		bookInfoPublishDateValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateValue.setForeground(Color.WHITE);
		bookInfoPublishDateValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookInfoPublishDateValue.setBounds(119, 99, 82, 33);
		panel.add(bookInfoPublishDateValue);
		
		JLabel bookInfoPageCountLabel = new JLabel("Page Count:");
		bookInfoPageCountLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPageCountLabel.setForeground(Color.LIGHT_GRAY);
		bookInfoPageCountLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookInfoPageCountLabel.setBounds(10, 143, 112, 33);
		panel.add(bookInfoPageCountLabel);
		
		JLabel bookInfoPageCountValue = new JLabel("Value");
		bookInfoPageCountValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPageCountValue.setForeground(Color.WHITE);
		bookInfoPageCountValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookInfoPageCountValue.setBounds(119, 143, 82, 33);
		panel.add(bookInfoPageCountValue);
		
		JLabel bookInfoEditionLabel = new JLabel("Edition:");
		bookInfoEditionLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoEditionLabel.setForeground(Color.LIGHT_GRAY);
		bookInfoEditionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookInfoEditionLabel.setBounds(10, 187, 60, 33);
		panel.add(bookInfoEditionLabel);
		
		JLabel bookInfoEditiorValue = new JLabel("Value");
		bookInfoEditiorValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoEditiorValue.setForeground(Color.WHITE);
		bookInfoEditiorValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookInfoEditiorValue.setBounds(119, 187, 82, 33);
		panel.add(bookInfoEditiorValue);
		
		JLabel bookInfoISBNLabel = new JLabel("ISBN:");
		bookInfoISBNLabel.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoISBNLabel.setForeground(Color.LIGHT_GRAY);
		bookInfoISBNLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookInfoISBNLabel.setBounds(10, 231, 60, 33);
		panel.add(bookInfoISBNLabel);
		
		JLabel bookInfoISBNValue = new JLabel("Value");
		bookInfoISBNValue.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoISBNValue.setForeground(Color.WHITE);
		bookInfoISBNValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		bookInfoISBNValue.setBounds(119, 231, 82, 33);
		panel.add(bookInfoISBNValue);
		
		JPanel libraryInfoPanel = new JPanel();
		libraryInfoPanel.setBorder(new LineBorder(componentBorderColor, 2));
		libraryInfoPanel.setBackground(elevation1);
		libraryInfoPanel.setBounds(892, 259, 303, 163);
		bookLoanPanel.add(libraryInfoPanel);
		libraryInfoPanel.setLayout(null);
		
		JLabel libraryInfoNameLabel = new JLabel("Name:");
		libraryInfoNameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoNameLabel.setForeground(Color.LIGHT_GRAY);
		libraryInfoNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		libraryInfoNameLabel.setBounds(10, 11, 60, 33);
		libraryInfoPanel.add(libraryInfoNameLabel);
		
		JLabel libraryInfoNameValue = new JLabel("Value");
		libraryInfoNameValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoNameValue.setForeground(Color.WHITE);
		libraryInfoNameValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		libraryInfoNameValue.setBounds(119, 11, 82, 33);
		libraryInfoPanel.add(libraryInfoNameValue);
		
		JLabel libraryInfoPhoneLabel = new JLabel("Phone:");
		libraryInfoPhoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoPhoneLabel.setForeground(Color.LIGHT_GRAY);
		libraryInfoPhoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		libraryInfoPhoneLabel.setBounds(10, 55, 60, 33);
		libraryInfoPanel.add(libraryInfoPhoneLabel);
		
		JLabel libraryInfoPhoneValue = new JLabel("Value");
		libraryInfoPhoneValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoPhoneValue.setForeground(Color.WHITE);
		libraryInfoPhoneValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		libraryInfoPhoneValue.setBounds(119, 55, 82, 33);
		libraryInfoPanel.add(libraryInfoPhoneValue);
		
		JLabel libraryInfoAddressLabel = new JLabel("Address");
		libraryInfoAddressLabel.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoAddressLabel.setForeground(Color.LIGHT_GRAY);
		libraryInfoAddressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		libraryInfoAddressLabel.setBounds(10, 99, 112, 33);
		libraryInfoPanel.add(libraryInfoAddressLabel);
		
		JLabel libraryInfoAddressValue = new JLabel("Value");
		libraryInfoAddressValue.setHorizontalAlignment(SwingConstants.LEFT);
		libraryInfoAddressValue.setForeground(Color.WHITE);
		libraryInfoAddressValue.setFont(new Font("Tahoma", Font.PLAIN, 15));
		libraryInfoAddressValue.setBounds(119, 99, 82, 33);
		libraryInfoPanel.add(libraryInfoAddressValue);
		
		JButton bookLocationButton = new JButton("Find Location");
		bookLocationButton.setBorder(null);
		bookLocationButton.setForeground(Color.WHITE);
		bookLocationButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookLocationButton.setBounds(1068, 445, 127, 60);
		bookLocationButton.setBackground(elevation1);
		bookLoanPanel.add(bookLocationButton);
		
		JScrollPane librarySelectionScrollPanel = new JScrollPane();
		librarySelectionScrollPanel.setBorder(null);
		librarySelectionScrollPanel.setBounds(892, 34, 303, 214);
		bookLoanPanel.add(librarySelectionScrollPanel);
		
		DefaultListModel<String> libraryList = new DefaultListModel<>();
		JList<String> librarySelectionList = new JList<>(libraryList);
		librarySelectionList.setBorder(new LineBorder(componentBorderColor, 2));
		librarySelectionList.setBackground(elevation1);
		librarySelectionScrollPanel.setViewportView(librarySelectionList);
		
		JLabel librarySelectLabel = new JLabel("Choose a library from the below list:");
		librarySelectLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		librarySelectLabel.setForeground(Color.WHITE);
		librarySelectLabel.setBounds(892, 9, 303, 24);
		bookLoanPanel.add(librarySelectLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setForeground(Color.WHITE);
		textArea.setBorder(new LineBorder(componentBorderColor, 2));
		textArea.setBackground(elevation1);
		textArea.setBounds(892, 516, 303, 200);
		bookLoanPanel.add(textArea);
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
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(backgroundColor);
		panel_1.setBounds(247, 204, 726, 330);
		bookDonationPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane librarySelectionScrollPanel_1 = new JScrollPane();
		librarySelectionScrollPanel_1.setBounds(413, 38, 303, 214);
		panel_1.add(librarySelectionScrollPanel_1);
		librarySelectionScrollPanel_1.setBorder(null);
		JList<String> librarySelectionList_1 = new JList<String>(librarySelectionForm);
		librarySelectionList_1.setBackground(new Color(56, 56, 61));
		librarySelectionScrollPanel_1.setViewportView(librarySelectionList_1);
		
		JLabel librarySelectLabel_1 = new JLabel("Choose a library from the below list:");
		librarySelectLabel_1.setBounds(413, 11, 303, 24);
		panel_1.add(librarySelectLabel_1);
		librarySelectLabel_1.setForeground(Color.WHITE);
		librarySelectLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JButton bookLoanButton_1 = new JButton("Donate Book");
		bookLoanButton_1.setBounds(515, 263, 127, 60);
		panel_1.add(bookLoanButton_1);
		bookLoanButton_1.setForeground(Color.WHITE);
		bookLoanButton_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		bookLoanButton_1.setBorder(null);
		bookLoanButton_1.setBackground(new Color(56, 56, 61));
		
		JLabel bookInfoTitleLabel_1 = new JLabel("Title:");
		bookInfoTitleLabel_1.setBounds(10, 11, 97, 33);
		panel_1.add(bookInfoTitleLabel_1);
		bookInfoTitleLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField = new JTextField();
		textField.setBounds(171, 19, 183, 25);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel bookInfoTitleLabel_1_1 = new JLabel("Author:");
		bookInfoTitleLabel_1_1.setBounds(10, 55, 97, 33);
		panel_1.add(bookInfoTitleLabel_1_1);
		bookInfoTitleLabel_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1_1.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField_1 = new JTextField();
		textField_1.setBounds(171, 63, 183, 25);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel bookInfoTitleLabel_1_1_1_1 = new JLabel("Page Count:");
		bookInfoTitleLabel_1_1_1_1.setBounds(10, 143, 97, 33);
		panel_1.add(bookInfoTitleLabel_1_1_1_1);
		bookInfoTitleLabel_1_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1_1_1_1.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel bookInfoTitleLabel_1_1_1 = new JLabel("Publish Date:");
		bookInfoTitleLabel_1_1_1.setBounds(10, 99, 97, 33);
		panel_1.add(bookInfoTitleLabel_1_1_1);
		bookInfoTitleLabel_1_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1_1_1.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField_2 = new JTextField();
		textField_2.setBounds(171, 107, 183, 25);
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(171, 151, 183, 25);
		panel_1.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel bookInfoTitleLabel_1_1_1_2 = new JLabel("Edition:");
		bookInfoTitleLabel_1_1_1_2.setBounds(10, 187, 97, 33);
		panel_1.add(bookInfoTitleLabel_1_1_1_2);
		bookInfoTitleLabel_1_1_1_2.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1_1_1_2.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField_4 = new JTextField();
		textField_4.setBounds(171, 195, 183, 25);
		panel_1.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(171, 239, 183, 25);
		panel_1.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel bookInfoTitleLabel_1_1_1_3 = new JLabel("ISBN");
		bookInfoTitleLabel_1_1_1_3.setBounds(10, 231, 97, 33);
		panel_1.add(bookInfoTitleLabel_1_1_1_3);
		bookInfoTitleLabel_1_1_1_3.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1_1_1_3.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel bookInfoTitleLabel_1_1_1_4 = new JLabel("Publisher");
		bookInfoTitleLabel_1_1_1_4.setBounds(10, 275, 97, 33);
		panel_1.add(bookInfoTitleLabel_1_1_1_4);
		bookInfoTitleLabel_1_1_1_4.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_1_1_1_4.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_1_1_1_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		textField_6 = new JTextField();
		textField_6.setBounds(171, 283, 183, 25);
		panel_1.add(textField_6);
		textField_6.setColumns(10);
		
		JPanel profilePanel = new JPanel();
		profilePanel.setBackground(backgroundColor);
		contentPanel.add(profilePanel, "profile");
		profilePanel.setLayout(null);
		
		DefaultListModel<Book> takenBooks = new DefaultListModel<>();
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(elevation1);
		panel_3.setBounds(279, 25, 565, 703);
		profilePanel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(122, 35, 320, 152);
		panel_3.add(panel_2);
		panel_2.setBackground(elevation1);
		panel_2.setLayout(null);
		
		JLabel bookInfoTitleLabel_2 = new JLabel("Name:");
		bookInfoTitleLabel_2.setBounds(10, 11, 99, 33);
		panel_2.add(bookInfoTitleLabel_2);
		bookInfoTitleLabel_2.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleLabel_2.setForeground(Color.LIGHT_GRAY);
		bookInfoTitleLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel bookInfoTitleValue_1 = new JLabel("Value");
		bookInfoTitleValue_1.setBounds(119, 11, 82, 33);
		panel_2.add(bookInfoTitleValue_1);
		bookInfoTitleValue_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoTitleValue_1.setForeground(Color.WHITE);
		bookInfoTitleValue_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel bookInfoAuthorLabel_1 = new JLabel("Last Name:");
		bookInfoAuthorLabel_1.setBounds(10, 55, 112, 33);
		panel_2.add(bookInfoAuthorLabel_1);
		bookInfoAuthorLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorLabel_1.setForeground(Color.LIGHT_GRAY);
		bookInfoAuthorLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel bookInfoAuthorValue_1 = new JLabel("Value");
		bookInfoAuthorValue_1.setBounds(119, 55, 82, 33);
		panel_2.add(bookInfoAuthorValue_1);
		bookInfoAuthorValue_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoAuthorValue_1.setForeground(Color.WHITE);
		bookInfoAuthorValue_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel bookInfoPublishDateLabel_1 = new JLabel("Email:");
		bookInfoPublishDateLabel_1.setBounds(10, 99, 112, 33);
		panel_2.add(bookInfoPublishDateLabel_1);
		bookInfoPublishDateLabel_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateLabel_1.setForeground(Color.LIGHT_GRAY);
		bookInfoPublishDateLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel bookInfoPublishDateValue_1 = new JLabel("Value");
		bookInfoPublishDateValue_1.setBounds(119, 99, 82, 33);
		panel_2.add(bookInfoPublishDateValue_1);
		bookInfoPublishDateValue_1.setHorizontalAlignment(SwingConstants.LEFT);
		bookInfoPublishDateValue_1.setForeground(Color.WHITE);
		bookInfoPublishDateValue_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JScrollPane takenBooksScroll = new JScrollPane();
		takenBooksScroll.setBounds(122, 210, 320, 200);
		panel_3.add(takenBooksScroll);
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
		panel_3.add(btnReturnBook);
		btnReturnBook.setForeground(Color.WHITE);
		btnReturnBook.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnReturnBook.setBorder(null);
		btnReturnBook.setBackground(new Color(42, 42, 54));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 513, 545, 179);
		panel_3.add(scrollPane_1);
		
		DefaultListModel<String> takenBooksModel = new DefaultListModel<>();
		JList<String> reviewList_1 = new JList<String>(takenBooksModel);
		reviewList_1.setSelectionForeground(Color.WHITE);
		reviewList_1.setSelectionBackground(Color.DARK_GRAY);
		reviewList_1.setForeground(Color.WHITE);
		reviewList_1.setBackground(new Color(56, 56, 61));
		scrollPane_1.setViewportView(reviewList_1);
		
		JLabel lblNewLabel = new JLabel("Personal Info:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(122, 11, 320, 23);
		panel_3.add(lblNewLabel);
		
		JLabel lblTakenBooks = new JLabel("Taken Books:");
		lblTakenBooks.setForeground(Color.WHITE);
		lblTakenBooks.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTakenBooks.setBounds(122, 187, 320, 23);
		panel_3.add(lblTakenBooks);
		
		JLabel lblPreviousComments = new JLabel("Previous Comments:");
		lblPreviousComments.setForeground(Color.WHITE);
		lblPreviousComments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPreviousComments.setBounds(10, 481, 320, 23);
		panel_3.add(lblPreviousComments);
		takenBooks.addElement(new Book(3, "1984", original1));
		takenBooks.addElement(new Book(3, "1985", original1));
		takenBooks.addElement(new Book(3, "1986", original1));
		takenBooks.addElement(new Book(3, "1987", original1));
		takenBooks.addElement(new Book(3, "1988", original1));
		takenBooks.addElement(new Book(3, "1989", original1));
		takenBooks.addElement(new Book(3, "1990", original1));
		takenBooks.addElement(new Book(3, "1991", original1));
		takenBooks.addElement(new Book(3, "1992", original1));
		
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
        label.setBorder(new LineBorder(new Color(56, 56, 61), 4));
        label.setFont(font);
        label.setForeground(Color.white);
        return label;
    }
}