package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.ResultDto;
import com.smartexam.backend.entity.Result;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.ResultRepository;
import com.smartexam.backend.service.ResultService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ResultServiceImpl implements ResultService {

    private final ResultRepository resultRepository;
    private final ModelMapper modelMapper;

    @Override
    public ResultDto saveResult(ResultDto resultDto) {

        Result result = modelMapper.map(resultDto, Result.class);

        Result savedResult = resultRepository.save(result);

        return modelMapper.map(savedResult, ResultDto.class);
    }

    @Override
    public List<ResultDto> getAllResults() {

        return resultRepository.findAll()
                .stream()
                .map(result -> modelMapper.map(result, ResultDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ResultDto getResultById(Long id) {

        Result result = resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Result not found with ID: " + id));

        return modelMapper.map(result, ResultDto.class);
    }

    @Override
    public ResultDto updateResult(Long id, ResultDto resultDto) {

        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Result not found with ID: " + id));

        existingResult.setStudentId(resultDto.getStudentId());
        existingResult.setExamId(resultDto.getExamId());
        existingResult.setScore(resultDto.getScore());
        existingResult.setGrade(resultDto.getGrade());
        existingResult.setStatus(resultDto.getStatus());
        existingResult.setMalpracticeDetected(resultDto.getMalpracticeDetected());

        Result updatedResult = resultRepository.save(existingResult);

        return modelMapper.map(updatedResult, ResultDto.class);
    }

    @Override
    public void deleteResult(Long id) {

        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Result not found with ID: " + id));

        resultRepository.delete(existingResult);
    }
}