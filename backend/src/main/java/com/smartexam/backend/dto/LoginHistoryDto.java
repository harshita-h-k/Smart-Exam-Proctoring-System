package com.smartexam.backend.dto;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginHistoryDto {

    private Long id;

    @NotNull(message = "User ID is required")
    private Long userId;

    @NotBlank(message = "IP Address is required")
    private String ipAddress;

    @NotBlank(message = "Browser is required")
    private String browser;

    @NotBlank(message = "Operating System is required")
    private String operatingSystem;

    @NotNull(message = "Login status is required")
    private Boolean successful;
}