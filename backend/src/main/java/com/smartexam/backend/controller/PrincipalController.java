package com.smartexam.backend.controller;

import com.smartexam.backend.dto.PrincipalDto;
import com.smartexam.backend.service.PrincipalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/principals")
@RequiredArgsConstructor
public class PrincipalController {

    private final PrincipalService principalService;

    // Create Principal
    @PostMapping
    public ResponseEntity<PrincipalDto> savePrincipal(@Valid @RequestBody PrincipalDto principalDto) {

        PrincipalDto savedPrincipal = principalService.savePrincipal(principalDto);

        return new ResponseEntity<>(savedPrincipal, HttpStatus.CREATED);
    }

    // Get All Principals
    @GetMapping
    public ResponseEntity<List<PrincipalDto>> getAllPrincipals() {

        List<PrincipalDto> principals = principalService.getAllPrincipals();

        return ResponseEntity.ok(principals);
    }

    // Get Principal By ID
    @GetMapping("/{id}")
    public ResponseEntity<PrincipalDto> getPrincipalById(@PathVariable Long id) {

        PrincipalDto principal = principalService.getPrincipalById(id);

        return ResponseEntity.ok(principal);
    }

    // Update Principal
    @PutMapping("/{id}")
    public ResponseEntity<PrincipalDto> updatePrincipal(
            @PathVariable Long id,
            @Valid @RequestBody PrincipalDto principalDto) {

        PrincipalDto updatedPrincipal = principalService.updatePrincipal(id, principalDto);

        return ResponseEntity.ok(updatedPrincipal);
    }

    // Delete Principal
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePrincipal(@PathVariable Long id) {

        principalService.deletePrincipal(id);

        return ResponseEntity.ok("Principal deleted successfully.");
    }
}