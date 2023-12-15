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
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student)
    {
        connection = DBConnection.getConnection();
        try
        {
            addStudent = connection.prepareStatement("insert into app.student (studentid,firstname,lastname) values (?,?,?)");
            addStudent.setString(1,student.getStudentID());
            addStudent.setString(2,student.getFirstName());
            addStudent.setString(3,student.getLastName());
            addStudent.executeUpdate();
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<StudentEntry> getAllStudents()
    {
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        try
        {
            getAllStudents = connection.prepareStatement("select studentid,firstname,lastname from app.student order by lastname");
            resultSet = getAllStudents.executeQuery();
            
            while(resultSet.next())
            {
                students.add(new StudentEntry(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3)));
            }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return students;
        
    }
    
    public static StudentEntry getStudent(String StudentID)
    {
        connection = DBConnection.getConnection();
        StudentEntry student = new StudentEntry("","","");
        
        try
        {
            getStudent = connection.prepareStatement("select studentid,firstname,lastname from app.student where studentid = ?");
            getStudent.setString(1,StudentID);
            resultSet = getStudent.executeQuery(); 
            if (resultSet.next()) {
                student.setStudentID(resultSet.getString(1));
                student.setFirstName(resultSet.getString(2));
                student.setLastName(resultSet.getString(3));
            }

        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        return student;
        
    }
    public static void dropStudent(String StudentID)
    {
        connection = DBConnection.getConnection();
        
        try
        {
            dropStudent = connection.prepareStatement("delete from app.student where studentid = ?");
            dropStudent.setString(1,StudentID);
            dropStudent.executeUpdate();
      
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }

        
    }
}
