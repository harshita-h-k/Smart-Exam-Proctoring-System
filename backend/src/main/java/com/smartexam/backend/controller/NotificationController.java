package com.smartexam.backend.controller;

import com.smartexam.backend.dto.NotificationDto;
import com.smartexam.backend.service.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    // Create Notification
    @PostMapping
    public ResponseEntity<NotificationDto> saveNotification(
            @Valid @RequestBody NotificationDto notificationDto) {

        NotificationDto savedNotification =
                notificationService.saveNotification(notificationDto);

        return new ResponseEntity<>(savedNotification, HttpStatus.CREATED);
    }

    // Get All Notifications
    @GetMapping
    public ResponseEntity<List<NotificationDto>> getAllNotifications() {

        List<NotificationDto> notifications =
                notificationService.getAllNotifications();

        return ResponseEntity.ok(notifications);
    }

    // Get Notification By ID
    @GetMapping("/{id}")
    public ResponseEntity<NotificationDto> getNotificationById(
            @PathVariable Long id) {

        NotificationDto notification =
                notificationService.getNotificationById(id);

        return ResponseEntity.ok(notification);
    }

    // Update Notification
    @PutMapping("/{id}")
    public ResponseEntity<NotificationDto> updateNotification(
            @PathVariable Long id,
            @Valid @RequestBody NotificationDto notificationDto) {

        NotificationDto updatedNotification =
                notificationService.updateNotification(id, notificationDto);

        return ResponseEntity.ok(updatedNotification);
    }

    // Delete Notification
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return ResponseEntity.ok("Notification deleted successfully.");
    }
}