package services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StudentService {

	public static void saveStudent(String id, String name) throws IOException {
		// TODO Auto-generated method stub

		File dir = new File("data/Student");
		dir.mkdirs();
		
		File file = new File(dir, id + ".txt");
		
		if (file.exists()) {
			System.out.println("Student ID already Exists");
		return;
		
		}
	FileWriter fw = new FileWriter(file);
	fw.write("ID: " + id + "\n");
	fw.write("Name: " + name + "\n");
	fw.close();
	
	
	
	}

}
