package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto;

public class ClinicalHistoryRequestDto {

    private String diagnosis;
    private String allergies;

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }
}
