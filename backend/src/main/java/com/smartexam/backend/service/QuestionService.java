package com.smartexam.backend.service;

import com.smartexam.backend.dto.QuestionDto;

import java.util.List;

public interface QuestionService {

    QuestionDto saveQuestion(QuestionDto questionDto);

    List<QuestionDto> getAllQuestions();

    QuestionDto getQuestionById(Long id);

    QuestionDto updateQuestion(Long id, QuestionDto questionDto);

    void deleteQuestion(Long id);
}