package com.smartexam.backend.service;

import com.smartexam.backend.dto.NotificationDto;

import java.util.List;

public interface NotificationService {

    NotificationDto saveNotification(NotificationDto notificationDto);

    List<NotificationDto> getAllNotifications();

    NotificationDto getNotificationById(Long id);

    NotificationDto updateNotification(Long id, NotificationDto notificationDto);

    void deleteNotification(Long id);
}