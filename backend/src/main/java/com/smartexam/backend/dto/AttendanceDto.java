package com.smartexam.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDto {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotNull(message = "Attendance status is required")
    private Boolean present;

    @NotNull(message = "Login time is required")
    private LocalDateTime loginTime;

    @NotNull(message = "Logout time is required")
    private LocalDateTime logoutTime;

    @NotNull(message = "Duration is required")
    @Positive(message = "Duration must be greater than 0")
    private Long duration;
}