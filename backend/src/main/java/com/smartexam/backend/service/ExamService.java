package com.smartexam.backend.service;

import com.smartexam.backend.dto.ExamDto;

import java.util.List;

public interface ExamService {

    ExamDto saveExam(ExamDto examDto);

    List<ExamDto> getAllExams();

    ExamDto getExamById(Long id);

    ExamDto updateExam(Long id, ExamDto examDto);

    void deleteExam(Long id);
}