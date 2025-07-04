package service;

import DAO.StudentDAO;
import Validate.StudentValidator;
import entity.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class StudentManagementService {
    public static void displayStudents(){
        List<Student> studentsList = StudentDAO.getAllStudents();
        studentsList.forEach(System.out::println);
    }

    public static void createStudent(Scanner scanner){
        Student student = new Student();
        student.inputData(scanner);
        boolean result = StudentDAO.createStudent(student);
        if(result) System.out.println("Thêm mới thành công");
        else System.err.println("Xảy ra lỗi trong quá trình thêm mới");
    }

    public static void updateStudent(Scanner scanner){
        System.out.println("Nhập mã sinh viên cần cập nhật");
        String id = scanner.next();
        if (StudentValidator.validateStudentId(id)) {
            int StudentId = Integer.parseInt(id);
            Student student = StudentDAO.findById(StudentId);
            if (student != null){
                boolean isExit = true;
                do {
                    System.out.println("1. Cập nhật tên sinh viên");
                    System.out.println("2. Cập nhật email sinh viên");
                    System.out.println("3. Cập nhật sđt của sinh viên");
                    System.out.println("4. Cập nhật ngày đăng ký");
                    System.out.println("5. Cập nhật trạng thái");
                    System.out.println("6. Thoát");
                    System.out.println("Lựa chọn của bạn:");
                    int choice = Integer.parseInt(scanner.next());
                    switch (choice) {
                        case 1:
                            System.out.println("Nhập tên sinh viên");
                            String name = scanner.nextLine();
                            if (StudentValidator.validateStudentName(name)) {
                                student.setFull_Name(name);
                            } else System.err.println("Tên sinh viên tối đa 100 ký tự");
                            break;
                        case 2:
                            System.out.println("Nhập email sinh viên");
                            String email = scanner.nextLine();
                            if (StudentValidator.validateStudentEmail(email)) {
                                student.setEmail(email);
                            } else System.err.println("Email sinh viên tối đa 100 ký tự và không được trùng lặp");
                            break;
                        case 3:
                            System.out.println("Nhập sđt sinh viên");
                            String phone = scanner.nextLine();
                            if (StudentValidator.validateStudentPhone(phone)) {
                                student.setPhone_Number(phone);
                            } else System.err.println("Sđt sinh viên tối đa 15 ký tự");
                            break;
                        case 4:
                            System.out.println("Nhập ngày đăng ký");
                            String date = scanner.nextLine();
                            if (StudentValidator.validateRegisterDate(date)) {
                                student.setRegister_Date(LocalDate.parse(date));
                            } else System.err.println("Ngày đăng ký có định dạng yyyy-MM-dd");
                            break;
                        case 5:
                            System.out.println("Nhập trạng thái sinh viên");
                            String status = scanner.nextLine();
                            if (StudentValidator.validateStatus(status)) {
                                student.setStatus(Boolean.parseBoolean(status));
                            } else System.err.println("Trạng thái không đúng định dạng");
                            break;
                        case 6:
                            isExit = true;
                            break;
                            default:
                                System.out.println("Hãy chọn từ 1 đến 6");
                    }
                } while (isExit);
                // Cập nhật
                boolean result = StudentDAO.updateStudent(student);
                if (result) System.out.println("Cập nhật thành công");
            } else System.err.println("Không có mã sinh viên");
        } else System.err.println("Mã sinh viên không đúng định dạng");
    }

    public static void deleteStudent(Scanner scanner){
        System.out.println("Nhập mã sinh viên");
        String id = scanner.nextLine();
        if (StudentValidator.validateStudentId(id)) {
            int studentId = Integer.parseInt(id);
            Student student = StudentDAO.findById(studentId);
            if (student != null) {
                //Xóa
                boolean result = StudentDAO.deleteStudent(studentId);
                if (result) System.out.println("Xóa thành công");
                else System.err.println("Xảy ra lỗi trong quá trình xóa");
                } else System.err.println("Không tồn tại mã sinh viên");
            } else System.err.println("Mã sinh viên không đúng định dạng");
    }
}
