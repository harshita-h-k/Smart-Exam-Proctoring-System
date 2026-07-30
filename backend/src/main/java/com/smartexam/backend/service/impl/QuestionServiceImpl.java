package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.QuestionDto;
import com.smartexam.backend.entity.Question;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.QuestionRepository;
import com.smartexam.backend.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final ModelMapper modelMapper;

    @Override
    public QuestionDto saveQuestion(QuestionDto questionDto) {

        Question question = modelMapper.map(questionDto, Question.class);

        Question savedQuestion = questionRepository.save(question);

        return modelMapper.map(savedQuestion, QuestionDto.class);
    }

    @Override
    public List<QuestionDto> getAllQuestions() {

        return questionRepository.findAll()
                .stream()
                .map(question -> modelMapper.map(question, QuestionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public QuestionDto getQuestionById(Long id) {

        Question question = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + id));

        return modelMapper.map(question, QuestionDto.class);
    }

    @Override
    public QuestionDto updateQuestion(Long id, QuestionDto questionDto) {

        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + id));

        existingQuestion.setExamId(questionDto.getExamId());
        existingQuestion.setQuestionText(questionDto.getQuestionText());
        existingQuestion.setOptionA(questionDto.getOptionA());
        existingQuestion.setOptionB(questionDto.getOptionB());
        existingQuestion.setOptionC(questionDto.getOptionC());
        existingQuestion.setOptionD(questionDto.getOptionD());
        existingQuestion.setCorrectAnswer(questionDto.getCorrectAnswer());
        existingQuestion.setMarks(questionDto.getMarks());

        Question updatedQuestion = questionRepository.save(existingQuestion);

        return modelMapper.map(updatedQuestion, QuestionDto.class);
    }

    @Override
    public void deleteQuestion(Long id) {

        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Question not found with ID: " + id));

        questionRepository.delete(existingQuestion);
    }
}