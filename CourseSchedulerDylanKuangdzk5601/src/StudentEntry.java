/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thicc
 */
public class StudentEntry {
    private String StudentID;
    private String FirstName;
    private String Lastname;

    public StudentEntry(String StudentID, String FirstName, String Lastname) {
        this.StudentID = StudentID;
        this.FirstName = FirstName;
        this.Lastname = Lastname;
    }

    public String getStudentID() {
        return StudentID;
    }

    public void setStudentID(String StudentID) {
        this.StudentID = StudentID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return Lastname;
    }

    public void setLastName(String Lastname) {
        this.Lastname = Lastname;
    }

    StudentEntry getStudent(String studentID) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
