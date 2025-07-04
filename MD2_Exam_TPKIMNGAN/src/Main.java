import service.StudentManagementService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("*******STUDENT MANAGEMENT*******");
            System.out.println("1. Danh sách sinh viên");
            System.out.println("2. Thêm mới sinh viên");
            System.out.println("3. Cập nhật sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("5. Tìm kiếm sinh viên theo tên");
            System.out.println("6. Sắp ắp xếp sinh viên theo ngày đăng ký");
            System.out.println("7. Thoát");
            System.out.println("Lựa chọn của bạn");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    StudentManagementService.displayStudents();
                    break;
                case 2:
                    StudentManagementService.createStudent(scanner);
                    break;
                case 3:
                    StudentManagementService.updateStudent(scanner);
                    break;
                case 4:
                    StudentManagementService.deleteStudent(scanner);
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chon từ 1 đến 7.");
            }
        } while (true);
    }
}