package com.smartexam.backend.controller;

import com.smartexam.backend.dto.ResultDto;
import com.smartexam.backend.service.ResultService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/results")
@RequiredArgsConstructor
public class ResultController {

    private final ResultService resultService;

    // Create Result
    @PostMapping
    public ResponseEntity<ResultDto> saveResult(
            @Valid @RequestBody ResultDto resultDto) {

        ResultDto savedResult = resultService.saveResult(resultDto);

        return new ResponseEntity<>(savedResult, HttpStatus.CREATED);
    }

    // Get All Results
    @GetMapping
    public ResponseEntity<List<ResultDto>> getAllResults() {

        List<ResultDto> results = resultService.getAllResults();

        return ResponseEntity.ok(results);
    }

    // Get Result By ID
    @GetMapping("/{id}")
    public ResponseEntity<ResultDto> getResultById(@PathVariable Long id) {

        ResultDto result = resultService.getResultById(id);

        return ResponseEntity.ok(result);
    }

    // Update Result
    @PutMapping("/{id}")
    public ResponseEntity<ResultDto> updateResult(
            @PathVariable Long id,
            @Valid @RequestBody ResultDto resultDto) {

        ResultDto updatedResult =
                resultService.updateResult(id, resultDto);

        return ResponseEntity.ok(updatedResult);
    }

    // Delete Result
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResult(@PathVariable Long id) {

        resultService.deleteResult(id);

        return ResponseEntity.ok("Result deleted successfully.");
    }
}