package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;

public interface ClinicalHistoryService {

    ClinicalHistoryResponseDto upload(String childDocument, MultipartFile file, String fileName) throws IOException;

    List<ClinicalHistoryResponseDto> findByChild(String childDocument);

    ClinicalHistoryResponseDto findById(Long id);
    
    ClinicalHistory findEntityById(Long id);

    void delete(Long id);
}
