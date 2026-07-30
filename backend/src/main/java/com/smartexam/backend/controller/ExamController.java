package com.smartexam.backend.controller;

import com.smartexam.backend.dto.ExamDto;
import com.smartexam.backend.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    // Create Exam
    @PostMapping
    public ResponseEntity<ExamDto> saveExam(@Valid @RequestBody ExamDto examDto) {

        ExamDto savedExam = examService.saveExam(examDto);

        return new ResponseEntity<>(savedExam, HttpStatus.CREATED);
    }

    // Get All Exams
    @GetMapping
    public ResponseEntity<List<ExamDto>> getAllExams() {

        List<ExamDto> exams = examService.getAllExams();

        return ResponseEntity.ok(exams);
    }

    // Get Exam By ID
    @GetMapping("/{id}")
    public ResponseEntity<ExamDto> getExamById(@PathVariable Long id) {

        ExamDto exam = examService.getExamById(id);

        return ResponseEntity.ok(exam);
    }

    // Update Exam
    @PutMapping("/{id}")
    public ResponseEntity<ExamDto> updateExam(
            @PathVariable Long id,
            @Valid @RequestBody ExamDto examDto) {

        ExamDto updatedExam = examService.updateExam(id, examDto);

        return ResponseEntity.ok(updatedExam);
    }

    // Delete Exam
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExam(@PathVariable Long id) {

        examService.deleteExam(id);

        return ResponseEntity.ok("Exam deleted successfully.");
    }
}