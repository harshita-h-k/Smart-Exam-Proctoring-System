package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.NotificationDto;
import com.smartexam.backend.entity.Notification;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.NotificationRepository;
import com.smartexam.backend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public NotificationDto saveNotification(NotificationDto notificationDto) {

        Notification notification = modelMapper.map(notificationDto, Notification.class);

        Notification savedNotification = notificationRepository.save(notification);

        return modelMapper.map(savedNotification, NotificationDto.class);
    }

    @Override
    public List<NotificationDto> getAllNotifications() {

        return notificationRepository.findAll()
                .stream()
                .map(notification -> modelMapper.map(notification, NotificationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public NotificationDto getNotificationById(Long id) {

        Notification notification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with ID: " + id));

        return modelMapper.map(notification, NotificationDto.class);
    }

    @Override
    public NotificationDto updateNotification(Long id, NotificationDto notificationDto) {

        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with ID: " + id));

        existingNotification.setUserId(notificationDto.getUserId());
        existingNotification.setTitle(notificationDto.getTitle());
        existingNotification.setMessage(notificationDto.getMessage());
        existingNotification.setReadStatus(notificationDto.getReadStatus());

        Notification updatedNotification = notificationRepository.save(existingNotification);

        return modelMapper.map(updatedNotification, NotificationDto.class);
    }

    @Override
    public void deleteNotification(Long id) {

        Notification existingNotification = notificationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Notification not found with ID: " + id));

        notificationRepository.delete(existingNotification);
    }
}