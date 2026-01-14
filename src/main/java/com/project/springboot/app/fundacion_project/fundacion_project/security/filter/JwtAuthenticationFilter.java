package com.project.springboot.app.fundacion_project.fundacion_project.security.filter;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.springboot.app.fundacion_project.fundacion_project.user.dto.UserDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;

//This class calls UserDetailsService class in order to authenticate an user in the database
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(
            org.springframework.security.authentication.AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    //Deserializing the object received (DTO) as a JSON and convert it into a Java class
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        UserDTO userDto = null;
        String username = null;
        String password = null;

        try {
            userDto = new ObjectMapper().readValue(request.getInputStream(), UserDTO.class);
            username = userDto.getUsername();
            password = userDto.getPassword();
        } catch (StreamReadException | DatabindException | IOException  e) {
            e.printStackTrace();
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
            username, 
            password
        );

        return authenticationManager.authenticate(authenticationToken);
    }

    
}
