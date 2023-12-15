/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author thicc
 */
public class CourseEntry {
    private String CourseCode;
    private String Description;

    public CourseEntry(String CourseCode, String Description) {
        this.CourseCode = CourseCode;
        this.Description = Description;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getDescription() {
        return Description;
    }

    public void setCourseCode(String CourseCode) {
        this.CourseCode = CourseCode;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }
    
}
