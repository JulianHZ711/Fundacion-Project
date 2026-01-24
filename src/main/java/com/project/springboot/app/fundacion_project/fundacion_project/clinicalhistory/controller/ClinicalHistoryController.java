package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.service.ClinicalHistoryService;

@RestController
@RequestMapping("/api/children/{childId}/clinical-history")
@PreAuthorize("hasRole('ADMIN')")
public class ClinicalHistoryController {
    
       private final ClinicalHistoryService clinicalHistoryService;

    public ClinicalHistoryController(ClinicalHistoryService clinicalHistoryService) {
        this.clinicalHistoryService = clinicalHistoryService;
    }

    @PostMapping
    public ResponseEntity<ClinicalHistoryResponseDto> create(
            @PathVariable Long childId,
            @RequestBody ClinicalHistoryRequestDto dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clinicalHistoryService.createClinicalHistory(childId, dto));
    }
}
