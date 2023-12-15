/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thicc
 */
public class ClassDescription {
    private String CourseCode;
    private String Description;
    private Integer Seats;

    public ClassDescription(String CourseCode, String Description, Integer Seats) {
        this.CourseCode = CourseCode;
        this.Description = Description;
        this.Seats = Seats;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public Integer getSeats() {
        return Seats;
    }

    public void setSeats(Integer Seats) {
        this.Seats = Seats;
    }
    
}
