package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginPage {

	private JFrame frame;
	private final JLabel lblNewLabel = new JLabel("");
	private JTextField emailLoginInput;
	private JTextField NameTextField;
	private JTextField LastNameTextField;
	private JTextField EmailTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldLogin;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(245, 245, 245));
		frame.setTitle("myLibrary");
		frame.setBounds(100, 100, 800, 500);
		frame.setUndecorated(true);
		
		Image icon = new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/icon.png")).getImage();
		frame.setIconImage(icon);

		FrameDragListener frameDragListener = new FrameDragListener(frame);
		frame.addMouseListener(frameDragListener);
		frame.addMouseMotionListener(frameDragListener);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel_3_1_1 = new JLabel("details and start reading!");
		lblNewLabel_3_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1_1.setForeground(new Color(232, 232, 232));
		lblNewLabel_3_1_1.setFont(new Font("Cooper Black", Font.PLAIN, 21));
		lblNewLabel_3_1_1.setBounds(0, 302, 284, 77);
		frame.getContentPane().add(lblNewLabel_3_1_1);

		JLabel lblNewLabel_3_1 = new JLabel("Enter your personal");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(new Color(232, 232, 232));
		lblNewLabel_3_1.setFont(new Font("Cooper Black", Font.PLAIN, 23));
		lblNewLabel_3_1.setBounds(10, 274, 261, 77);
		frame.getContentPane().add(lblNewLabel_3_1);
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/library.png")));
		lblNewLabel_4.setBounds(80, 166, 120, 105);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_3 = new JLabel("Welcome back");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Cooper Black", Font.PLAIN, 36));
		lblNewLabel_3.setForeground(new Color(232, 232, 232));
		lblNewLabel_3.setBounds(10, 63, 261, 146);
		frame.getContentPane().add(lblNewLabel_3);
		lblNewLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGrid.png")));
		lblNewLabel.setBounds(0, 0, 284, 500);
		frame.getContentPane().add(lblNewLabel);

		JPanel panel = new JPanel();
		panel.setBounds(283, 0, 517, 500);
		frame.getContentPane().add(panel);
		CardLayout cards = new CardLayout();
		panel.setLayout(cards);
		// cards.show(panel, "signupPanel");

		JPanel signinPanel = new JPanel();
		signinPanel.setBounds(0, 0, 517, 500);
		panel.add(signinPanel, "signinPanel");
		signinPanel.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("Sign in to myLibrary");
		lblNewLabel_1.setBounds(94, 85, 328, 77);
		signinPanel.add(lblNewLabel_1);
		lblNewLabel_1.setForeground(new Color(6, 214, 160));
		lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_5 = new JLabel("Enter your details:");
		lblNewLabel_5.setBounds(165, 152, 181, 43);
		signinPanel.add(lblNewLabel_5);
		lblNewLabel_5.setForeground(new Color(153, 153, 153));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);

		emailLoginInput = new JTextField();
		emailLoginInput.setBounds(137, 206, 249, 32);
		signinPanel.add(emailLoginInput);
		emailLoginInput.setBackground(new Color(244, 244, 244));
		emailLoginInput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailLoginInput.setForeground(Color.GRAY);
		emailLoginInput.setColumns(10);

		JLabel lblNewLabel_5_1 = new JLabel("Don't have an account?");
		lblNewLabel_5_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(panel, "signupPanel");
			}
		});
		lblNewLabel_5_1.setBounds(137, 342, 249, 43);
		signinPanel.add(lblNewLabel_5_1);
		lblNewLabel_5_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(new Color(102, 102, 102));
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 17));

		JLabel lblNewLabel_7 = new JLabel("SIGN IN");
		lblNewLabel_7.setBounds(174, 302, 172, 43);
		signinPanel.add(lblNewLabel_7);
		lblNewLabel_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(new Color(6, 214, 160));

		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setBackground(new Color(244, 244, 244));
		passwordFieldLogin.setForeground(Color.GRAY);
		passwordFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldLogin.setBounds(137, 249, 249, 32);
		signinPanel.add(passwordFieldLogin);

		JPanel signupPanel = new JPanel();
		panel.add(signupPanel, "signupPanel");
		signupPanel.setLayout(null);

		@SuppressWarnings("unused")
		TextPrompt emailLoginPlaceholder = new TextPrompt("Email ", emailLoginInput);
		@SuppressWarnings("unused")
		TextPrompt passwordLoginPlaceholder = new TextPrompt("Password ", passwordFieldLogin);
		
		JLabel lblNewLabel_7_1_1 = new JLabel("");
		lblNewLabel_7_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_7_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_7_1_1.setOpaque(true);
		lblNewLabel_7_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1.setForeground(Color.WHITE);
		lblNewLabel_7_1_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_7_1_1.setBackground(new Color(14, 42, 71));
		lblNewLabel_7_1_1.setBounds(462, 25, 17, 6);
		signinPanel.add(lblNewLabel_7_1_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("X");
		lblNewLabel_8_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_8_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1.setForeground(new Color(14, 42, 71));
		lblNewLabel_8_1.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel_8_1.setBounds(485, 11, 22, 27);
		signinPanel.add(lblNewLabel_8_1);

		NameTextField = new JTextField();
		NameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		NameTextField.setForeground(Color.GRAY);
		NameTextField.setBounds(122, 175, 217, 33);
		signupPanel.add(NameTextField);
		NameTextField.setColumns(10);

		LastNameTextField = new JTextField();
		LastNameTextField.setColumns(10);
		LastNameTextField.setBounds(122, 219, 217, 33);
		signupPanel.add(LastNameTextField);

		EmailTextField = new JTextField();
		EmailTextField.setColumns(10);
		EmailTextField.setBounds(122, 263, 217, 33);
		signupPanel.add(EmailTextField);

		passwordField = new JPasswordField();
		passwordField.setBounds(122, 307, 217, 33);
		signupPanel.add(passwordField);

		JLabel lblNewLabel_7_2 = new JLabel("SIGN UP");
		lblNewLabel_7_2.setOpaque(true);
		lblNewLabel_7_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_2.setForeground(Color.WHITE);
		lblNewLabel_7_2.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_7_2.setBackground(new Color(6, 214, 160));
		lblNewLabel_7_2.setBounds(146, 351, 172, 43);
		signupPanel.add(lblNewLabel_7_2);

		LastNameTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		LastNameTextField.setForeground(Color.GRAY);

		EmailTextField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		EmailTextField.setForeground(Color.GRAY);

		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordField.setForeground(Color.GRAY);
		@SuppressWarnings("unused")
		TextPrompt namePlaceholder = new TextPrompt("Name ", NameTextField);
		@SuppressWarnings("unused")
		TextPrompt lastNamePlaceholder = new TextPrompt("Last Name ", LastNameTextField);
		@SuppressWarnings("unused")
		TextPrompt emailPlaceholder = new TextPrompt("Email ", EmailTextField);
		@SuppressWarnings("unused")
		TextPrompt passwordPlaceholder = new TextPrompt("Password ", passwordField);

		JLabel lblNewLabel_1_1 = new JLabel("Sign up to myLibrary");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setForeground(new Color(6, 214, 160));
		lblNewLabel_1_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		lblNewLabel_1_1.setBounds(66, 66, 328, 77);
		signupPanel.add(lblNewLabel_1_1);

		JLabel lblNewLabel_5_2 = new JLabel("Enter your details:");
		lblNewLabel_5_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_2.setForeground(new Color(153, 153, 153));
		lblNewLabel_5_2.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5_2.setBounds(140, 125, 181, 43);
		signupPanel.add(lblNewLabel_5_2);

		JLabel lblNewLabel_5_1_1 = new JLabel("Sign in instead");
		lblNewLabel_5_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(panel, "signinPanel");
			}
		});
		lblNewLabel_5_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_5_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1_1.setForeground(new Color(102, 102, 102));
		lblNewLabel_5_1_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_5_1_1.setBounds(350, 446, 157, 43);
		signupPanel.add(lblNewLabel_5_1_1);
		
		JLabel lblNewLabel_7_1_1_1 = new JLabel("");
		lblNewLabel_7_1_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_7_1_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_7_1_1_1.setOpaque(true);
		lblNewLabel_7_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_7_1_1_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_7_1_1_1.setBackground(new Color(14, 42, 71));
		lblNewLabel_7_1_1_1.setBounds(462, 25, 17, 6);
		signupPanel.add(lblNewLabel_7_1_1_1);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("X");
		lblNewLabel_8_1_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_8_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8_1_1.setForeground(new Color(14, 42, 71));
		lblNewLabel_8_1_1.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel_8_1_1.setBounds(485, 11, 22, 27);
		signupPanel.add(lblNewLabel_8_1_1);

		// cards.show(panel, "signupPanel");
	}

	public static class FrameDragListener extends MouseAdapter {

		private final JFrame frame;
		private Point mouseDownCompCoords = null;

		public FrameDragListener(JFrame frame) {
			this.frame = frame;
		}

		public void mouseReleased(MouseEvent e) {
			mouseDownCompCoords = null;
		}

		public void mousePressed(MouseEvent e) {
			mouseDownCompCoords = e.getPoint();
		}

		public void mouseDragged(MouseEvent e) {
			Point currCoords = e.getLocationOnScreen();
			frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
		}
	}

	public JFrame getFrame() {
		return frame;
	}
	
}
