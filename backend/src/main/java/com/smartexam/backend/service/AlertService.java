package com.smartexam.backend.service;

import com.smartexam.backend.dto.AlertDto;

import java.util.List;

public interface AlertService {

    AlertDto saveAlert(AlertDto alertDto);

    List<AlertDto> getAllAlerts();

    AlertDto getAlertById(Long id);

    AlertDto updateAlert(Long id, AlertDto alertDto);

    void deleteAlert(Long id);
}