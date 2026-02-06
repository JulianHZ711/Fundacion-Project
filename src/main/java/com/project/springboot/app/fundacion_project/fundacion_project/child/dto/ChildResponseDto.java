package com.project.springboot.app.fundacion_project.fundacion_project.child.dto;

import java.time.LocalDate;

public class ChildResponseDto {
    
    private String document;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String bloodType;
    private Integer age;  // ← Se incluye en la respuesta (calculada)

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}