package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;

import java.util.Optional;

public interface ClinicalHistoryRepository extends JpaRepository<ClinicalHistory, Long> {
    Optional<ClinicalHistory> findByChildId(Long childId);
}
