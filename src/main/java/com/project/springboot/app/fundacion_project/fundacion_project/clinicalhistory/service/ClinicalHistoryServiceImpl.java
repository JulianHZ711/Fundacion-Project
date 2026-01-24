package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.service;

import org.springframework.stereotype.Service;

import com.project.springboot.app.fundacion_project.fundacion_project.child.model.Child;
import com.project.springboot.app.fundacion_project.fundacion_project.child.repository.ChildRepository;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.repository.ClinicalHistoryRepository;

@Service
public class ClinicalHistoryServiceImpl implements ClinicalHistoryService {

      private final ChildRepository childRepository;
    private final ClinicalHistoryRepository clinicalHistoryRepository;

    public ClinicalHistoryServiceImpl(
            ChildRepository childRepository,
            ClinicalHistoryRepository clinicalHistoryRepository) {
        this.childRepository = childRepository;
        this.clinicalHistoryRepository = clinicalHistoryRepository;
    }

    @Override
    public ClinicalHistoryResponseDto createClinicalHistory(Long childId, ClinicalHistoryRequestDto dto) {

        Child child = childRepository.findById(childId)
                .orElseThrow(() -> new RuntimeException("Child not found"));

        ClinicalHistory history = new ClinicalHistory();
        history.setDiagnosis(dto.getDiagnosis());
        history.setAllergies(dto.getAllergies());
        history.setChild(child);

        history = clinicalHistoryRepository.save(history);

        ClinicalHistoryResponseDto response = new ClinicalHistoryResponseDto();
        response.setId(history.getId());
        response.setDiagnosis(history.getDiagnosis());
        response.setAllergies(history.getAllergies());
        response.setChildId(child.getId());

        return response;
    }
}
