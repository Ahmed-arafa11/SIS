package gui;

import javax.swing.*;
import services.StudentService;

public class RegisterStudentGUI {

	public  RegisterStudentGUI() {
	
		
		JFrame frame = new JFrame("Register Student");
	
		JLabel lblId = new JLabel("Student ID:");
		lblId.setBounds(30, 30, 100, 25);	
		
		JTextField txtId = new JTextField();
		txtId.setBounds(130, 30, 150, 25);
		
		JLabel lblName = new JLabel("Student Name:");
		lblName.setBounds(30, 70, 100, 25);
	
		JTextField txtName = new JTextField();
		txtName.setBounds(130, 70, 150, 25);
		
        JButton btnRegister = new JButton("Register");	
        btnRegister.setBounds(100, 120, 120, 35);
	
        btnRegister.addActionListener(e -> {
    	      String id = txtId.getText();
    	String name= txtName.getText();
    	
    	if (id.isEmpty() || name.isEmpty()) {
    		JOptionPane.showMessageDialog(frame,"Please fill all fields");
    		return;
    	}
    	
    	try {
    		StudentService.saveStudent(id,name);
    		JOptionPane.showMessageDialog(frame,"Student Registered Successfully");
    		txtId.setText("");
    		txtName.setText("");
    	} catch (Exception ex) { 
    		JOptionPane.showMessageDialog(frame,"Error saving student");
    		
    	 }
        });
    	
        frame.add(lblId);
        	frame.add(txtId);
        		frame.add(lblName);
        			frame.add(txtName);
        				frame.add(btnRegister);
    	
    	frame.setSize(350, 230);
    	frame.setLayout(null);
    	frame.setVisible(true);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
    	
	  }
	}	

	
