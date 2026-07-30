package com.smartexam.backend.service;

import com.smartexam.backend.dto.PrincipalDto;

import java.util.List;

public interface PrincipalService {

    PrincipalDto savePrincipal(PrincipalDto principalDto);

    List<PrincipalDto> getAllPrincipals();

    PrincipalDto getPrincipalById(Long id);

    PrincipalDto updatePrincipal(Long id, PrincipalDto principalDto);

    void deletePrincipal(Long id);
}