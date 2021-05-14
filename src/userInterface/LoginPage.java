package userInterface;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import javax.swing.SwingUtilities;

public class LoginPage {

	private JFrame frame;
	private final JLabel sideBarIcon = new JLabel("");
	private JTextField emailLoginInput;
	private JTextField NameTextField;
	private JTextField LastNameTextField;
	private JTextField EmailTextField;
	private JPasswordField passwordField;
	private JPasswordField passwordFieldLogin;
	private boolean verified = false;
	private Color textColor = new Color(253, 65, 60);
	private Color buttonText = new Color(254, 188, 44);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

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
		Color backgroundColor = new Color(33, 37, 41);
		
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
		
		
		sideBarIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGridUpdated.png")));
		sideBarIcon.setBounds(0, 0, 284, 500);
		frame.getContentPane().add(sideBarIcon);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(283, 0, 517, 500);
		frame.getContentPane().add(panel);
		CardLayout cards = new CardLayout();
		panel.setLayout(cards);
		// cards.show(panel, "signupPanel");

		JPanel signinPanel = new JPanel();
		signinPanel.setBackground(backgroundColor);
		signinPanel.setBounds(0, 0, 517, 500);
		panel.add(signinPanel, "signinPanel");
		signinPanel.setLayout(null);

		JLabel infoTextSignIn = new JLabel("Sign in to myLibrary");
		infoTextSignIn.setBounds(94, 85, 328, 77);
		signinPanel.add(infoTextSignIn);
		infoTextSignIn.setForeground(new Color(253, 65, 60));
		infoTextSignIn.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		infoTextSignIn.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel infoTextDetails = new JLabel("Enter your details:");
		infoTextDetails.setBounds(165, 152, 181, 43);
		signinPanel.add(infoTextDetails);
		infoTextDetails.setForeground(Color.WHITE);
		infoTextDetails.setFont(new Font("Arial", Font.BOLD, 15));
		infoTextDetails.setHorizontalAlignment(SwingConstants.CENTER);

		emailLoginInput = new JTextField();
		emailLoginInput.setBounds(137, 206, 249, 32);
		signinPanel.add(emailLoginInput);
		emailLoginInput.setBackground(new Color(244, 244, 244));
		emailLoginInput.setFont(new Font("Tahoma", Font.PLAIN, 15));
		emailLoginInput.setForeground(Color.GRAY);
		emailLoginInput.setColumns(10);

