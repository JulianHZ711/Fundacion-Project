package com.project.springboot.app.fundacion_project.fundacion_project.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springboot.app.fundacion_project.fundacion_project.role.Role;
import com.project.springboot.app.fundacion_project.fundacion_project.role.repository.RoleRepository;
import com.project.springboot.app.fundacion_project.fundacion_project.user.User;
import com.project.springboot.app.fundacion_project.fundacion_project.user.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Transactional
    @Override
    public User save(User user) {
        // Optional<Role> optionalRoleUser = roleRepository.findByName(user.getRole());

        // optionalRoleUser.ifPresent(role -> user.setRole(role));

        // if(user.isAdmin()){
        //     Optional<Role> optionalRoleAdmin = roleRepository.findByName(user.getRoles());
        //     optionalRoleAdmin.ifPresent(role -> roles.add(role));
        // }

        //Encoding the password using PasswordEncoder
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repository.save(user);
    }

}
