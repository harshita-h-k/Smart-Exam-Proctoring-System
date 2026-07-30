package com.smartexam.backend.controller;

import com.smartexam.backend.dto.LoginHistoryDto;
import com.smartexam.backend.service.LoginHistoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login-history")
@RequiredArgsConstructor
public class LoginHistoryController {

    private final LoginHistoryService loginHistoryService;

    // Create Login History
    @PostMapping
    public ResponseEntity<LoginHistoryDto> saveLoginHistory(
            @Valid @RequestBody LoginHistoryDto loginHistoryDto) {

        LoginHistoryDto savedLoginHistory =
                loginHistoryService.saveLoginHistory(loginHistoryDto);

        return new ResponseEntity<>(savedLoginHistory, HttpStatus.CREATED);
    }

    // Get All Login History
    @GetMapping
    public ResponseEntity<List<LoginHistoryDto>> getAllLoginHistory() {

        List<LoginHistoryDto> loginHistoryList =
                loginHistoryService.getAllLoginHistory();

        return ResponseEntity.ok(loginHistoryList);
    }

    // Get Login History By ID
    @GetMapping("/{id}")
    public ResponseEntity<LoginHistoryDto> getLoginHistoryById(
            @PathVariable Long id) {

        LoginHistoryDto loginHistory =
                loginHistoryService.getLoginHistoryById(id);

        return ResponseEntity.ok(loginHistory);
    }

    // Update Login History
    @PutMapping("/{id}")
    public ResponseEntity<LoginHistoryDto> updateLoginHistory(
            @PathVariable Long id,
            @Valid @RequestBody LoginHistoryDto loginHistoryDto) {

        LoginHistoryDto updatedLoginHistory =
                loginHistoryService.updateLoginHistory(id, loginHistoryDto);

        return ResponseEntity.ok(updatedLoginHistory);
    }

    // Delete Login History
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoginHistory(
            @PathVariable Long id) {

        loginHistoryService.deleteLoginHistory(id);

        return ResponseEntity.ok("Login history deleted successfully.");
    }
}