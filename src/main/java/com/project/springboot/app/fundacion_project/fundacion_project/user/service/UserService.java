package com.project.springboot.app.fundacion_project.fundacion_project.user.service;

import java.util.List;


import com.project.springboot.app.fundacion_project.fundacion_project.user.User;
import com.project.springboot.app.fundacion_project.fundacion_project.user.dto.UserDTO;

public interface UserService {
    List<User> findAll();

    //Generic method for fixing type conflicts
    User save(UserDTO user);
}