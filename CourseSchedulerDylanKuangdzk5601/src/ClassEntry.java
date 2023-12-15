/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thicc
 */
public class ClassEntry {
    private String Semester;
    private String CourseCode;
    private Integer Seats;

    public ClassEntry(String Semester, String CourseCode, Integer Seats) {
        this.Semester = Semester;
        this.CourseCode = CourseCode;
        this.Seats = Seats;
    }

    public String getSemester() {
        return Semester;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public Integer getSeats() {
        return Seats;
    }

    public void setSemester(String Semester) {
        this.Semester = Semester;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public void setSeats(Integer Seats) {
        this.Seats = Seats;
    }
    
}
