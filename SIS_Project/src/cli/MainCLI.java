package cli;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class MainCLI {

    static Scanner input = new Scanner(System.in);

    
    // الفرق
    static String[] levels = {
            "First Year",
            "Second Year",
            "Third Year",
            "Fourth Year"
    };

    // المواد حسب الفرقة
    static String[][] subjectsByLevel = {
            { "Math", "Physics", "Python", "IT", "Intro to cyber security", "English" },
            { "Data Structures", "Linux", "Progress in C++", "Database", "OS", "CCNA" },
            { "Computer Graphics", "Data Communication", "CCNA II", "Java Programming II" },
            { "Server Admin", "CCNA IV", "IOT Security", "Big Data", "CCNP", "Machine Learning" }
    };

    // تخزين عدد الـ LO
    static int assignment1LOCount;
    static int assignment2LOCount;

    public static void main(String[] args) {

        int choice;

        do {
        	System.out.println("\n=== MAIN MENU ===");
        	System.out.println("1- Add Subject");
        	System.out.println("2- Register Student");
        	System.out.println("3- Enroll Student in Subject");
        	System.out.println("4- Enter Grades");
        	System.out.println("5- Show Student Result");
        	System.out.println("0- Exit");

            choice = input.nextInt();

            switch (choice) {

            case 1:
                addSubject();
                break;

            case 2:
                registerStudent();
                break;

            case 3:
                enrollStudent();
                break;

            case 4:
                enterGrades();
                break;

            case 5:
                showResult();
                break;
           
            default:
            	System.out.println("Invalid Choice");
            }

        } while (choice != 0);
    
    }

    // ==============================
    // Manage Subject Menu
    // ==============================
    public static void manageSubjectMenu() {

        int choice;

        do {
            System.out.println("\n--- Manage Subject ---");
            System.out.println("1- Add Subject");
            System.out.println("0- Back");
            System.out.print("Enter choice: ");

            choice = input.nextInt();

            switch (choice) {
                case 1:
                    addSubject();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 0);
    }

    // ==============================
    // Add Subject
    // ==============================
    public static void addSubject() {

        input.nextLine();

        System.out.print("Subject Name: ");
        String subjectName = input.nextLine();

        System.out.print("Level ID: ");
        int level = input.nextInt();

        System.out.print("Assignment 1 LO Count: ");
        assignment1LOCount = input.nextInt();

        System.out.print("Assignment 2 LO Count: ");
        assignment2LOCount = input.nextInt();

        try {

            Connection conn = DBConnection.connect();

            if (conn == null) {
                System.out.println("Database connection failed");
                return;
            }

            String sql = "INSERT INTO Subject(name,level_id) VALUES(?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, subjectName);
            stmt.setInt(2, level);

            stmt.executeUpdate();

            conn.close();

            System.out.println("\nSubject Added Successfully ✅");

            System.out.println("\nAssignment Structure:");
            System.out.println("Assignment 1 LO Count: " + assignment1LOCount);
            System.out.println("Assignment 2 LO Count: " + assignment2LOCount);

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }

        int option;

        System.out.println("\n==========================");
        System.out.println("1- Enter Assignment Grades");
        System.out.println("2- Back to Main Menu");
        System.out.print("Enter choice: ");

        option = input.nextInt();

        if (option == 1) {
            DegreesAssignments();
        }
    }
    // ==============================
    // Register Student
    // ==============================
    static String[] departments = {
            "Artificial limbs",
            "Mechatronics",
            "ICT",
            "Autotronics"
    };

    public static void registerStudent() {

        input.nextLine();

        System.out.print("Student Name: ");
        String name = input.nextLine();

        System.out.print("Level ID: ");
        int level = input.nextInt();

        System.out.print("Department ID: ");
        int dept = input.nextInt();

        try {

            Connection conn = DBConnection.connect();

            String sql = "INSERT INTO Student(name,level_id,department_id) VALUES(?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setInt(2, level);
            stmt.setInt(3, dept);

            stmt.executeUpdate();

            System.out.println("Student Registered Successfully ✅");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
  
    public static void enrollStudent() {

        System.out.print("Student ID: ");
        int studentId = input.nextInt();

        System.out.print("Subject ID: ");
        int subjectId = input.nextInt();

        try {

            Connection conn = DBConnection.connect();

            String sql = "INSERT INTO Enrollment(student_id,subject_id) VALUES(?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, studentId);
            stmt.setInt(2, subjectId);

            stmt.executeUpdate();

            System.out.println("Student Enrolled Successfully ✅");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    public static void enterGrades() {

        System.out.print("Enrollment ID: ");
        int enrollmentId = input.nextInt();

        System.out.print("Number of LO: ");
        int loCount = input.nextInt();

        int total = 0;

        for (int i = 1; i <= loCount; i++) {

            System.out.print("LO" + i + " Grade (P/M/D): ");

            String grade = input.next().toUpperCase();

            int degree = 0;

            switch (grade) {

                case "P": degree = 9; break;
                case "M": degree = 12; break;
                case "D": degree = 15; break;
                default:
                	System.out.println("Invalid grade");
                	i--;
                	continue;
            }

            total += degree;
        }

        String finalGrade;

        if (total < 60)
            finalGrade = "NA";
        else if (total <= 75)
            finalGrade = "P";
        else if (total <= 89)
            finalGrade = "M";
        else
            finalGrade = "D";

        try {

            Connection conn = DBConnection.connect();

            String sql = "INSERT INTO Final_Result(enrollment_id,degree,grade) VALUES(?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, enrollmentId);
            stmt.setInt(2, total);
            stmt.setString(3, finalGrade);

            stmt.executeUpdate();
            conn.close();
            System.out.println("Final Result Saved ✅");

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    
    public static void showResult() {

        System.out.print("Student ID: ");
        int studentId = input.nextInt();

        try {

            Connection conn = DBConnection.connect();

            String sql =
            "SELECT Student.name, Subject.name, Final_Result.degree, Final_Result.grade " +
            "FROM Final_Result " +
            "JOIN Enrollment ON Final_Result.enrollment_id = Enrollment.enrollment_id " +
            "JOIN Student ON Enrollment.student_id = Student.student_id " +
            "JOIN Subject ON Enrollment.subject_id = Subject.subject_id " +
            "WHERE Student.student_id = ?";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, studentId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

            	System.out.println("\n======================");
            	System.out.println("Student: " + rs.getString(1));
            	System.out.println("Subject: " + rs.getString(2));
            	System.out.println("Degree : " + rs.getInt(3));
            	System.out.println("Grade  : " + rs.getString(4));
            	System.out.println("======================");
            }

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }
    // ==============================
    // Degrees Assignments
    // ==============================
    public static void DegreesAssignments() {

        input.nextLine();

        System.out.print("\nEnter Student Name: ");
        String studentName = input.nextLine();

        int assignment1Total = 0;
        int assignment2Total = 0;

        System.out.println("\nAssignment 1:");
        for (int i = 1; i <= assignment1LOCount; i++) {
            assignment1Total += getLOGrade("LO" + i);
        }

        System.out.println("\nAssignment 2:");
        for (int i = 1; i <= assignment2LOCount; i++) {
            assignment2Total += getLOGrade("LO" + i);
        }

        int subjectTotal = assignment1Total + assignment2Total;

        System.out.println("\n==============================");
        System.out.println("Student Name: " + studentName);
        System.out.println("Assignment 1 Total: " + assignment1Total);
        System.out.println("Assignment 2 Total: " + assignment2Total);
        System.out.println("Subject Total: " + subjectTotal);
        System.out.println("==============================");
    }

    static int getLOGrade(String loName) {

        System.out.print(loName + " Grade (P / M / D): ");
        String grade = input.next().toUpperCase();

        switch (grade) {
            case "P": return 9;
            case "M": return 12;
            case "D": return 15;
            default:
                System.out.println("Invalid Grade! Try again.");
                return getLOGrade(loName);
        }
    }
}