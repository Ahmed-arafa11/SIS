package gui;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Add_Subject extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add_Subject frame = new Add_Subject();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Add_Subject() {
		setTitle("ADD Subject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1132, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(997, 46, 74, 41);
		contentPane.add(comboBox);
		
		try {
		    Connection conn = DBConnection.connect();
		    String query = "SELECT level_id FROM Level ORDER BY level_id"; // ترتيب من 1 لـ 4
		    PreparedStatement pst = conn.prepareStatement(query);
		    ResultSet rs = pst.executeQuery();

		    // امسح أي عناصر موجودة قبل التحميل (اختياري)
		    comboBox.removeAllItems();

		    while (rs.next()) {
		        comboBox.addItem(rs.getString("level_id")); // حط الرقم كـ String
		    }

		    rs.close();
		    pst.close();

		} catch (SQLException e) {
		    System.out.println("Error loading levels: " + e.getMessage());
		}
		
		
		
		JComboBox<String> comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(795, 46, 192, 41);
		contentPane.add(comboBox_1);
		
		try {
		    Connection conn = DBConnection.connect();
		    String query = "SELECT name FROM Subject ORDER BY name"; // ترتيب من 1 لـ 4
		    PreparedStatement pst = conn.prepareStatement(query);
		    ResultSet rs = pst.executeQuery();

		    // امسح أي عناصر موجودة قبل التحميل (اختياري)
		    comboBox_1.removeAllItems();

		    while (rs.next()) {
		        comboBox_1.addItem(rs.getString("name")); // حط الرقم كـ String
		    }

		    rs.close();
		    pst.close();

		} catch (SQLException e) {
		    System.out.println("Error loading name: " + e.getMessage());
		}
		
		JComboBox<?> comboBox_1_1 = new JComboBox();
		comboBox_1_1.setBounds(696, 46, 89, 41);
		contentPane.add(comboBox_1_1);
		
		JComboBox<?> comboBox_1_1_1 = new JComboBox();
		comboBox_1_1_1.setBounds(597, 46, 89, 41);
		contentPane.add(comboBox_1_1_1);
		
		JLabel lblNewLabelLevel = new JLabel("LEVEL");
		lblNewLabelLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabelLevel.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblNewLabelLevel.setBounds(997, 10, 74, 26);
		contentPane.add(lblNewLabelLevel);
		
		JLabel lblCourse = new JLabel("COURSE");
		lblCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourse.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblCourse.setBounds(795, 10, 192, 26);
		contentPane.add(lblCourse);
		
		JLabel lblAss = new JLabel("Ass1");
		lblAss.setHorizontalAlignment(SwingConstants.CENTER);
		lblAss.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblAss.setBounds(700, 10, 85, 26);
		contentPane.add(lblAss);
		
		JLabel lblAss_2 = new JLabel("Ass2");
		lblAss_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAss_2.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblAss_2.setBounds(601, 10, 85, 26);
		contentPane.add(lblAss_2);
		
		JLabel lblCourse_1 = new JLabel("INSTRACTOR");
		lblCourse_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourse_1.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblCourse_1.setBounds(303, 10, 284, 26);
		contentPane.add(lblCourse_1);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(303, 46, 284, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButtonSUBMIT = new JButton("SUBMIT");
		btnNewButtonSUBMIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnNewButtonSUBMIT.setFont(new Font("Verdana Pro", Font.BOLD, 13));
		btnNewButtonSUBMIT.setBounds(56, 44, 237, 41);
		contentPane.add(btnNewButtonSUBMIT);
		
		table = new JTable();
		table.setBounds(36, 97, 1035, 351);
		contentPane.add(table);

	}
}
