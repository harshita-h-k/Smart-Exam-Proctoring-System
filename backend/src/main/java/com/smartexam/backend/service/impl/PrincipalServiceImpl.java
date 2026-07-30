package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.PrincipalDto;
import com.smartexam.backend.entity.Principal;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.PrincipalRepository;
import com.smartexam.backend.service.PrincipalService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrincipalServiceImpl implements PrincipalService {

    private final PrincipalRepository principalRepository;
    private final ModelMapper modelMapper;

    @Override
    public PrincipalDto savePrincipal(PrincipalDto principalDto) {

        Principal principal = modelMapper.map(principalDto, Principal.class);

        Principal savedPrincipal = principalRepository.save(principal);

        return modelMapper.map(savedPrincipal, PrincipalDto.class);
    }

    @Override
    public List<PrincipalDto> getAllPrincipals() {

        return principalRepository.findAll()
                .stream()
                .map(principal -> modelMapper.map(principal, PrincipalDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PrincipalDto getPrincipalById(Long id) {

        Principal principal = principalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Principal not found with ID: " + id));

        return modelMapper.map(principal, PrincipalDto.class);
    }

    @Override
    public PrincipalDto updatePrincipal(Long id, PrincipalDto principalDto) {

        Principal existingPrincipal = principalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Principal not found with ID: " + id));

        existingPrincipal.setUsername(principalDto.getUsername());
        existingPrincipal.setPassword(principalDto.getPassword());
        existingPrincipal.setEmail(principalDto.getEmail());
        existingPrincipal.setRole(principalDto.getRole());
        existingPrincipal.setActive(principalDto.getActive());

        Principal updatedPrincipal = principalRepository.save(existingPrincipal);

        return modelMapper.map(updatedPrincipal, PrincipalDto.class);
    }

    @Override
    public void deletePrincipal(Long id) {

        Principal existingPrincipal = principalRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Principal not found with ID: " + id));

        principalRepository.delete(existingPrincipal);
    }
}