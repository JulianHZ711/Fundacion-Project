package com.project.springboot.app.fundacion_project.fundacion_project.child.model;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;

import jakarta.persistence.Column;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "children")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private Integer age;

    @OneToOne(mappedBy = "child", cascade = CascadeType.ALL, orphanRemoval = true)
    private ClinicalHistory clinicalHistory;
    
    public Child() {
    }

    public Child(Long id, String fullName, Integer age) {
        this.id = id;
        this.fullName = fullName;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    
}
