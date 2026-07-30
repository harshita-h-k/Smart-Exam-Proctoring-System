package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.LoginHistoryDto;
import com.smartexam.backend.entity.LoginHistory;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.LoginHistoryRepository;
import com.smartexam.backend.service.LoginHistoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoginHistoryServiceImpl implements LoginHistoryService {

    private final LoginHistoryRepository loginHistoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public LoginHistoryDto saveLoginHistory(LoginHistoryDto loginHistoryDto) {

        LoginHistory loginHistory =
                modelMapper.map(loginHistoryDto, LoginHistory.class);

        LoginHistory savedLoginHistory =
                loginHistoryRepository.save(loginHistory);

        return modelMapper.map(savedLoginHistory, LoginHistoryDto.class);
    }

    @Override
    public List<LoginHistoryDto> getAllLoginHistory() {

        return loginHistoryRepository.findAll()
                .stream()
                .map(loginHistory ->
                        modelMapper.map(loginHistory, LoginHistoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public LoginHistoryDto getLoginHistoryById(Long id) {

        LoginHistory loginHistory = loginHistoryRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Login History not found with ID: " + id));

        return modelMapper.map(loginHistory, LoginHistoryDto.class);
    }

    @Override
    public LoginHistoryDto updateLoginHistory(Long id,
                                              LoginHistoryDto loginHistoryDto) {

        LoginHistory existingLoginHistory =
                loginHistoryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Login History not found with ID: " + id));

        existingLoginHistory.setUserId(loginHistoryDto.getUserId());
        existingLoginHistory.setIpAddress(loginHistoryDto.getIpAddress());
        existingLoginHistory.setBrowser(loginHistoryDto.getBrowser());
        existingLoginHistory.setOperatingSystem(loginHistoryDto.getOperatingSystem());
        existingLoginHistory.setSuccessful(loginHistoryDto.getSuccessful());

        LoginHistory updatedLoginHistory =
                loginHistoryRepository.save(existingLoginHistory);

        return modelMapper.map(updatedLoginHistory, LoginHistoryDto.class);
    }

    @Override
    public void deleteLoginHistory(Long id) {

        LoginHistory existingLoginHistory =
                loginHistoryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Login History not found with ID: " + id));

        loginHistoryRepository.delete(existingLoginHistory);
    }
}