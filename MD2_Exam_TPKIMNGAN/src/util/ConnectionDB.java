package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class ConnectionDB {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/Student_management";
    private static final String USER = "root";
    private static final String PASS = "@Attackonaki13";

    //Mở kết nối
    public static Connection openConnection() {
        Connection conn = null;
        try {
            Class.forName(DRIVER);
            //Dùng DriverManager để tạo 1 đối tượng
            conn = DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //Đóng kết nối
    public static void closeConnection(Connection conn, CallableStatement callst) {
        if (conn != null) {
            try {
                conn.close();
            }catch (Exception e) {
                throw  new RuntimeException(e);
            }
        }
        if (callst != null) {
            try {
                callst.close();
            }catch (Exception e) {
                throw  new RuntimeException(e);
            }
        }
    }

    //Test
    public static void main(String[] args) {
        Connection conn = openConnection();
        if (conn != null) System.out.println("Kết nối thành công");
        else System.err.println("Kết nối thất bại");
    }
}
