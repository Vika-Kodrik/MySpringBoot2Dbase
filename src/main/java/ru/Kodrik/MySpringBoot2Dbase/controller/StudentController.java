package ru.Kodrik.MySpringBoot2Dbase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.Kodrik.MySpringBoot2Dbase.dao.OperationResult;
import ru.Kodrik.MySpringBoot2Dbase.entity.Student;
import ru.Kodrik.MySpringBoot2Dbase.service.StudentService;

import java.util.List;

@RestController
@RequestMapping
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<OperationResult<List<Student>>> allStudents() {
        List<Student> allStudents = studentService.getAllStudents();
        if (allStudents.isEmpty()) {
            return ResponseEntity.ok(OperationResult.success("Список студентов пуст", allStudents));
        }
        return ResponseEntity.ok(OperationResult.success(allStudents));
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<OperationResult<Student>> getStudent(@PathVariable("id") Long id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return ResponseEntity.status(404)
                    .body(OperationResult.failure("Студент с id=" + id + " не найден"));
        }
        return ResponseEntity.ok(OperationResult.success(student));
    }

    @PostMapping("/students")
    public ResponseEntity<OperationResult<Student>> saveStudent(@RequestBody Student student) {
        try {
            Student saved = studentService.saveStudent(student);
            return ResponseEntity.ok(OperationResult.success("Студент добавлен", saved));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(OperationResult.failure("Ошибка при добавлении: " + e.getMessage()));
        }
    }

    @PutMapping("/students")
    public ResponseEntity<OperationResult<Student>> updateStudent(@RequestBody Student student) {
        try {
            Student existing = studentService.getStudent(student.getId());
            if (existing == null) {
                return ResponseEntity.status(404)
                        .body(OperationResult.failure("Студент с id=" + student.getId() + " не найден"));
            }
            studentService.saveStudent(student);
            return ResponseEntity.ok(OperationResult.success("Студент обновлён", student));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(OperationResult.failure("Ошибка при обновлении: " + e.getMessage()));
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<OperationResult<Void>> deleteStudent(@PathVariable("id") Long id) {
        try {
            Student existing = studentService.getStudent(id);
            if (existing == null) {
                return ResponseEntity.status(404)
                        .body(OperationResult.failure("Студент с id=" + id + " не найден"));
            }
            studentService.deleteStudent(id);
            return ResponseEntity.ok(OperationResult.success("Студент удалён", null));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(OperationResult.failure("Ошибка при удалении: " + e.getMessage()));
        }
    }
}
