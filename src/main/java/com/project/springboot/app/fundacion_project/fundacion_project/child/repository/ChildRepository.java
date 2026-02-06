package com.project.springboot.app.fundacion_project.fundacion_project.child.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.springboot.app.fundacion_project.fundacion_project.child.model.Child;

public interface ChildRepository extends JpaRepository<Child, String> {
}
