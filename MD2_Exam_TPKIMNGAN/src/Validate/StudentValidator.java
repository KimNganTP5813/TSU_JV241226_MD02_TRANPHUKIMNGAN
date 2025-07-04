package Validate;

import DAO.StudentDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class StudentValidator {
    public static boolean validateStudentId(String data){
        if (data!=null && !data.isEmpty()) {
            try {
                int studentId = Integer.parseInt(data);
                return true;
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return false;
    }

    public static boolean validateStudentName(String data){
        if (data!=null && !data.isEmpty()) {
            if (data.trim().length()<=100) return true;
        }
        return false;
    }

    public static boolean validateStudentEmail(String data){
        if (data!=null && !data.isEmpty()) {
            if (data.trim().length()<=100) {
                if (StudentDAO.isStudentEmailExist(data)) return true;
            }
        }
        return false;
    }

    public static boolean validateStudentPhone(String data){
        if (data!=null && !data.isEmpty()) {
            if (data.trim().length()<=15) return true;
        }
        return false;
    }

    public static boolean validateRegisterDate(String data){
        if (data!=null && !data.isEmpty()) {
            try {
                LocalDate localDate = LocalDate.parse(data, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (Exception e) {
                e.fillInStackTrace();
            }
        }
        return false;
    }

    public static boolean validateStatus(String data){
        if (data!=null && !data.isEmpty()) {
            if (data.equalsIgnoreCase("true")||data.equalsIgnoreCase("false")) return true;
        }
        return false;
    }

}
