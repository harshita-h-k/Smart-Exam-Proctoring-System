package com.smartexam.backend.controller;

import com.smartexam.backend.dto.AdminDto;
import com.smartexam.backend.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admins")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<AdminDto> createAdmin(
            @Valid @RequestBody AdminDto adminDto) {

        AdminDto savedAdmin = adminService.createAdmin(adminDto);

        return new ResponseEntity<>(savedAdmin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminDto> getAdminById(@PathVariable Long id) {

        AdminDto adminDto = adminService.getAdminById(id);

        return ResponseEntity.ok(adminDto);
    }

    @GetMapping
    public ResponseEntity<List<AdminDto>> getAllAdmins() {

        List<AdminDto> adminList = adminService.getAllAdmins();

        return ResponseEntity.ok(adminList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminDto> updateAdmin(
            @PathVariable Long id,
            @Valid @RequestBody AdminDto adminDto) {

        AdminDto updatedAdmin =
                adminService.updateAdmin(id, adminDto);

        return ResponseEntity.ok(updatedAdmin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAdmin(@PathVariable Long id) {

        adminService.deleteAdmin(id);

        return ResponseEntity.ok("Admin deleted successfully.");
    }
}