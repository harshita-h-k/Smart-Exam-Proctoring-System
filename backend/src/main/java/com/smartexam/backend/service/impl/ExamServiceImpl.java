package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.ExamDto;
import com.smartexam.backend.entity.Exam;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.ExamRepository;
import com.smartexam.backend.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {

    private final ExamRepository examRepository;
    private final ModelMapper modelMapper;

    @Override
    public ExamDto saveExam(ExamDto examDto) {

        Exam exam = modelMapper.map(examDto, Exam.class);

        Exam savedExam = examRepository.save(exam);

        return modelMapper.map(savedExam, ExamDto.class);
    }

    @Override
    public List<ExamDto> getAllExams() {

        return examRepository.findAll()
                .stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ExamDto getExamById(Long id) {

        Exam exam = examRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found with ID: " + id));

        return modelMapper.map(exam, ExamDto.class);
    }

    @Override
    public ExamDto updateExam(Long id, ExamDto examDto) {

        Exam existingExam = examRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found with ID: " + id));

        existingExam.setExamName(examDto.getExamName());
        existingExam.setSubject(examDto.getSubject());
        existingExam.setDuration(examDto.getDuration());
        existingExam.setStartTime(examDto.getStartTime());
        existingExam.setEndTime(examDto.getEndTime());
        existingExam.setActive(examDto.getActive());

        Exam updatedExam = examRepository.save(existingExam);

        return modelMapper.map(updatedExam, ExamDto.class);
    }

    @Override
    public void deleteExam(Long id) {

        Exam existingExam = examRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Exam not found with ID: " + id));

        examRepository.delete(existingExam);
    }
}