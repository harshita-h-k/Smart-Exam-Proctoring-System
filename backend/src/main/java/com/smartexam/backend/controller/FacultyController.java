package com.smartexam.backend.controller;

import com.smartexam.backend.dto.FacultyDto;
import com.smartexam.backend.service.FacultyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculties")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FacultyController {

    private final FacultyService facultyService;

    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(
            @Valid @RequestBody FacultyDto facultyDto) {

        FacultyDto savedFaculty = facultyService.createFaculty(facultyDto);

        return new ResponseEntity<>(savedFaculty, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable Long id) {

        FacultyDto facultyDto = facultyService.getFacultyById(id);

        return ResponseEntity.ok(facultyDto);
    }

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {

        List<FacultyDto> facultyList = facultyService.getAllFaculties();

        return ResponseEntity.ok(facultyList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(
            @PathVariable Long id,
            @Valid @RequestBody FacultyDto facultyDto) {

        FacultyDto updatedFaculty =
                facultyService.updateFaculty(id, facultyDto);

        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFaculty(@PathVariable Long id) {

        facultyService.deleteFaculty(id);

        return ResponseEntity.ok("Faculty deleted successfully.");
    }
}