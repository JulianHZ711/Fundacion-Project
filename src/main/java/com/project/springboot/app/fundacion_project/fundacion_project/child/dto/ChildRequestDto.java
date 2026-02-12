package com.project.springboot.app.fundacion_project.fundacion_project.child.dto;

import java.time.LocalDate;

import com.project.springboot.app.fundacion_project.fundacion_project.child.enums.EstadoNino;
import com.project.springboot.app.fundacion_project.fundacion_project.child.enums.TipoDocumento;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class ChildRequestDto {

    @NotBlank(message = "Document is required")
    @Size(min = 5, max = 20, message = "Document must be between 5 and 20 characters")
    private String document;

    @NotNull(message = "Document type is required")
    private TipoDocumento tipoDocumento;

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birth date must be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "Gender is required")
    @Pattern(regexp = "^(M|F|Otro)$", message = "Gender must be M, F, or Otro")
    private String gender;

    @NotBlank(message = "Blood type is required")
    @Pattern(regexp = "^(O\\+|O-|A\\+|A-|B\\+|B-|AB\\+|AB-)$", 
             message = "Blood type must be O+, O-, A+, A-, B+, B-, AB+, or AB-")
    private String bloodType;

    @NotNull(message = "Admission date is required")
    @PastOrPresent(message = "Admission date must be in the past or present")
    private LocalDate fechaIngreso;

    @NotNull(message = "Status is required")
    private EstadoNino estado;

    @Size(max = 1000, message = "Observations must not exceed 1000 characters")
    private String observaciones;

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
}