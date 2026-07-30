package com.smartexam.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ViolationDto {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotBlank(message = "Violation type is required")
    private String violationType;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Confidence score is required")
    @DecimalMin(value = "0.0", message = "Confidence score cannot be negative")
    @DecimalMax(value = "100.0", message = "Confidence score cannot exceed 100")
    private Double confidenceScore;

    @NotBlank(message = "Image path is required")
    private String imagePath;
}