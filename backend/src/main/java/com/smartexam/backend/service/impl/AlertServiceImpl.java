package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.AlertDto;
import com.smartexam.backend.entity.Alert;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.AlertRepository;
import com.smartexam.backend.service.AlertService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AlertServiceImpl implements AlertService {

    private final AlertRepository alertRepository;
    private final ModelMapper modelMapper;

    @Override
    public AlertDto saveAlert(AlertDto alertDto) {

        Alert alert = modelMapper.map(alertDto, Alert.class);

        Alert savedAlert = alertRepository.save(alert);

        return modelMapper.map(savedAlert, AlertDto.class);
    }

    @Override
    public List<AlertDto> getAllAlerts() {

        return alertRepository.findAll()
                .stream()
                .map(alert -> modelMapper.map(alert, AlertDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AlertDto getAlertById(Long id) {

        Alert alert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with ID: " + id));

        return modelMapper.map(alert, AlertDto.class);
    }

    @Override
    public AlertDto updateAlert(Long id, AlertDto alertDto) {

        Alert existingAlert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with ID: " + id));

        existingAlert.setStudentId(alertDto.getStudentId());
        existingAlert.setExamId(alertDto.getExamId());
        existingAlert.setAlertType(alertDto.getAlertType());
        existingAlert.setSeverity(alertDto.getSeverity());
        existingAlert.setMessage(alertDto.getMessage());
        existingAlert.setResolved(alertDto.getResolved());

        Alert updatedAlert = alertRepository.save(existingAlert);

        return modelMapper.map(updatedAlert, AlertDto.class);
    }

    @Override
    public void deleteAlert(Long id) {

        Alert existingAlert = alertRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Alert not found with ID: " + id));

        alertRepository.delete(existingAlert);
    }
}