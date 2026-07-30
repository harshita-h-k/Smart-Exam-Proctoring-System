package com.smartexam.backend.service;

import com.smartexam.backend.dto.ViolationDto;

import java.util.List;

public interface ViolationService {

    ViolationDto saveViolation(ViolationDto violationDto);

    List<ViolationDto> getAllViolations();

    ViolationDto getViolationById(Long id);

    ViolationDto updateViolation(Long id, ViolationDto violationDto);

    void deleteViolation(Long id);
}