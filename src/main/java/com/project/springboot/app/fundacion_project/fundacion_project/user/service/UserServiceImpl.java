package com.project.springboot.app.fundacion_project.fundacion_project.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springboot.app.fundacion_project.fundacion_project.user.User;
import com.project.springboot.app.fundacion_project.fundacion_project.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        return repository.save(user);
    }

}
