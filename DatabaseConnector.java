package sample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector
{
    /** Establishes the connection to the database and prints an
     * appropriate confirmation message.
     */
    public static void main (String args[])
    {
        try
        {
            Connection conn = null;
            // Loads the class object for the mysql driver into the Drive

            Class.forName("com.mysql.cj.jdbc.Driver");

          //  conn = DriverManager.getConnection("jdbc:mysql://comtor.org/"+
            //        "javafoundations?user=jf2e&password=hirsch");

           conn = DriverManager.getConnection("jdbc:mysql://localhost/students", "root", "JavaClassDatabaseProject!@2020");
            if(conn != null)
            {
                System.out.println("We have connected to our database!");
                Statement stmt = null;
                stmt = conn.createStatement();

                String sql = "CREATE TABLE STUDENTS " +
                        "(studentID INTEGER not NULL AUTO_INCREMENT, " +
                        " firstName VARCHAR(255), " +
                        " lastName VARCHAR(255), " +
                        " email INTEGER, " +
                        " sex CHAR, " +
                        " PRIMARY KEY ( studentID ))";

                String sql2 = "CREATE TABLE COURSES" +
                        "(courseID INTEGER not NULL, " +
                        " courseTitle VARCHAR(255), " +
                        " department VARCHAR(255), " +
                        " PRIMARY KEY ( courseID ))";

                String sql3 = "CREATE TABLE CLASSES" +
                        "( courseID INTEGER not NULL, " +
                        " studentID INTEGER not NULL, " +
                        " section VARCHAR(255) not NULL, " +
                        " year INTEGER, " +
                        " GPA FLOAT, " +
                        " PRIMARY KEY ( section )," +
                        " FOREIGN KEY (courseID) references COURSES(courseID), " +
                        " FOREIGN KEY (studentID) references STUDENTS(studentID) )";


                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql2);
                stmt.executeUpdate(sql3);
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("except 1");
            ex.printStackTrace();
        }

        catch(Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.println("except 2");
            ex.printStackTrace();
        }
    }
}