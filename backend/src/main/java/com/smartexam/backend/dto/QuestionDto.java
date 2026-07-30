package com.smartexam.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionDto {

    private Long id;

    @NotNull(message = "Exam ID is required")
    private Long examId;

    @NotBlank(message = "Question text is required")
    private String questionText;

    @NotBlank(message = "Option A is required")
    private String optionA;

    @NotBlank(message = "Option B is required")
    private String optionB;

    @NotBlank(message = "Option C is required")
    private String optionC;

    @NotBlank(message = "Option D is required")
    private String optionD;

    @NotBlank(message = "Correct answer is required")
    private String correctAnswer;

    @NotNull(message = "Marks are required")
    @Min(value = 1, message = "Marks must be greater than 0")
    private Integer marks;
}