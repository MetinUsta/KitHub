package userInterface;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import java.awt.CardLayout;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Window {

	private JFrame frame;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
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
		contentPanel.setLayout(new CardLayout(0, 0));
		
		JPanel bookLoanPanel = new JPanel();
		bookLoanPanel.setBackground(backgroundColor);
		contentPanel.add(bookLoanPanel, "name_92813382809000");
		
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
		bookSuggestionsScrollPanel.setBorder(null);
		bookSuggestionsScrollPanel.setBounds(10, 516, 317, 200);
		
		JPanel bookReview = new JPanel();
		bookReview.setBorder(new LineBorder(componentBorderColor, 2));
		bookReview.setBounds(337, 516, 545, 200);
		bookReview.setBackground(elevation1);
		
		JPanel bookCover = new JPanel();
		bookCover.setBounds(338, 11, 310, 494);
		bookCover.setBackground(Color.GRAY);
		
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
		
		JList<String> bookSuggest = new JList<>();
		bookSuggest.setBorder(new LineBorder(componentBorderColor, 2));
		bookSuggest.setBackground(elevation1);
		bookSuggestionsScrollPanel.setViewportView(bookSuggest);
		
		bookListScrollPane.setViewportView(list);
		bookLoanPanel.setLayout(null);
		bookLoanPanel.add(searchPanel);
		bookLoanPanel.add(bookListScrollPane);
		bookLoanPanel.add(bookLoanButton);
		bookLoanPanel.add(bookSuggestionsScrollPanel);
		bookLoanPanel.add(bookCover);
		
		JPanel overviewShadingPanel = new JPanel();
		JButton overviewHoverButton = new JButton("");
		overviewHoverButton.setDisabledIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Images/transparentBackgroundColorButton.png")));
		
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
		Image original = new ImageIcon(Window.class.getResource("/AppIcons/kissingCouple.png")).getImage();
		Image dimg = original.getScaledInstance(bookCover.getWidth(), bookCover.getHeight(), Image.SCALE_SMOOTH);
		bookCover.setLayout(null);
		
		overviewShadingPanel.setVisible(false);
		overviewShadingPanel.setBackground(new Color(0, 0, 0, 102));
		overviewShadingPanel.setBounds(0, 0, 310, 494);
		bookCover.add(overviewShadingPanel);
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
		bookCover.add(overviewHoverButton);
		bookLoanPanel.add(bookReview);
		
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
		bookInfoTitleValue.setBounds(80, 11, 82, 33);
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
		bookInfoAuthorValue.setBounds(80, 55, 82, 33);
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
		bookInfoEditiorValue.setBounds(80, 187, 82, 33);
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
		bookInfoISBNValue.setBounds(80, 231, 82, 33);
		panel.add(bookInfoISBNValue);
		
		JPanel libraryInfoPanel = new JPanel();
		libraryInfoPanel.setBorder(new LineBorder(componentBorderColor, 2));
		libraryInfoPanel.setBackground(elevation1);
		libraryInfoPanel.setBounds(892, 259, 303, 163);
		bookLoanPanel.add(libraryInfoPanel);
		
		JButton bookLocationButton = new JButton("Take Book");
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
		
		JList<String> librarySelectionList = new JList<>();
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
		bookLoanSidemenuBar.setBackground(sideBarMenuColor);
		bookLoanSidemenuBar.setBounds(10, 158, 131, 89);
		sideBarPanel.add(bookLoanSidemenuBar);
		bookLoanSidemenuBar.setLayout(null);
		
		JLabel bookLoanSidemenuLabel = new JLabel("");
		bookLoanSidemenuLabel.setBounds(33, 11, 64, 64);
		bookLoanSidemenuBar.add(bookLoanSidemenuLabel);
		bookLoanSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/bookIcon.png")));
		bookLoanSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel bookDonationSidemenuBar = new JPanel();
		bookDonationSidemenuBar.setLayout(null);
		bookDonationSidemenuBar.setBackground(sideBarMenuColor);
		bookDonationSidemenuBar.setBounds(10, 258, 131, 89);
		sideBarPanel.add(bookDonationSidemenuBar);
		
		JLabel bookDonationSidemenuLabel = new JLabel("");
		bookDonationSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/bookDonationIcon.png")));
		bookDonationSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		bookDonationSidemenuLabel.setBounds(33, 11, 64, 64);
		bookDonationSidemenuBar.add(bookDonationSidemenuLabel);
		
		JPanel profileSidemenuBar = new JPanel();
		profileSidemenuBar.setLayout(null);
		profileSidemenuBar.setBackground(sideBarMenuColor);
		profileSidemenuBar.setBounds(10, 626, 131, 89);
		sideBarPanel.add(profileSidemenuBar);
		
		JLabel profileSidemenuLabel = new JLabel("");
		profileSidemenuLabel.setIcon(new ImageIcon(Window.class.getResource("/SystemAssets/Icons/userMenuIcon.png")));
		profileSidemenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		profileSidemenuLabel.setBounds(33, 11, 64, 64);
		profileSidemenuBar.add(profileSidemenuLabel);
		
		JPanel bookLoanSidemenuSelection = new JPanel();
		bookLoanSidemenuSelection.setBackground(sideBarSelectionColor);
		bookLoanSidemenuSelection.setBounds(0, 158, 10, 89);
		sideBarPanel.add(bookLoanSidemenuSelection);
		
		JPanel bookDonationSidemenuSelection = new JPanel();
		bookDonationSidemenuSelection.setBackground(sideBarSelectionColor);
		bookDonationSidemenuSelection.setBounds(0, 258, 10, 89);
		sideBarPanel.add(bookDonationSidemenuSelection);
		
		JPanel profileSidemenuSelection = new JPanel();
		profileSidemenuSelection.setBackground(sideBarSelectionColor);
		profileSidemenuSelection.setBounds(0, 626, 10, 89);
		sideBarPanel.add(profileSidemenuSelection);
		frame.getContentPane().add(contentPanel);
	}
}