package com.smartexam.backend.service;

import com.smartexam.backend.dto.LoginHistoryDto;

import java.util.List;

public interface LoginHistoryService {

    LoginHistoryDto saveLoginHistory(LoginHistoryDto loginHistoryDto);

    List<LoginHistoryDto> getAllLoginHistory();

    LoginHistoryDto getLoginHistoryById(Long id);

    LoginHistoryDto updateLoginHistory(Long id, LoginHistoryDto loginHistoryDto);

    void deleteLoginHistory(Long id);
}