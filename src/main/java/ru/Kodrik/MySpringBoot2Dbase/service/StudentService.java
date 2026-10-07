package ru.Kodrik.MySpringBoot2Dbase.service;

import org.springframework.stereotype.Service;
import ru.Kodrik.MySpringBoot2Dbase.entity.Student;

import java.util.List;

@Service
public interface StudentService {

    List<Student>getAllStudents();

    Student saveStudent(Student student);

    Student getStudent(Long id);

    void deleteStudent(Long id);
}
