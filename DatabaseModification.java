package sample;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;



public class DatabaseModification {
    public static MyPieChart P = new MyPieChart(0, 0, 0, 0);

    public static void main(String args[]) {
        try {

            Connection conn = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/students", "root", "JavaClassDatabaseProject!@2020");

            if (conn != null) {
                System.out.println("We have connected to our database!");
                DatabaseModification.createTables(conn);

                insertStudents(conn, new students(1, "Alex", "Matzoros", "alex@gmail.com", "M"));
                insertStudents(conn, new students(2, "Jane", "Jetson", "Jane@gmail.com", "F"));
                insertStudents(conn, new students(3, "Fred", "Flinstone", "Fred@gmail.com", "M"));
                insertStudents(conn, new students(4, "Bugs", "Bunny", "Bugs@gmail.com", "M"));
                insertStudents(conn, new students(5, "Velma", "Dinkley", "Velma@gmail.com", "F"));
                insertStudents(conn, new students(6, "Lisa", "Simpson", "Lisa@gmail.com", "F"));
                insertStudents(conn, new students(7, "Jerry", "Mouse", "alex@gmail.com", "M"));
                insertStudents(conn, new students(8, "Jane", "Jetson", "Jane@gmail.com", "F"));
                insertStudents(conn, new students(9, "Fred", "Flinstone", "Fred@gmail.com", "M"));
                insertStudents(conn, new students(15, "Barney", "Rubble", "Barney@gmail.com", "M"));
                insertStudents(conn, new students(20, "Lola", "Bunny", "Lola@gmail.com", "F"));
                insertStudents(conn, new students(23, "Woody", "Woodpecker", "WW@gmail.com", "M"));
                insertStudents(conn, new students(43, "Minnie", "Mouse", "MM@gmail.com", "F"));
                insertStudents(conn, new students(56, "Spongebob", "Squarepants", "SpongeS@gmail.com", "M"));

                insertCourse(conn, new courses("CSC 22100", "Software Design Laboratory", "Computer Science"));
                insertCourse(conn, new courses("CSC 33600", "Database Systems", "Computer Science"));
                insertCourse(conn, new courses("EE 30600", "Linear Systems Analysis II", "Electrical Engineering"));
                insertCourse(conn, new courses("EE 24100", "Electronics", "Electrical Engineering"));
                insertCourse(conn, new courses("HIST 20600", "Early Modern Europe", "History"));

                insertClass(conn, new classes("CSC 33600", 3, 42311, 2020, "Spring", "A"));
                insertClass(conn, new classes("EE 24100", 5, 42001, 2020, "Spring", "F"));
                insertClass(conn, new classes("EE 24100", 2, 42001, 2020, "Spring", "C"));
                insertClass(conn, new classes("HIST 20600", 3, 32199, 2020, "Spring", "A"));
                //some students in CSC 22100 with different sections
                insertClass(conn, new classes("CSC 22100", 1, 11023, 2020, "Spring", "A"));
                insertClass(conn, new classes("CSC 22100", 2, 11024, 2020, "Spring", "F"));
                insertClass(conn, new classes("CSC 22100", 3, 11024, 2020, "Spring", "C"));
                insertClass(conn, new classes("CSC 22100", 4, 11024, 2020, "Spring", "D"));
                insertClass(conn, new classes("CSC 22100", 5, 11024, 2020, "Spring", "F"));
                insertClass(conn, new classes("CSC 22100", 6, 11024, 2020, "Spring", "W"));
                insertClass(conn, new classes("CSC 22100", 7, 11024, 2020, "Spring", "A"));
                insertClass(conn, new classes("CSC 22100", 8, 11023, 2020, "Spring", "W"));
                insertClass(conn, new classes("CSC 22100", 9, 11023, 2020, "Spring", "F"));
                insertClass(conn, new classes("CSC 22100", 15, 11023, 2020, "Spring", "F"));
                insertClass(conn, new classes("CSC 22100", 20, 11023, 2020, "Spring", "A"));
                insertClass(conn, new classes("CSC 22100", 23, 11023, 2020, "Spring", "A"));
                insertClass(conn, new classes("CSC 22100", 43, 11023, 2020, "Spring", "A"));
                insertClass(conn, new classes("CSC 22100", 56, 11023, 2020, "Spring", "A"));

                extract_data(conn);
                create_pie_chart(conn);
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("except 1");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.println("except 2");
            ex.printStackTrace();
        }
    }

    public static void createTables(Connection conn) {
        try {
            PreparedStatement statement1 = conn.prepareStatement(
                    "CREATE TABLE STUDENTS " +
                            "(studentID INTEGER not NULL AUTO_INCREMENT, " +
                            " firstName VARCHAR(255), " +
                            " lastName VARCHAR(255), " +
                            " email VARCHAR(255), " +
                            " sex ENUM('M','F'), " +
                            " PRIMARY KEY ( studentID ))");

            PreparedStatement statement2 = conn.prepareStatement(
                    "CREATE TABLE COURSES" +
                            "(courseID VARCHAR(255) not NULL, " +
                            " courseTitle VARCHAR(255), " +
                            " department VARCHAR(255), " +
                            " PRIMARY KEY ( courseID ))");

            PreparedStatement statement3 = conn.prepareStatement(
                    "CREATE TABLE CLASSES" +
                            "( courseID VARCHAR(255) not NULL, " +
                            " studentID INTEGER not NULL, " +
                            " section INTEGER not NULL, " +
                            " semester VARCHAR(255), " +
                            " year INTEGER, " +
                            " GPA ENUM('A','B','C','D','F','W'), " +
                            " PRIMARY KEY ( courseID, studentID, section )," +
                            " FOREIGN KEY (courseID) references COURSES(courseID), " +
                            " FOREIGN KEY (studentID) references STUDENTS(studentID) )");
            statement1.execute();
            statement2.execute();
            statement3.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertStudents(Connection conn, students s) {
        try {
            PreparedStatement student = conn.prepareStatement(
                    "INSERT STUDENTS (studentID, firstname, lastName, email, sex)" + " VALUES(?,?,?,?,?)");
            student.setInt(1, s.getStudentID());
            student.setString(2, s.getFirstName());
            student.setString(3, s.getLastName());
            student.setString(4, s.getEmail());
            student.setString(5, s.getSex());
            student.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertCourse(Connection conn, courses c) {
        try {
            PreparedStatement course = conn.prepareStatement(
                    "INSERT COURSES (courseID, courseTitle, department)" + "VALUES(?,?,?)");
            course.setString(1, c.getCourseID());
            course.setString(2, c.getCourseTitle());
            course.setString(3, c.getDepartment());
            course.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insertClass(Connection conn, classes c) {
        try {
            String t = "INSERT CLASSES(courseID, studentID, section, semester, year, GPA)" + "VALUES(?,?,?,?,?,?)";
            PreparedStatement classs = conn.prepareStatement(t);
            classs.setString(1, c.getCourseID());
            classs.setInt(2, c.getStudentID());
            classs.setInt(3, c.getSection());
            classs.setString(4, c.getSemester());
            classs.setInt(5, c.getYear());
            classs.setString(6, c.getGPA());
            classs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void extract_data(Connection conn) {
        //# of students enrolled in csc 22100 in the spring 2020 semester for each letter grade
        try {
            String prpst = "SELECT classes.GPA, COUNT(GPA) FROM CLASSES WHERE  section = '11023' and courseID = 'CSC 22100'" +
                    "or section = '11024' and year = '2020' and semester = 'Spring' GROUP by GPA ORDER BY GPA";
            PreparedStatement hold = conn.prepareStatement(prpst);
            ResultSet gpa_result = hold.executeQuery();
            ResultSetMetaData data = gpa_result.getMetaData();
            int column = 1;
            int count = 0;
            int max_col = data.getColumnCount();
            String print = "";
            if (max_col > 0) {
                System.out.println("The GPA's for CSC 22100");
                System.out.println("  GPA" + " " + "Frequency");
                System.out.println("===================");
                while (gpa_result.next()) {
                    for (int col = 1; col <= max_col; col++) {
                        //only want to print after for loop is done.
                        if (gpa_result.getString(col) != null)
                            print += " | " + gpa_result.getString(col) + " | ";
                        if (col == 2) {
                            count += Integer.parseInt(gpa_result.getString(col));
                        }
                    }
                    System.out.println(print);
                    System.out.println("--------------------");
                    print = "";
                }
                System.out.println(" Total = " + count);
            } else
                System.out.println("No results found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void create_pie_chart(Connection conn) {
        Draw_ALL.pie_chart_data(conn);
    }

    public static class Draw_ALL extends Application {
        private static Map<String, Double> histogram = new LinkedHashMap<>();
        public static void pie_chart_data(Connection conn) {
            try {
                String all_GPAs = "SELECT classes.GPA, COUNT(GPA) FROM CLASSES GROUP by GPA ORDER BY GPA";
                PreparedStatement holder = conn.prepareStatement(all_GPAs);
                ResultSet gpa_results = holder.executeQuery();
                Integer value = 0;
                Double helper = 0.0;
                String name = "";
                while (gpa_results.next()) {
                    name = gpa_results.getString(1);
                    value = gpa_results.getInt(2);
                    helper = value.doubleValue();
                    histogram.put(name, helper);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            P.setProbabilites(histogram);
            launch();
        }
        @Override
        public void start(Stage primaryStage) {
            Group root = new Group();
            primaryStage.setTitle("Project 4");
            Canvas canvas = new Canvas(675, 300);
            GraphicsContext gx = canvas.getGraphicsContext2D();
            root.getChildren().add(canvas);
            Label label1 = new Label("N = ");
            TextField textField = new TextField(" ");
            HBox hb = new HBox();
            hb.getChildren().addAll(label1, textField);
            hb.setSpacing(10);
            root.getChildren().add(hb);
            Button draw = new Button("Draw");
            hb.getChildren().add(draw);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

            double canvaswidth = canvas.getWidth();
            double canvasheight = canvas.getHeight();
            double radius;
            if (canvasheight > canvaswidth) {
                radius = canvaswidth / 2;
            } else radius = canvasheight / 2;

            P.setX(canvaswidth / 2);
            P.setY(canvasheight / 2);
            P.setHeight(radius);
            P.setWidth(radius);
            draw.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    assert Integer.parseInt(textField.getText()) < 6 && Integer.parseInt(textField.getText()) > 0;
                    if (Integer.parseInt(textField.getText()) > 6)
                        P.setN(6);
                    else if (Integer.parseInt(textField.getText()) < 0)
                        P.setN(0);
                    else {
                        P.setN(Integer.parseInt(textField.getText()));
                    }
                    gx.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                    P.draw(gx);
                }
            });
        }
    }
}

