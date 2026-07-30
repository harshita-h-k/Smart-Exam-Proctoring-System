package com.smartexam.backend.controller;

import com.smartexam.backend.dto.ViolationDto;
import com.smartexam.backend.service.ViolationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/violations")
@RequiredArgsConstructor
public class ViolationController {

    private final ViolationService violationService;

    // Create Violation
    @PostMapping
    public ResponseEntity<ViolationDto> saveViolation(
            @Valid @RequestBody ViolationDto violationDto) {

        ViolationDto savedViolation =
                violationService.saveViolation(violationDto);

        return new ResponseEntity<>(savedViolation, HttpStatus.CREATED);
    }

    // Get All Violations
    @GetMapping
    public ResponseEntity<List<ViolationDto>> getAllViolations() {

        List<ViolationDto> violations =
                violationService.getAllViolations();

        return ResponseEntity.ok(violations);
    }

    // Get Violation By ID
    @GetMapping("/{id}")
    public ResponseEntity<ViolationDto> getViolationById(
            @PathVariable Long id) {

        ViolationDto violation =
                violationService.getViolationById(id);

        return ResponseEntity.ok(violation);
    }

    // Update Violation
    @PutMapping("/{id}")
    public ResponseEntity<ViolationDto> updateViolation(
            @PathVariable Long id,
            @Valid @RequestBody ViolationDto violationDto) {

        ViolationDto updatedViolation =
                violationService.updateViolation(id, violationDto);

        return ResponseEntity.ok(updatedViolation);
    }

    // Delete Violation
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViolation(
            @PathVariable Long id) {

        violationService.deleteViolation(id);

        return ResponseEntity.ok("Violation deleted successfully.");
    }
}