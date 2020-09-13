package sample;

public class students {
    private int studentID;
    private String firstName;
    private String lastName;
    private String email;
    private String sex;

    //constructor
    public students(int studentID, String firstName, String lastName, String email, String sex) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.sex = sex;    }

    //setters and getters
    public int getStudentID() {        return studentID;    }
    public void setStudentID(int studentID) {        this.studentID = studentID;    }
    public String getFirstName() {        return firstName;    }
    public void setFirstName(String firstName) {        this.firstName = firstName;    }
    public String getLastName() {        return lastName;    }
    public void setLastName(String lastName) {        this.lastName = lastName;    }
    public String getEmail() {        return email;    }
    public void setEmail(String email) {        this.email = email;    }
    public String getSex() {        return sex;    }
    public void setSex(String sex) {        this.sex = sex;    }
}