		JLabel infoTextAccount = new JLabel("Don't have an account?");
		infoTextAccount.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(panel, "signupPanel");
			}
		});
		infoTextAccount.setBounds(137, 342, 249, 43);
		signinPanel.add(infoTextAccount);
		infoTextAccount.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		infoTextAccount.setHorizontalAlignment(SwingConstants.CENTER);
		infoTextAccount.setForeground(Color.WHITE);
		infoTextAccount.setFont(new Font("Arial", Font.BOLD, 17));

		JLabel signInButton = new JLabel("SIGN IN");
		signInButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!verified){
					verified = true;
					Window window = new Window(getTextColor(), getButtonText());
					window.getFrame().setVisible(true);
					frame.setVisible(false);
				}
			}
		});
		signInButton.setBounds(174, 302, 172, 43);
		signinPanel.add(signInButton);
		signInButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signInButton.setForeground(new Color(255, 255, 255));
		signInButton.setFont(new Font("Arial", Font.BOLD, 24));
		signInButton.setHorizontalAlignment(SwingConstants.CENTER);
		signInButton.setOpaque(true);
		signInButton.setBackground(new Color(253, 65, 60));

		passwordFieldLogin = new JPasswordField();
		passwordFieldLogin.setBackground(new Color(244, 244, 244));
		passwordFieldLogin.setForeground(Color.GRAY);
		passwordFieldLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passwordFieldLogin.setBounds(137, 249, 249, 32);
		signinPanel.add(passwordFieldLogin);

		JPanel signupPanel = new JPanel();
		signupPanel.setBackground(backgroundColor);
		panel.add(signupPanel, "signupPanel");
		signupPanel.setLayout(null);

		@SuppressWarnings("unused")
		TextPrompt emailLoginPlaceholder = new TextPrompt("Email ", emailLoginInput);
		@SuppressWarnings("unused")
		TextPrompt passwordLoginPlaceholder = new TextPrompt("Password ", passwordFieldLogin);
		
		JLabel iconifyButton = new JLabel("");
		iconifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		iconifyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconifyButton.setOpaque(true);
		iconifyButton.setHorizontalAlignment(SwingConstants.CENTER);
		iconifyButton.setForeground(Color.WHITE);
		iconifyButton.setFont(new Font("Arial", Font.BOLD, 24));
		iconifyButton.setBackground(Color.WHITE);
		iconifyButton.setBounds(462, 25, 17, 6);
		signinPanel.add(iconifyButton);
		
		JLabel closeButton = new JLabel("X");
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
		closeButton.setForeground(Color.WHITE);
		closeButton.setFont(new Font("Arial", Font.BOLD, 23));
		closeButton.setBounds(485, 11, 22, 27);
		signinPanel.add(closeButton);
		
		JPanel colorOptionsPanel = new JPanel();
		colorOptionsPanel.setLayout(null);
		colorOptionsPanel.setBackground(new Color(39, 43, 47));
		colorOptionsPanel.setBounds(80, 385, 360, 61);
		signinPanel.add(colorOptionsPanel);
		
		ImageIcon moryesil = scaleColorOption("/SystemAssets/ColorOptions/moryesil.png");
		ImageIcon turkuazsomon = scaleColorOption("/SystemAssets/ColorOptions/turkuazsomon.png");
		ImageIcon turuncumavi = scaleColorOption("/SystemAssets/ColorOptions/turuncumavi.png");
		ImageIcon turuncusari = scaleColorOption("/SystemAssets/ColorOptions/turuncusari.png");
		ImageIcon yesilsari = scaleColorOption("/SystemAssets/ColorOptions/yesilsari.png");
		
		JLabel turuncusariIcon = new JLabel("");
		turuncusariIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		turuncusariIcon.setIcon(turuncusari);
		turuncusariIcon.setHorizontalAlignment(SwingConstants.CENTER);
		turuncusariIcon.setBounds(10, 11, 60, 41);
		colorOptionsPanel.add(turuncusariIcon);
		
		JLabel turkuazsomonIcon = new JLabel("");
		turkuazsomonIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		turkuazsomonIcon.setIcon(turkuazsomon);
		turkuazsomonIcon.setHorizontalAlignment(SwingConstants.CENTER);
		turkuazsomonIcon.setBounds(80, 11, 60, 41);
		colorOptionsPanel.add(turkuazsomonIcon);
		
		JLabel yesilsariIcon = new JLabel("");
		yesilsariIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		yesilsariIcon.setHorizontalAlignment(SwingConstants.CENTER);
		yesilsariIcon.setIcon(yesilsari);
		yesilsariIcon.setBounds(150, 11, 60, 41);
		colorOptionsPanel.add(yesilsariIcon);
		
		JLabel moryesilIcon = new JLabel("");
		moryesilIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moryesilIcon.setHorizontalAlignment(SwingConstants.CENTER);
		moryesilIcon.setIcon(moryesil);
		moryesilIcon.setBounds(220, 11, 60, 41);
		colorOptionsPanel.add(moryesilIcon);
		
		JLabel turuncumaviIcon = new JLabel("");
		turuncumaviIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		turuncumaviIcon.setHorizontalAlignment(SwingConstants.CENTER);
		turuncumaviIcon.setIcon(turuncumavi);
		turuncumaviIcon.setBounds(290, 11, 60, 41);
		colorOptionsPanel.add(turuncumaviIcon);
		
		JLabel infoTextSignUp = new JLabel("Sign up to myLibrary");
		JLabel signUpButton = new JLabel("SIGN UP");
		
		MouseAdapter colorChooser = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == turkuazsomonIcon) {
					infoTextSignIn.setForeground(new Color(54, 199, 208));
					signInButton.setBackground(new Color(54, 199, 208));
					infoTextSignUp.setForeground(new Color(54, 199, 208));
					signUpButton.setBackground(new Color(54, 199, 208));
					sideBarIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGridCyan.png")));
					setTextColor(54, 199, 208);
					setButtonText(255, 164, 142);
					
				}
				if(e.getSource() == yesilsariIcon) {
					infoTextSignIn.setForeground(new Color(63, 139, 76));
					signInButton.setBackground(new Color(63, 139, 76));
					infoTextSignUp.setForeground(new Color(63, 139, 76));
					signUpButton.setBackground(new Color(63, 139, 76));
					sideBarIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGridGreen.png")));
					setTextColor(63, 139, 76);
					setButtonText(198, 192, 19);
				}
				if(e.getSource() == moryesilIcon) {
					infoTextSignIn.setForeground(new Color(187, 134, 252));
					signInButton.setBackground(new Color(187, 134, 252));
					infoTextSignUp.setForeground(new Color(187, 134, 252));
					signUpButton.setBackground(new Color(187, 134, 252));
					sideBarIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGridPurple.png")));
					setTextColor(187, 134, 252);
					setButtonText(3, 218, 196);
				}
				if(e.getSource() == turuncumaviIcon) {
					infoTextSignIn.setForeground(new Color(253, 65, 60));
					signInButton.setBackground(new Color(253, 65, 60));
					infoTextSignUp.setForeground(new Color(253, 65, 60));
					signUpButton.setBackground(new Color(253, 65, 60));
					sideBarIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGridUpdated.png")));
					setTextColor(253, 65, 60);
					setButtonText(2, 129, 255);
				}
				if(e.getSource() == turuncusariIcon) {
					infoTextSignIn.setForeground(new Color(253, 65, 60));
					signInButton.setBackground(new Color(253, 65, 60));
					infoTextSignUp.setForeground(new Color(253, 65, 60));
					signUpButton.setBackground(new Color(253, 65, 60));
					sideBarIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginPageAssets/IconGridUpdated.png")));
					setTextColor(253, 65, 60);
					setButtonText(254, 188, 44);
				}
			}
		};
		
		turkuazsomonIcon.addMouseListener(colorChooser);
		yesilsariIcon.addMouseListener(colorChooser);
		moryesilIcon.addMouseListener(colorChooser);
		turuncumaviIcon.addMouseListener(colorChooser);
		turuncusariIcon.addMouseListener(colorChooser);
		
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

		
		signUpButton.setOpaque(true);
		signUpButton.setHorizontalAlignment(SwingConstants.CENTER);
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setFont(new Font("Arial", Font.BOLD, 24));
		signUpButton.setBackground(new Color(6, 214, 160));
		signUpButton.setBounds(146, 351, 172, 43);
		signupPanel.add(signUpButton);
		
		
		
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

		
		infoTextSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		infoTextSignUp.setForeground(new Color(6, 214, 160));
		infoTextSignUp.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		infoTextSignUp.setBounds(66, 66, 328, 77);
		signupPanel.add(infoTextSignUp);

		JLabel infoTextNewDetails = new JLabel("Enter your details:");
		infoTextNewDetails.setHorizontalAlignment(SwingConstants.CENTER);
		infoTextNewDetails.setForeground(Color.WHITE);
		infoTextNewDetails.setFont(new Font("Arial", Font.BOLD, 15));
		infoTextNewDetails.setBounds(140, 125, 181, 43);
		signupPanel.add(infoTextNewDetails);

		JLabel returnButton = new JLabel("Sign in instead");
		returnButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cards.show(panel, "signinPanel");
			}
		});
		returnButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		returnButton.setHorizontalAlignment(SwingConstants.CENTER);
		returnButton.setForeground(Color.WHITE);
		returnButton.setFont(new Font("Arial", Font.BOLD, 17));
		returnButton.setBounds(350, 446, 157, 43);
		signupPanel.add(returnButton);
		
		JLabel iconifyButtonSignUp = new JLabel("");
		iconifyButtonSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		iconifyButtonSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		iconifyButtonSignUp.setOpaque(true);
		iconifyButtonSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		iconifyButtonSignUp.setForeground(Color.WHITE);
		iconifyButtonSignUp.setFont(new Font("Arial", Font.BOLD, 24));
		iconifyButtonSignUp.setBackground(Color.WHITE);
		iconifyButtonSignUp.setBounds(462, 25, 17, 6);
		signupPanel.add(iconifyButtonSignUp);
		
		JLabel closeButtonSignUp = new JLabel("X");
		closeButtonSignUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButtonSignUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeButtonSignUp.setHorizontalAlignment(SwingConstants.CENTER);
		closeButtonSignUp.setForeground(Color.WHITE);
		closeButtonSignUp.setFont(new Font("Arial", Font.BOLD, 23));
		closeButtonSignUp.setBounds(485, 11, 22, 27);
		signupPanel.add(closeButtonSignUp);
		
		

		// cards.show(panel, "signupPanel");
		frame.setVisible(true);
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
	
	public ImageIcon scaleColorOption(String path) {
		Image colorImage = new ImageIcon(Window.class.getResource(path)).getImage();
		Image scaledColorImage = colorImage.getScaledInstance((int) (28*1.91), 28, Image.SCALE_SMOOTH);
		ImageIcon scaledColorImageIcon = new ImageIcon(scaledColorImage);
		return scaledColorImageIcon;
	}

	public boolean isVerified() {
		return verified;
	}

	public Color getTextColor() {
		return textColor;
	}

	public void setTextColor(int r, int g, int b) {
		Color newColor = new Color(r, g, b);
		this.textColor = newColor;
	}

	public Color getButtonText() {
		return buttonText;
	}

	public void setButtonText(int r, int g, int b) {
		Color newColor = new Color(r, g, b);
		this.buttonText = newColor;
	}
	
}
