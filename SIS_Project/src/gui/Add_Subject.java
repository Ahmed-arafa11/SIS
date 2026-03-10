package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.DefaultComboBoxModel;

public class Add_Subject extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_COURSENAME;
	private JTable table;

	DefaultTableModel model;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				Add_Subject frame = new Add_Subject();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public Add_Subject() {
		setTitle("ADD Subject");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1250, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JComboBox<String> comboBoxLevel = new JComboBox<>();
		comboBoxLevel.setBounds(36, 46, 74, 41);
		contentPane.add(comboBoxLevel);

		try {
			Connection conn = DBConnection.connect();
			String query = "SELECT level_id FROM Level ORDER BY level_id";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				comboBoxLevel.addItem(rs.getString("level_id"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		JComboBox<String> comboBoxLOASS1 = new JComboBox<>();
		comboBoxLOASS1.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
		comboBoxLOASS1.setBounds(322, 46, 89, 41);
		contentPane.add(comboBoxLOASS1);

		JComboBox<String> comboBoxLOASS2 = new JComboBox<>();
		comboBoxLOASS2.setModel(new DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4" }));
		comboBoxLOASS2.setBounds(421, 46, 89, 41);
		contentPane.add(comboBoxLOASS2);

		JLabel lblLevel = new JLabel("LEVEL");
		lblLevel.setHorizontalAlignment(SwingConstants.CENTER);
		lblLevel.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblLevel.setBounds(36, 10, 74, 26);
		contentPane.add(lblLevel);

		JLabel lblCourse = new JLabel("COURSE NAME");
		lblCourse.setHorizontalAlignment(SwingConstants.CENTER);
		lblCourse.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblCourse.setBounds(120, 10, 192, 26);
		contentPane.add(lblCourse);

		JLabel lblAss = new JLabel("Ass1");
		lblAss.setHorizontalAlignment(SwingConstants.CENTER);
		lblAss.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblAss.setBounds(326, 10, 85, 26);
		contentPane.add(lblAss);

		JLabel lblAss_2 = new JLabel("Ass2");
		lblAss_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblAss_2.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblAss_2.setBounds(425, 10, 85, 26);
		contentPane.add(lblAss_2);

		JLabel lblInstructor = new JLabel("INSTRUCTOR");
		lblInstructor.setHorizontalAlignment(SwingConstants.CENTER);
		lblInstructor.setFont(new Font("Verdana Pro", Font.BOLD, 16));
		lblInstructor.setBounds(520, 10, 284, 26);
		contentPane.add(lblInstructor);

		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(520, 46, 284, 41);
		contentPane.add(textField);

		textField_COURSENAME = new JTextField();
		textField_COURSENAME.setHorizontalAlignment(SwingConstants.CENTER);
		textField_COURSENAME.setBounds(120, 46, 192, 41);
		contentPane.add(textField_COURSENAME);

		JButton btnNewButtonSUBMIT = new JButton("SUBMIT");
		btnNewButtonSUBMIT.setFont(new Font("Verdana Pro", Font.BOLD, 13));
		btnNewButtonSUBMIT.setBounds(846, 28, 284, 67);
		contentPane.add(btnNewButtonSUBMIT);

		table = new JTable();
		model = new DefaultTableModel();
		model.addColumn("Level");
		model.addColumn("Subject ID");
		model.addColumn("Subject Name");
		model.addColumn("LO1 Count");
		model.addColumn("LO2 Count");
		model.addColumn("Doctor Name");

		table.setModel(model);
		table.setFont(new Font("Verdana Pro", Font.PLAIN, 18));
		table.setRowHeight(30);
		JTableHeader header = table.getTableHeader();
		header.setFont(new Font("Verdana Pro", Font.BOLD, 20));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(36, 120, 1150, 400);
		contentPane.add(scrollPane);

		loadTableData();

		btnNewButtonSUBMIT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection conn = DBConnection.connect();

					String doctorName = textField.getText();
					String subjectName = textField_COURSENAME.getText();
					String level = comboBoxLevel.getSelectedItem().toString();

					int loAss1 = Integer.parseInt(comboBoxLOASS1.getSelectedItem().toString());
					int loAss2 = Integer.parseInt(comboBoxLOASS2.getSelectedItem().toString());

					// أولا خزّن الدكتور
					String insertDoctor = "INSERT INTO Doctor(name) VALUES(?)";
					PreparedStatement pstDoctor = conn.prepareStatement(insertDoctor, PreparedStatement.RETURN_GENERATED_KEYS);
					pstDoctor.setString(1, doctorName);
					pstDoctor.executeUpdate();
					ResultSet rsDoctor = pstDoctor.getGeneratedKeys();
					int doctorId = 0;
					if (rsDoctor.next()) {
						doctorId = rsDoctor.getInt(1);
					}

					// خزّن المادة مع level و doctor_id
					String insertSubject = "INSERT INTO Subject(name, level_id, doctor_id) VALUES(?, ?, ?)";
					PreparedStatement pstSubject = conn.prepareStatement(insertSubject, PreparedStatement.RETURN_GENERATED_KEYS);
					pstSubject.setString(1, subjectName);
					pstSubject.setString(2, level);
					pstSubject.setInt(3, doctorId);
					pstSubject.executeUpdate();
					ResultSet rsSubject = pstSubject.getGeneratedKeys();
					int subject_id = 0;
					if (rsSubject.next()) {
						subject_id = rsSubject.getInt(1);
					}

					// خزّن LO1
					for (int i = 1; i <= loAss1; i++) {
						String insertLO = "INSERT INTO lo(subject_id, lo_name, assignment_type) VALUES(?,?,?)";
						PreparedStatement pstLO = conn.prepareStatement(insertLO);
						pstLO.setInt(1, subject_id);
						pstLO.setString(2, "LO" + i);
						pstLO.setString(3, "Assignment1");
						pstLO.executeUpdate();
					}

					// خزّن LO2
					for (int i = 1; i <= loAss2; i++) {
						String insertLO = "INSERT INTO lo(subject_id, lo_name, assignment_type) VALUES(?,?,?)";
						PreparedStatement pstLO = conn.prepareStatement(insertLO);
						pstLO.setInt(1, subject_id);
						pstLO.setString(2, "LO" + i);
						pstLO.setString(3, "Assignment2");
						pstLO.executeUpdate();
					}

					// اضف الصف الجديد للجدول فورًا
					model.addRow(new Object[] { level, subject_id, subjectName, loAss1, loAss2, doctorName });

					// امسح الحقول بعد الإدخال
					textField.setText("");
					textField_COURSENAME.setText("");
					comboBoxLOASS1.setSelectedIndex(0);
					comboBoxLOASS2.setSelectedIndex(0);

				} catch (Exception ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
	}

	private void loadTableData() {
	    try {
	        Connection conn = DBConnection.connect();
	        String query = "SELECT s.level_id, s.subject_id, s.name AS subject_name, " +
	                       "(SELECT COUNT(*) FROM lo WHERE subject_id=s.subject_id AND assignment_type='Assignment1') AS ass1, " +
	                       "(SELECT COUNT(*) FROM lo WHERE subject_id=s.subject_id AND assignment_type='Assignment2') AS ass2, " +
	                       "d.name AS doctor_name " +
	                       "FROM Subject s " +
	                       "LEFT JOIN Doctor d ON s.doctor_id = d.doctor_id " +
	                       "ORDER BY s.level_id, s.subject_id";

	        PreparedStatement pst = conn.prepareStatement(query);
	        ResultSet rs = pst.executeQuery();

	        model.setRowCount(0); // امسح كل الصفوف قبل التحميل الجديد

	        while (rs.next()) {
	            String level = rs.getString("level_id");       // من جدول Subject
	            int subject_id = rs.getInt("subject_id");     // من جدول Subject
	            String subject_name = rs.getString("subject_name"); // اسم المادة من Subject
	            int ass1 = rs.getInt("ass1");                 // LO1 Count
	            int ass2 = rs.getInt("ass2");                 // LO2 Count
	            String doctor = rs.getString("doctor_name");  // اسم الدكتور من Doctor

	            model.addRow(new Object[] { level, subject_id, subject_name, ass1, ass2, doctor });
	        }

	        rs.close();
	        pst.close();
	    } catch (Exception e) {
	        System.out.println("Error loading table: " + e.getMessage());
	    }
	}

}