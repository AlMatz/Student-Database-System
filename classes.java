package sample;

public class classes {
    private String CourseID;
    private int StudentID;
    private int section;
    private int year;
    private String semester;
    private String GPA;
    //constructor
    public classes(String courseID, int studentID, int section, int year, String semester, String GPA) {
        CourseID = courseID;
        StudentID = studentID;
        this.section = section;
        this.year = year;
        this.semester = semester;
        this.GPA = GPA; }

    //setters and getters
    public String getCourseID() {
        return CourseID;
    }
    public void setCourseID(String courseID) {
        CourseID = courseID;
    }
    public int getStudentID() {
        return StudentID;
    }
    public void setStudentID(int studentID) {
        StudentID = studentID;
    }
    public int getSection() {
        return section;
    }
    public void setSection(int section) {
        this.section = section;
    }
    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getGPA() {
        return GPA;
    }
    public void setGPA(String GPA) {
        this.GPA = GPA;
    }
}
