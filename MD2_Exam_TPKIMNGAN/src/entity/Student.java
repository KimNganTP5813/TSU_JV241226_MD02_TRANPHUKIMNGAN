package entity;

import Validate.StudentValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Student {
    private int Student_Id;
    private String Full_Name;
    private String Email;
    private String Phone_Number;
    private LocalDate Register_Date;
    private boolean Status;

    public Student() {
    }

    public Student(int student_Id, String full_Name, String email, String phone_Number, LocalDate register_Date, boolean status) {
        Student_Id = student_Id;
        Full_Name = full_Name;
        Email = email;
        Phone_Number = phone_Number;
        Register_Date = register_Date;
        Status = status;
    }

    public int getStudent_Id() {
        return Student_Id;
    }

    public void setStudent_Id(int student_Id) {
        Student_Id = student_Id;
    }

    public String getFull_Name() {
        return Full_Name;
    }

    public void setFull_Name(String full_Name) {
        Full_Name = full_Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone_Number() {
        return Phone_Number;
    }

    public void setPhone_Number(String phone_Number) {
        Phone_Number = phone_Number;
    }

    public LocalDate getRegister_Date() {
        return Register_Date;
    }

    public void setRegister_Date(LocalDate register_Date) {
        Register_Date = register_Date;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    @Override
    public String toString() {
        return String.format("Mã học viên: %s\nHọ tên học viên: %s\nEmail đăng ký: %s\nSĐT học viên: %s\nNgày đăng ký: %s\nTrạng thái: %s\n________",
                Student_Id, Full_Name, Email, Phone_Number, Register_Date, Status ? "Hoạt động" : "Không hoạt động");
    }

    public void inputData(Scanner scanner){
        System.out.println("Nhập tên sinh viên");
        do {
            String studentName = scanner.nextLine();
            if (StudentValidator.validateStudentName(studentName)) {
                this.Full_Name = studentName;
                break;
            }
            System.err.println("Tên học sinh tối đa 100 ký tự");
        } while (true);

        System.out.println("Nhập email sinh viên");
        do {
            String studentEmail = scanner.nextLine();
            if (StudentValidator.validateStudentEmail(studentEmail)) {
                this.Email = studentEmail;
                break;
            }
            System.err.println("Email không thể trùng và tối đa 100 ký tự");
        } while (true);

        System.out.println("Nhập số điện thoại sinh viên");
        do {
            String studentPhone = scanner.nextLine();
            if (StudentValidator.validateStudentPhone(studentPhone)) {
                this.Phone_Number = studentPhone;
                break;
            }
            System.err.println("Số điện thoại không được để trống");
        } while (true);

        System.out.println("Nhập ngày đăng kí của sinh viên");
        do {
            String registerDate = scanner.nextLine();
            if (StudentValidator.validateRegisterDate(registerDate)) {
                this.Register_Date = LocalDate.parse(registerDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                break;
            }
            System.err.println("Ngày đăng kí phải có định dạng yyyy-MM-dd");
        } while (true);
    }
}
