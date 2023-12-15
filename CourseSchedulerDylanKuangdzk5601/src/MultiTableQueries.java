/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author acv
 */
public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getAllClassDescription;
    private static PreparedStatement getScheduledStudentsByClass;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static ResultSet resultSet;

    
    public static ArrayList<ClassDescription> getAllClassDescriptions(String semester)
    {
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> descriptions = new ArrayList<ClassDescription>();
        try
        {
            getAllClassDescription = connection.prepareStatement("select app.class.coursecode,description,seats from app.course,app.class where semester = ? and app.class.coursecode = app.course.coursecode order by app.class.coursecode");
            getAllClassDescription.setString(1, semester);
            resultSet = getAllClassDescription.executeQuery();

            
            while(resultSet.next())
            {
                descriptions.add(new ClassDescription(resultSet.getString(1),resultSet.getString(2),resultSet.getInt(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return descriptions;
        
    }  
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> schedules = new ArrayList<StudentEntry>();
        try
        {
            getScheduledStudentsByClass = connection.prepareStatement("select app.student.studentid,firstname,lastname from app.student,app.schedule "
                    + "where app.student.studentid = app.schedule.studentid and semester = ? and coursecode = ? and status = ?");
            getScheduledStudentsByClass.setString(1, semester);
            getScheduledStudentsByClass.setString(2,courseCode);
            getScheduledStudentsByClass.setString(3,"scheduled");
            
            resultSet = getScheduledStudentsByClass.executeQuery();

            
            while(resultSet.next())
            {
                schedules.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedules;
      }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode)
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> schedules = new ArrayList<StudentEntry>();
        try
        {
            getWaitlistedStudentsByClass = connection.prepareStatement("select app.student.studentid,firstname,lastname from app.student,app.schedule "
                    + "where app.student.studentid = app.schedule.studentid and semester = ? and coursecode = ? and status = ? order by app.schedule.timestamp");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2,courseCode);
            getWaitlistedStudentsByClass.setString(3,"waitlisted");
            resultSet = getWaitlistedStudentsByClass.executeQuery();

            
            while(resultSet.next())
            {
                schedules.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return schedules;
      }
}
        
