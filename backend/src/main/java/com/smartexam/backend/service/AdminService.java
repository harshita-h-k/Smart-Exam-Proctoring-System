package com.smartexam.backend.service;

import com.smartexam.backend.dto.AdminDto;

import java.util.List;

public interface AdminService {

    AdminDto createAdmin(AdminDto adminDto);

    AdminDto getAdminById(Long id);

    List<AdminDto> getAllAdmins();

    AdminDto updateAdmin(Long id, AdminDto adminDto);

    void deleteAdmin(Long id);
}