package com.smartexam.backend.controller;

import com.smartexam.backend.dto.StudentDto;
import com.smartexam.backend.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    // Create Student
    @PostMapping
    public ResponseEntity<StudentDto> saveStudent(@Valid @RequestBody StudentDto studentDto) {

        StudentDto savedStudent = studentService.saveStudent(studentDto);

        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    // Get All Students
    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {

        List<StudentDto> students = studentService.getAllStudents();

        return ResponseEntity.ok(students);
    }

    // Get Student By Id
    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {

        StudentDto student = studentService.getStudentById(id);

        return ResponseEntity.ok(student);
    }

    // Update Student
    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(
            @PathVariable Long id,
            @Valid @RequestBody StudentDto studentDto) {

        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);

        return ResponseEntity.ok(updatedStudent);
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        studentService.deleteStudent(id);

        return ResponseEntity.ok("Student deleted successfully.");
    }
}