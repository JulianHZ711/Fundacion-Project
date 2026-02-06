package com.project.springboot.app.fundacion_project.fundacion_project.child.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "children")
public class Child {

    @Id
    private String document;

    @Column(nullable = false)
    private String firstName;  

    @Column(nullable = false)
    private String lastName;  

    @Column(nullable = false)
    private LocalDate birthDate;  

    @Column(nullable = false)
    private String gender;  

    @Column(nullable = false)
    private String bloodType;  

    @Transient  
    private Integer age;  

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClinicalHistory> clinicalHistories = new ArrayList<>();
    
    // ========== CONSTRUCTORES ==========
    
    public Child() {
    }

    public Child(String document, String firstName, String lastName, 
                 LocalDate birthDate, String gender, String bloodType) {
        this.document = document;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bloodType = bloodType;
    }

    // ========== MÉTODO PARA CALCULAR EDAD ==========
    
    public Integer getAge() {
        if (birthDate == null) {
            return null;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
        // ← Period.between calcula la diferencia entre dos fechas
        // ← .getYears() devuelve solo los años completos
    }

    // NO necesitas setAge() porque se calcula automáticamente

    // ========== GETTERS Y SETTERS ==========

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public List<ClinicalHistory> getClinicalHistories() {
        return clinicalHistories;
    }

    public void setClinicalHistories(List<ClinicalHistory> clinicalHistories) {
        this.clinicalHistories = clinicalHistories;
    }
}