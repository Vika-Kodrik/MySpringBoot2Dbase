package ru.Kodrik.MySpringBoot2Dbase.dao;

import org.springframework.stereotype.Repository;
import ru.Kodrik.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Repository
public interface StudentDAO {

    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);
}
