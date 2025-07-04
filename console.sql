create database Student_management;
use Student_management;

create table Student(
                        Student_Id int primary key auto_increment,
                        Full_Name varchar(100) not null,
                        Email varchar(100) not null unique,
                        Phone_Number varchar(15) not null,
                        Register_Date date not null,
                        Status bit default 1
);

/*Procedure thực hiện lấy ấy danh sách sinh viên*/
DELIMITER &&
create procedure find_all_students()
begin
    select * from Student;
end;
DELIMITER &&
call find_all_students();

/*Procedure thực hiện thêm mới sinh viên*/
DELIMITER &&
create procedure create_student(
    name_in varchar(100),
    email_in varchar(100),
    phoneNumber_in varchar(15),
    registerDate_in date
)
begin
    insert into student(Full_Name, Email, Phone_Number, Register_Date)
        value (name_in,email_in,phoneNumber_in,registerDate_in);
end;
DELIMITER &&
call create_student('Nguyễn Văn An', 'nvan@gmail.com','095522001','2020-01-01');

/*Procedure thực hiện lấy thông tin sinh viên theo mã sinh viên*/
DELIMITER &&
create procedure find_student_by_id(id_in int)
begin
    select * from student where Student_Id = id_in;
end;
DELIMITER &&
call find_student_by_id(3);

/*Procedure thực hiện cập ập nhật ật thông tin sinh viên*/
DELIMITER &&
create procedure update_student(
    id_in int,
    name_in varchar(100),
    email_in varchar(100),
    phoneNumber_in varchar(15),
    registerDate_in date,
    status_in bit
)
begin
    update student
        set Student_Id = id_in,
            Full_Name = name_in,
            Email = email_in,
            Phone_Number = phoneNumber_in,
            Register_Date = registerDate_in,
            Status = status_in
        where Student_Id = id_in;
end;
DELIMITER &&
call update_student(3, 'Chu Văn An', 'cvan@gmail.com','095522001' ,'2020-01-01', 0 );

/*Procedure thực hiện xóa sinh viên*/
DELIMITER &&
create procedure delete_student(id_in int)
begin
    delete  from student where Student_Id = id_in;
end;
DELIMITER &&
call delete_student(1);

/*Procedure thực hiện tìm kiếm sinh viên theo tên tương đối*/
DELIMITER &&
create procedure find_student_by_name(name_in varchar(100))
begin
    declare name_search varchar(100);
    set name_search = concat('%', name_in, '%');
    select * from student where Full_Name like name_search;
end;
DELIMITER &&
call find_student_by_name('An');

/*Procedure kiểm tra email có tồn tại hay không*/
DELIMITER &&
create procedure is_email_exist(email_in varchar(100))
begin
    select * from student where Email = email_in;
end;
DELIMITER &&
call is_email_exist('')