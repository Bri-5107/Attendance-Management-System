package io.github.brii.attendance_management_system.view;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.SwingConstants;
import java.awt.Color;


public class loginPage extends JFrame{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton loginButton;
	
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginPage frame = new loginPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public loginPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{75, 62, 181, 0};
		gbl_contentPane.rowHeights = new int[]{143, 26, 26, 29, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setLabelFor(lblNewLabel);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.BOTH; 
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		contentPane.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setForeground(new Color(0, 0, 0));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		contentPane.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 2;
		contentPane.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.gridx = 2;
		gbc_passwordField.gridy = 2;
		contentPane.add(passwordField, gbc_passwordField);
		
		loginButton = new JButton("Log In");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				String url = "jdbc:mysql://localhost:3306/attendanceDB";
				String username = "root";
				String password ="rlBD_115";
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver"); //create the JDBC driver to be able to connect to MySQL
					Connection con = DriverManager.getConnection(url, username, password); //connect to DB using the specified parameters
					
					String userName = textField.getText(); //retrieve text from JtextField component
					String passWord = (new String( passwordField.getPassword())); //retrieve text from JpasswordField component
					
					
					String sql = "SELECT password FROM admin WHERE username = ?";
				    PreparedStatement pstmt = con.prepareStatement(sql);
				    pstmt.setString(1, userName);

				    ResultSet rs = pstmt.executeQuery();

				    if (rs.next()) {
				        String storedPassword = rs.getString("password");

				       
				        if (passWord.equals(storedPassword)) { 
				            JOptionPane.showMessageDialog(loginPage.this, "Login successful!");
				          
				        } else {
				            JOptionPane.showMessageDialog(loginPage.this, "Incorrect password.", "Login Error", JOptionPane.ERROR_MESSAGE);
				        }
				    } else {
				        JOptionPane.showMessageDialog(loginPage.this, "Username not found.", "Login Error", JOptionPane.ERROR_MESSAGE);
				    }

				    rs.close();
				    pstmt.close();
				    con.close();

				} catch (Exception ex) {
					 System.out.println("Database Error");
				}
				
			}
		});
		GridBagConstraints gbc_loginButton = new GridBagConstraints();
		gbc_loginButton.fill = GridBagConstraints.VERTICAL;
		gbc_loginButton.gridx = 2;
		gbc_loginButton.gridy = 4;
		contentPane.add(loginButton, gbc_loginButton);
	}
		
}

