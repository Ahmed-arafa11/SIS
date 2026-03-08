package cli;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    public static Connection connect() {

        try {

        	String url = "jdbc:sqlite:UniversityDB.db";
            return DriverManager.getConnection(url);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        return null;
    }
}