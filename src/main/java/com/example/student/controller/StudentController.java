package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/sort/id")
    public ResponseEntity<List<Student>> getStudentsSortedById() {
        return ResponseEntity.ok(studentService.sortById());
    }

    @GetMapping("/sort/cgpa")
    public ResponseEntity<List<Student>> getStudentsSortedByCgpa() {
        return ResponseEntity.ok(studentService.sortByCgpa());
    }

    @GetMapping("/departments/unique")
    public ResponseEntity<Set<String>> getUniqueDepartments() {
        return ResponseEntity.ok(studentService.getUniqueDepartments());
    }

    @GetMapping("/recent")
    public ResponseEntity<List<Student>> getRecentStudents() {
        return ResponseEntity.ok(studentService.getRecentStudents());
    }

    @GetMapping("/recent/peek")
    public ResponseEntity<Student> peekRecentStudent() {
        Student student = studentService.peekRecentStudent();
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/recent/pop")
    public ResponseEntity<Student> popRecentStudent() {
        Student student = studentService.popRecentStudent();
        if (student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.notFound().build();
    }
}
