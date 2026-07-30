package com.smartexam.backend.controller;

import com.smartexam.backend.dto.QuestionDto;
import com.smartexam.backend.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    // Create Question
    @PostMapping
    public ResponseEntity<QuestionDto> saveQuestion(@Valid @RequestBody QuestionDto questionDto) {

        QuestionDto savedQuestion = questionService.saveQuestion(questionDto);

        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    // Get All Questions
    @GetMapping
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {

        List<QuestionDto> questions = questionService.getAllQuestions();

        return ResponseEntity.ok(questions);
    }

    // Get Question By ID
    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) {

        QuestionDto question = questionService.getQuestionById(id);

        return ResponseEntity.ok(question);
    }

    // Update Question
    @PutMapping("/{id}")
    public ResponseEntity<QuestionDto> updateQuestion(
            @PathVariable Long id,
            @Valid @RequestBody QuestionDto questionDto) {

        QuestionDto updatedQuestion = questionService.updateQuestion(id, questionDto);

        return ResponseEntity.ok(updatedQuestion);
    }

    // Delete Question
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long id) {

        questionService.deleteQuestion(id);

        return ResponseEntity.ok("Question deleted successfully.");
    }
}