package com.smartexam.backend.controller;

import com.smartexam.backend.dto.AlertDto;
import com.smartexam.backend.service.AlertService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@RequiredArgsConstructor
public class AlertController {

    private final AlertService alertService;

    // Create Alert
    @PostMapping
    public ResponseEntity<AlertDto> saveAlert(@Valid @RequestBody AlertDto alertDto) {

        AlertDto savedAlert = alertService.saveAlert(alertDto);

        return new ResponseEntity<>(savedAlert, HttpStatus.CREATED);
    }

    // Get All Alerts
    @GetMapping
    public ResponseEntity<List<AlertDto>> getAllAlerts() {

        List<AlertDto> alerts = alertService.getAllAlerts();

        return ResponseEntity.ok(alerts);
    }

    // Get Alert By ID
    @GetMapping("/{id}")
    public ResponseEntity<AlertDto> getAlertById(@PathVariable Long id) {

        AlertDto alert = alertService.getAlertById(id);

        return ResponseEntity.ok(alert);
    }

    // Update Alert
    @PutMapping("/{id}")
    public ResponseEntity<AlertDto> updateAlert(
            @PathVariable Long id,
            @Valid @RequestBody AlertDto alertDto) {

        AlertDto updatedAlert = alertService.updateAlert(id, alertDto);

        return ResponseEntity.ok(updatedAlert);
    }

    // Delete Alert
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAlert(@PathVariable Long id) {

        alertService.deleteAlert(id);

        return ResponseEntity.ok("Alert deleted successfully.");
    }
}