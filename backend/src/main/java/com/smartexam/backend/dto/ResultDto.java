package com.smartexam.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDto {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotNull(message = "Score is required")
    @PositiveOrZero(message = "Score cannot be negative")
    private Double score;

    @NotBlank(message = "Grade is required")
    private String grade;

    @NotBlank(message = "Status is required")
    private String status;

    @NotNull(message = "Malpractice status is required")
    private Boolean malpracticeDetected;
}