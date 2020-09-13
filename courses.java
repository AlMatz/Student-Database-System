package sample;

public class courses {
    private String courseID;
    private String courseTitle;
    private String department;

    public courses(String courseID, String courseTitle, String department) {
        this.courseID = courseID;
        this.courseTitle = courseTitle;
        this.department = department;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
