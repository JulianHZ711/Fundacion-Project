package com.project.springboot.app.fundacion_project.fundacion_project.user.service;

import java.util.List;


import com.project.springboot.app.fundacion_project.fundacion_project.user.User;

public interface UserService {
    List<User> findAll();

    User save(User user);
}