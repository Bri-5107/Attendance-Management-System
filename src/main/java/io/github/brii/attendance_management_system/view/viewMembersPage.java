package io.github.brii.attendance_management_system.view;

import java.awt.EventQueue;
import java.sql.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class viewMembersPage extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewMembersPage frame = new viewMembersPage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	public viewMembersPage() {
		//Create Frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 738, 468);
		
		//Create Panel
		contentPane = new JPanel();
		contentPane.setBackground(new Color(238, 238, 238));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Create Table
		table = new JTable();
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
	
		
		//Create ScrollPane and add Table to it
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(52, 106, 630, 272);

		contentPane.add(scrollPane);
		table.setFillsViewportHeight(true);
		
		JButton addBtn = new JButton("Add");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addMemberPage memberPage = new addMemberPage();
				memberPage.setVisible(true);
			}
		});
		addBtn.setBounds(492, 391, 91, 29);
		contentPane.add(addBtn);
		
		JButton deleteBtn = new JButton("Delete");
		addBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		deleteBtn.setBounds(595, 391, 91, 29);
		contentPane.add(deleteBtn);
		
		
		
		try {
			//DriverManager Parameters
			String url = "jdbc:mysql://localhost:3306/attendanceDB";
			String username = "root";
			String password ="rlBD_115";
			
			//Establish DB Connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, username, password);
			
			//Create, Send, and Execute Query Statements
			String sql = "SELECT * FROM members"; 
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//Create Column Headers
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				String columnName = rsmd.getColumnName(i);
				model.addColumn(columnName);
			}
			
			//Populate Table 
			while (rs.next()) {
			
				Object[] rowData = new Object[columnCount];
				for (int i = 1; i <= columnCount; i++) {
					rowData[i-1] = rs.getObject(i);
				}
				model.addRow(rowData);
			
			}
				
				
		} catch (Exception e) {
			e.printStackTrace();
	        new JOptionPane();
	        JOptionPane.showMessageDialog(this, "Database Error", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
		
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}




}

