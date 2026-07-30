package com.smartexam.backend.controller;

import com.smartexam.backend.dto.AttendanceDto;
import com.smartexam.backend.service.AttendanceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    // Create Attendance
    @PostMapping
    public ResponseEntity<AttendanceDto> saveAttendance(@Valid @RequestBody AttendanceDto attendanceDto) {

        AttendanceDto savedAttendance = attendanceService.saveAttendance(attendanceDto);

        return new ResponseEntity<>(savedAttendance, HttpStatus.CREATED);
    }

    // Get All Attendance
    @GetMapping
    public ResponseEntity<List<AttendanceDto>> getAllAttendance() {

        List<AttendanceDto> attendanceList = attendanceService.getAllAttendance();

        return ResponseEntity.ok(attendanceList);
    }

    // Get Attendance By ID
    @GetMapping("/{id}")
    public ResponseEntity<AttendanceDto> getAttendanceById(@PathVariable Long id) {

        AttendanceDto attendance = attendanceService.getAttendanceById(id);

        return ResponseEntity.ok(attendance);
    }

    // Update Attendance
    @PutMapping("/{id}")
    public ResponseEntity<AttendanceDto> updateAttendance(
            @PathVariable Long id,
            @Valid @RequestBody AttendanceDto attendanceDto) {

        AttendanceDto updatedAttendance =
                attendanceService.updateAttendance(id, attendanceDto);

        return ResponseEntity.ok(updatedAttendance);
    }

    // Delete Attendance
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAttendance(@PathVariable Long id) {

        attendanceService.deleteAttendance(id);

        return ResponseEntity.ok("Attendance deleted successfully.");
    }
}