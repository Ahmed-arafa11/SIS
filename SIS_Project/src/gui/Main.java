package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
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
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 973, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButtonAdd = new JButton("Add Subject");
		btnNewButtonAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add_Subject add = new Add_Subject();
		        add.setVisible(true);
		        dispose(); 
			}
		});
		btnNewButtonAdd.setForeground(new Color(198, 128, 18));
		btnNewButtonAdd.setBounds(37, 36, 248, 68);
		btnNewButtonAdd.setBackground(new Color(28, 27, 74));
		btnNewButtonAdd.setFont(new Font("Verdana Pro", Font.BOLD, 18));
		contentPane.add(btnNewButtonAdd);
		
		JButton btnRegisterStudent = new JButton("Register Student");
		btnRegisterStudent.setForeground(new Color(198, 128, 18));
		btnRegisterStudent.setBounds(295, 36, 249, 68);
		btnRegisterStudent.setBackground(new Color(28, 27, 74));
		btnRegisterStudent.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnRegisterStudent);
		
		JButton btnEnrollStudentIn = new JButton("Enroll Student in Subject");
		btnEnrollStudentIn.setForeground(new Color(198, 128, 18));
		btnEnrollStudentIn.setBounds(554, 36, 248, 68);
		btnEnrollStudentIn.setBackground(new Color(28, 27, 74));
		btnEnrollStudentIn.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnEnrollStudentIn);
		
		JButton btnEnterGrades = new JButton("Enter Grades");
		btnEnterGrades.setForeground(new Color(198, 128, 18));
		btnEnterGrades.setBounds(37, 131, 248, 68);
		btnEnterGrades.setBackground(new Color(28, 27, 74));
		btnEnterGrades.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnEnterGrades);
		
		JButton btnShowStudentResult = new JButton("Show Student Result");
		btnShowStudentResult.setForeground(new Color(198, 128, 18));
		btnShowStudentResult.setBounds(296, 131, 248, 68);
		btnShowStudentResult.setBackground(new Color(28, 27, 74));
		btnShowStudentResult.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnShowStudentResult);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBounds(662, 422, 248, 68);
		btnExit.setBackground(new Color(233, 84, 84));
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		contentPane.add(btnExit);

	}
}