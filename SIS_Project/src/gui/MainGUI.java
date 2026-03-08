package gui;

import javax.swing.*;

public class MainGUI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("SIS - Main Menu");
		
		JButton btnRegisterStudent = new JButton("Register Student");
		btnRegisterStudent.setBounds(60, 40, 180, 40);
		
		btnRegisterStudent.addActionListener(e -> { new RegisterStudentGUI(); });
		
		frame.add(btnRegisterStudent);
		
		frame.setSize(300, 180);
		frame.setLayout(null);
		frame.setVisible(true);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
	}

}
