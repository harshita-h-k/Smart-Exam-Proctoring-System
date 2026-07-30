package com.smartexam.backend.service;

import com.smartexam.backend.dto.ResultDto;

import java.util.List;

public interface ResultService {

    ResultDto saveResult(ResultDto resultDto);

    List<ResultDto> getAllResults();

    ResultDto getResultById(Long id);

    ResultDto updateResult(Long id, ResultDto resultDto);

    void deleteResult(Long id);
}