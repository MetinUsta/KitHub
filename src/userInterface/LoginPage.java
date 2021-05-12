package userInterface;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPage {

	private JFrame frame;
	private final JLabel sideBarBackgroundLabel = new JLabel("");
	private JTextField emailInputArea;
	private JTextField passwordInputArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setTitle("myLibrary");
		frame.setBounds(100, 100, 800, 500);
		frame.setUndecorated(true);
		
		Image icon = new ImageIcon(LoginPage.class.getResource("/loginWindowAssets/icon.png")).getImage();
		frame.setIconImage(icon);
		
		FrameDragListener frameDragListener = new FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon signin = new ImageIcon(LoginPage.class.getResource("/loginWindowAssets/sign.png"));
		Image imagesg = signin.getImage();
		Image newSignin = imagesg.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
		signin = new ImageIcon(newSignin);
		
		JLabel signinLabel = new JLabel("SIGN IN");
		signinLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		signinLabel.setForeground(new Color(255, 255, 255));
		signinLabel.setFont(new Font("Arial", Font.BOLD, 24));
		signinLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signinLabel.setOpaque(true);
		signinLabel.setBackground(new Color(6, 214, 160));
		signinLabel.setBounds(446, 386, 172, 43);
		frame.getContentPane().add(signinLabel);
		
		JLabel emailPlaceHolder = new JLabel("Email");
		emailPlaceHolder.setForeground(new Color(153, 153, 153));
		emailPlaceHolder.setFont(new Font("Arial", Font.PLAIN, 16));
		emailPlaceHolder.setBounds(415, 248, 69, 20);
		frame.getContentPane().add(emailPlaceHolder);
		
		JLabel passwordPlaceHolder = new JLabel("Password");
		passwordPlaceHolder.setForeground(new Color(153, 153, 153));
		passwordPlaceHolder.setFont(new Font("Arial", Font.PLAIN, 16));
		passwordPlaceHolder.setBounds(415, 301, 87, 20);
		frame.getContentPane().add(passwordPlaceHolder);
		
		JLabel sideBarInfoLabelTop = new JLabel("Enter your personal");
		sideBarInfoLabelTop.setHorizontalAlignment(SwingConstants.CENTER);
		sideBarInfoLabelTop.setForeground(new Color(232, 232, 232));
		sideBarInfoLabelTop.setFont(new Font("Cooper Black", Font.PLAIN, 23));
		sideBarInfoLabelTop.setBounds(10, 274, 261, 77);
		frame.getContentPane().add(sideBarInfoLabelTop);
		
		JLabel sideBarInfoLabelBottom = new JLabel("details and start reading!");
		sideBarInfoLabelBottom.setHorizontalAlignment(SwingConstants.CENTER);
		sideBarInfoLabelBottom.setForeground(new Color(232, 232, 232));
		sideBarInfoLabelBottom.setFont(new Font("Cooper Black", Font.PLAIN, 21));
		sideBarInfoLabelBottom.setBounds(0, 302, 284, 77);
		frame.getContentPane().add(sideBarInfoLabelBottom);
		
		/*JLabel lblNewLabel_3_1 = new JLabel("Enter your personal");
		lblNewLabel_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3_1.setForeground(new Color(232, 232, 232));
		lblNewLabel_3_1.setFont(new Font("Cooper Black", Font.PLAIN, 23));
		lblNewLabel_3_1.setBounds(10, 274, 261, 77);
		frame.getContentPane().add(lblNewLabel_3_1);*/
		
		JLabel libraryIcon = new JLabel("");
		libraryIcon.setHorizontalAlignment(SwingConstants.CENTER);
		libraryIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/loginWindowAssets/library.png")));
		libraryIcon.setBounds(80, 166, 120, 105);
		frame.getContentPane().add(libraryIcon);
		
		JLabel sideBarWelcomeLabel = new JLabel("Welcome back");
		sideBarWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sideBarWelcomeLabel.setFont(new Font("Cooper Black", Font.PLAIN, 36));
		sideBarWelcomeLabel.setForeground(new Color(232, 232, 232));
		sideBarWelcomeLabel.setBounds(10, 63, 261, 146);
		frame.getContentPane().add(sideBarWelcomeLabel);
		sideBarBackgroundLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginWindowAssets/IconGrid.png")));
		sideBarBackgroundLabel.setBounds(0, 0, 284, 500);
		frame.getContentPane().add(sideBarBackgroundLabel);
		
		JLabel contentPanelHeaderLabel = new JLabel("Sign in to myLibrary");
		contentPanelHeaderLabel.setForeground(new Color(6, 214, 160));
		contentPanelHeaderLabel.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		contentPanelHeaderLabel.setHorizontalAlignment(SwingConstants.CENTER);
		contentPanelHeaderLabel.setBounds(363, 80, 328, 77);
		frame.getContentPane().add(contentPanelHeaderLabel);
		
		JLabel guidingInfoLabel = new JLabel("Enter your details below to continue");
		guidingInfoLabel.setForeground(new Color(153, 153, 153));
		guidingInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 17));
		guidingInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		guidingInfoLabel.setBounds(372, 184, 309, 43);
		frame.getContentPane().add(guidingInfoLabel);
		
		emailInputArea = new JTextField();
		emailInputArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				emailPlaceHolder.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(emailInputArea.getText().compareTo("") == 0) {
					emailPlaceHolder.setVisible(true);
				}
			}
		});
		emailInputArea.setBackground(new Color(244, 244, 244));
		emailInputArea.setBounds(409, 242, 249, 32);
		frame.getContentPane().add(emailInputArea);
		emailInputArea.setColumns(10);
		
		passwordInputArea = new JTextField();
		passwordInputArea.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				passwordPlaceHolder.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(passwordInputArea.getText().compareTo("") == 0) {
					passwordPlaceHolder.setVisible(true);
				}
			}
		});
		passwordInputArea.setColumns(10);
		passwordInputArea.setBackground(new Color(244, 244, 244));
		passwordInputArea.setBounds(409, 295, 249, 32);
		frame.getContentPane().add(passwordInputArea);
		
		JLabel newAccountLabel = new JLabel("Don't have an account?");
		newAccountLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		newAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		newAccountLabel.setForeground(new Color(102, 102, 102));
		newAccountLabel.setFont(new Font("Arial", Font.BOLD, 17));
		newAccountLabel.setBounds(409, 332, 249, 43);
		frame.getContentPane().add(newAccountLabel);
		
		JLabel closeButton = new JLabel("X");
		closeButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		closeButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		closeButton.setFont(new Font("Arial", Font.BOLD, 23));
		closeButton.setHorizontalAlignment(SwingConstants.CENTER);
		closeButton.setForeground(new Color(14, 42, 71));
		closeButton.setBounds(768, 11, 22, 27);
		frame.getContentPane().add(closeButton);
		
		JLabel minifyButton = new JLabel("");
		minifyButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		minifyButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		minifyButton.setOpaque(true);
		minifyButton.setHorizontalAlignment(SwingConstants.CENTER);
		minifyButton.setForeground(Color.WHITE);
		minifyButton.setFont(new Font("Arial", Font.BOLD, 24));
		minifyButton.setBackground(new Color(14, 42, 71));
		minifyButton.setBounds(740, 20, 17, 6);
		frame.getContentPane().add(minifyButton);
		
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
}
