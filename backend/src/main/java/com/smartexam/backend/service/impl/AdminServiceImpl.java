package com.smartexam.backend.service.impl;

import com.smartexam.backend.dto.AdminDto;
import com.smartexam.backend.entity.Admin;
import com.smartexam.backend.exception.ResourceNotFoundException;
import com.smartexam.backend.repository.AdminRepository;
import com.smartexam.backend.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepository adminRepository,
                            ModelMapper modelMapper) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AdminDto createAdmin(AdminDto adminDto) {

        Admin admin = modelMapper.map(adminDto, Admin.class);

        Admin savedAdmin = adminRepository.save(admin);

        return modelMapper.map(savedAdmin, AdminDto.class);
    }

    @Override
    public AdminDto getAdminById(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found with id: " + id));

        return modelMapper.map(admin, AdminDto.class);
    }

    @Override
    public List<AdminDto> getAllAdmins() {

        return adminRepository.findAll()
                .stream()
                .map(admin -> modelMapper.map(admin, AdminDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminDto updateAdmin(Long id, AdminDto adminDto) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found with id: " + id));

        admin.setAdminId(adminDto.getAdminId());
        admin.setFirstName(adminDto.getFirstName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setPhoneNumber(adminDto.getPhoneNumber());
        admin.setDesignation(adminDto.getDesignation());
        admin.setActive(adminDto.getActive());

        Admin updatedAdmin = adminRepository.save(admin);

        return modelMapper.map(updatedAdmin, AdminDto.class);
    }

    @Override
    public void deleteAdmin(Long id) {

        Admin admin = adminRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Admin not found with id: " + id));

        adminRepository.delete(admin);
    }
}