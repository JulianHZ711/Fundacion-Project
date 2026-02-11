package com.project.springboot.app.fundacion_project.fundacion_project.child.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.project.springboot.app.fundacion_project.fundacion_project.child.enums.EstadoNino;
import com.project.springboot.app.fundacion_project.fundacion_project.child.enums.TipoDocumento;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public EstadoNino getEstado() {
        return estado;
    }

    public void setEstado(EstadoNino estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Column(nullable = false)
    private String bloodType;  

    @Transient  
    private Integer age;  

     @Column(nullable = false)
    private LocalDate fechaIngreso;  

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private EstadoNino estado;  

    @Lob
    @Column(columnDefinition = "TEXT")
    private String observaciones;  

    @OneToMany(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ClinicalHistory> clinicalHistories = new ArrayList<>();
    
    // ========== CONSTRUCTORES ==========
    
    public Child() {
    }

    public Child(String document, TipoDocumento tipoDocumento, String firstName, String lastName,
                 LocalDate birthDate, String gender, String bloodType, LocalDate fechaIngreso,
                 EstadoNino estado, String observaciones) {
        this.document = document;
        this.tipoDocumento = tipoDocumento;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.bloodType = bloodType;
        this.fechaIngreso = fechaIngreso;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    // ========== MÉTODO PARA CALCULAR EDAD ==========
    
    public Integer getAge() {
        if (birthDate == null) {
            return null;
        }
        return Period.between(birthDate, LocalDate.now()).getYears();
    
    }


    // ========== GETTERS Y SETTERS ==========

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    private TipoDocumento tipoDocumento;

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