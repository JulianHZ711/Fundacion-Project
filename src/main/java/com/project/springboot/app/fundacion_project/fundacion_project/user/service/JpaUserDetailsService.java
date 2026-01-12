package com.project.springboot.app.fundacion_project.fundacion_project.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.springboot.app.fundacion_project.fundacion_project.user.User;
import com.project.springboot.app.fundacion_project.fundacion_project.user.repository.UserRepository;

//Implementing UserDetailsService for more security and validation in the login endpoint
@Service
public class JpaUserDetailsService implements UserDetailsService {
    //We have to inject the userRepository in order to validate the user
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = repository.findByUsername(username);

        if(userOptional.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usernae %s not found", username));
        }

        //Getting user info
        User user = userOptional.orElseThrow();

        //Converting the role to a list in order to avoid errors because spring security constructor requires a list
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().getName()));

        return new org.springframework.security.core.userdetails.User(
            user.getUsername(),
            user.getPassword(),
            user.isEnabled(),
            true,
            true,
            true,
            authorities
        );
    }

}
