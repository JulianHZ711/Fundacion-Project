package com.project.springboot.app.fundacion_project.fundacion_project.child.dto;

import java.time.LocalDate;

import com.project.springboot.app.fundacion_project.fundacion_project.child.enums.EstadoNino;
import com.project.springboot.app.fundacion_project.fundacion_project.child.enums.TipoDocumento;


public class ChildResponseDto {
    
    private String document;
    private TipoDocumento tipoDocumento;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String bloodType;
    private LocalDate fechaIngreso;
    private EstadoNino estado;
    private String observaciones;
    private Integer age;

    // ========== GETTERS Y SETTERS ==========

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}