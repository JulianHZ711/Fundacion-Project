package com.project.springboot.app.fundacion_project.fundacion_project.user.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.springboot.app.fundacion_project.fundacion_project.user.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
