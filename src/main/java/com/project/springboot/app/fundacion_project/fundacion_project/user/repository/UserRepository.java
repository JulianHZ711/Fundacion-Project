package com.project.springboot.app.fundacion_project.fundacion_project.user.repository;

import org.springframework.data.repository.CrudRepository;

import com.project.springboot.app.fundacion_project.fundacion_project.user.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
