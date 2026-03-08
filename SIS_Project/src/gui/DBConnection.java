package gui;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static Connection connection = null;

    public static Connection connect() {
        try {
            // مسار الداتا بيز
            String url = "jdbc:sqlite:UniversityDB.db";

            if (connection == null) {
                connection = DriverManager.getConnection(url);
                System.out.println("Connected to SQLite database successfully!");
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }

        return connection;
    }
}