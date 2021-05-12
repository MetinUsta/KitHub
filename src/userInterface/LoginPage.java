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
	private final JLabel lblNewLabel = new JLabel("");
	private JTextField textField;
	private JTextField textField_1;

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
		
		JLabel lblNewLabel_7 = new JLabel("SIGN IN");
		lblNewLabel_7.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_7.setForeground(new Color(255, 255, 255));
		lblNewLabel_7.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setOpaque(true);
		lblNewLabel_7.setBackground(new Color(6, 214, 160));
		lblNewLabel_7.setBounds(446, 386, 172, 43);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_6 = new JLabel("Email");
		lblNewLabel_6.setForeground(new Color(153, 153, 153));
		lblNewLabel_6.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(415, 248, 69, 20);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_6_1 = new JLabel("Password");
		lblNewLabel_6_1.setForeground(new Color(153, 153, 153));
		lblNewLabel_6_1.setFont(new Font("Arial", Font.PLAIN, 16));
		lblNewLabel_6_1.setBounds(415, 301, 87, 20);
		frame.getContentPane().add(lblNewLabel_6_1);
		
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
		lblNewLabel_4.setIcon(new ImageIcon(LoginPage.class.getResource("/loginWindowAssets/library.png")));
		lblNewLabel_4.setBounds(80, 166, 120, 105);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_3 = new JLabel("Welcome back");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Cooper Black", Font.PLAIN, 36));
		lblNewLabel_3.setForeground(new Color(232, 232, 232));
		lblNewLabel_3.setBounds(10, 63, 261, 146);
		frame.getContentPane().add(lblNewLabel_3);
		lblNewLabel.setIcon(new ImageIcon(LoginPage.class.getResource("/LoginWindowAssets/IconGrid.png")));
		lblNewLabel.setBounds(0, 0, 284, 500);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Sign in to myLibrary");
		lblNewLabel_1.setForeground(new Color(6, 214, 160));
		lblNewLabel_1.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 27));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(363, 80, 328, 77);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("Enter your details below to continue");
		lblNewLabel_5.setForeground(new Color(153, 153, 153));
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(372, 184, 309, 43);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_6.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField.getText().compareTo("") == 0) {
					lblNewLabel_6.setVisible(true);
				}
			}
		});
		textField.setBackground(new Color(244, 244, 244));
		textField.setBounds(409, 242, 249, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				lblNewLabel_6_1.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				if(textField_1.getText().compareTo("") == 0) {
					lblNewLabel_6_1.setVisible(true);
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBackground(new Color(244, 244, 244));
		textField_1.setBounds(409, 295, 249, 32);
		frame.getContentPane().add(textField_1);
		
		JLabel lblNewLabel_5_1 = new JLabel("Don't have an account?");
		lblNewLabel_5_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_5_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5_1.setForeground(new Color(102, 102, 102));
		lblNewLabel_5_1.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_5_1.setBounds(409, 332, 249, 43);
		frame.getContentPane().add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_8 = new JLabel("X");
		lblNewLabel_8.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_8.setFont(new Font("Arial", Font.BOLD, 23));
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setForeground(new Color(14, 42, 71));
		lblNewLabel_8.setBounds(768, 11, 22, 27);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_7_1 = new JLabel("");
		lblNewLabel_7_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lblNewLabel_7_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(JFrame.ICONIFIED);
			}
		});
		lblNewLabel_7_1.setOpaque(true);
		lblNewLabel_7_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7_1.setForeground(Color.WHITE);
		lblNewLabel_7_1.setFont(new Font("Arial", Font.BOLD, 24));
		lblNewLabel_7_1.setBackground(new Color(14, 42, 71));
		lblNewLabel_7_1.setBounds(740, 20, 17, 6);
		frame.getContentPane().add(lblNewLabel_7_1);
		
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
