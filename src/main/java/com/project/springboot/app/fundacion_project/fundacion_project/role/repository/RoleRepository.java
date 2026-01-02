package com.project.springboot.app.fundacion_project.fundacion_project.role.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.springboot.app.fundacion_project.fundacion_project.role.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);
}