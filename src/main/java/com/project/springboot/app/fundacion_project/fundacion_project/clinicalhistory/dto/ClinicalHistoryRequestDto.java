package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto;

import jakarta.validation.constraints.NotNull;

public class ClinicalHistoryRequestDto {

    @NotNull(message = "childId is required")
    private Long childId;

    public ClinicalHistoryRequestDto() {
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }
}

