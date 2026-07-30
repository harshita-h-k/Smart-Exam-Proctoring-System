package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.ViolationDto;
import com.smartexam.backend.entity.Violation;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.ViolationRepository;
import com.smartexam.backend.service.ViolationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ViolationServiceImpl implements ViolationService {

    private final ViolationRepository violationRepository;
    private final ModelMapper modelMapper;

    @Override
    public ViolationDto saveViolation(ViolationDto violationDto) {

        Violation violation = modelMapper.map(violationDto, Violation.class);

        Violation savedViolation = violationRepository.save(violation);

        return modelMapper.map(savedViolation, ViolationDto.class);
    }

    @Override
    public List<ViolationDto> getAllViolations() {

        return violationRepository.findAll()
                .stream()
                .map(violation -> modelMapper.map(violation, ViolationDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ViolationDto getViolationById(Long id) {

        Violation violation = violationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Violation not found with ID: " + id));

        return modelMapper.map(violation, ViolationDto.class);
    }

    @Override
    public ViolationDto updateViolation(Long id, ViolationDto violationDto) {

        Violation existingViolation = violationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Violation not found with ID: " + id));

        existingViolation.setStudentId(violationDto.getStudentId());
        existingViolation.setExamId(violationDto.getExamId());
        existingViolation.setViolationType(violationDto.getViolationType());
        existingViolation.setDescription(violationDto.getDescription());
        existingViolation.setConfidenceScore(violationDto.getConfidenceScore());
        existingViolation.setImagePath(violationDto.getImagePath());

        Violation updatedViolation = violationRepository.save(existingViolation);

        return modelMapper.map(updatedViolation, ViolationDto.class);
    }

    @Override
    public void deleteViolation(Long id) {

        Violation existingViolation = violationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Violation not found with ID: " + id));

        violationRepository.delete(existingViolation);
    }
}