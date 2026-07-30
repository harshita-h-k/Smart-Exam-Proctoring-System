package com.smartexam.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertDto {

    private Long id;

    @NotNull(message = "Student ID is required")
    private Long studentId;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotBlank(message = "Alert type is required")
    private String alertType;

    @NotBlank(message = "Severity is required")
    private String severity;

    @NotBlank(message = "Message is required")
    private String message;

    @NotNull(message = "Resolved status is required")
    private Boolean resolved;
}