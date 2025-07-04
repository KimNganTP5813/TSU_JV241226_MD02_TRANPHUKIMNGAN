package DAO;

import Validate.StudentValidator;
import entity.Student;
import service.StudentManagementService;
import util.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDAO {
    public static List<Student> getAllStudents(){
        Connection conn = null;
        CallableStatement callst = null;
        List<Student> listStudents = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{call find_all_students()}");
            ResultSet rs = callst.executeQuery();
            listStudents = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student();
                student.setStudent_Id(rs.getInt("student_id"));
                student.setFull_Name(rs.getString("full_name"));
                student.setEmail(rs.getString("email"));
                student.setPhone_Number(rs.getString("phone_number"));
                student.setRegister_Date(LocalDate.parse(rs.getString("register_date")));
                student.setStatus(rs.getBoolean("status"));
                listStudents.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callst);
        }
        return listStudents;
    }

    public static boolean createStudent(Student student){
        Connection conn = null;
        CallableStatement callst = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{call create_student(?,?,?,?)}");
            callst.setString(1, student.getFull_Name());
            callst.setString(2, student.getEmail());
            callst.setString(3, student.getPhone_Number());
            callst.setDate(4, java.sql.Date.valueOf(student.getRegister_Date()));
            callst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callst);
        }
        return false;
    }

    public static boolean isStudentEmailExist(String email){
        Connection conn = null;
        CallableStatement callst = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{call is_email_exist(?)}");
            callst.setString(1, email);
            callst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callst);
        }
        return false;
    }

    public static Student findById(int id){
        Connection conn = null;
        CallableStatement callst = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{call find_student_by_id(?)}");
            callst.setInt(1, id);
            ResultSet rs = callst.executeQuery();
            student = new Student();
            if (rs.next()) {
                student.setStudent_Id(rs.getInt("Student_Id"));
                student.setFull_Name(rs.getString("Full_Name"));
                student.setEmail(rs.getString("Email"));
                student.setPhone_Number(rs.getString("Phone_Number"));
                student.setRegister_Date(LocalDate.parse(rs.getString("Register_Date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                student.setStatus(rs.getBoolean("Status"));
            }
        }catch (Exception e){
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callst);
        }
        return student;
    }

    public static boolean deleteStudent(int id){
        Connection conn = null;
        CallableStatement callst = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{call delete_student(?)}");
            callst.setInt(1, id);
            callst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callst);
        }
        return false;
    }

    public static boolean updateStudent(Student student){
        Connection conn = null;
        CallableStatement callst = null;
        try {
            conn = ConnectionDB.openConnection();
            callst = conn.prepareCall("{call update_student(?,?,?,?,?,?)}");
            callst.setInt(1, student.getStudent_Id());
            callst.setString(2, student.getFull_Name());
            callst.setString(3, student.getEmail());
            callst.setString(4, student.getPhone_Number());
            callst.setDate(5, java.sql.Date.valueOf(student.getRegister_Date()));
            callst.setBoolean(6, student.isStatus());
            callst.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            ConnectionDB.closeConnection(conn, callst);
        }
        return false;
    }
}
