package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.service;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryResponseDto;

public interface ClinicalHistoryService {

    ClinicalHistoryResponseDto createClinicalHistory(Long childId, ClinicalHistoryRequestDto dto);
}
