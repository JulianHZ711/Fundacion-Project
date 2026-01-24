package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model;

import com.project.springboot.app.fundacion_project.fundacion_project.child.model.Child;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clinical_histories")
public class ClinicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 500)
    private String diagnosis;

    @Column(length = 500)
    private String allergies;

    @OneToOne
    @JoinColumn(name = "child_id", nullable = false, unique = true)
    private Child child;

    public ClinicalHistory() {
    }

    public ClinicalHistory(Long id, String diagnosis, String allergies, Child child) {
        this.id = id;
        this.diagnosis = diagnosis;
        this.allergies = allergies;
        this.child = child;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }
}
