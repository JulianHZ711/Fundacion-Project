package com.project.springboot.app.fundacion_project.fundacion_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.project.springboot.app.fundacion_project.fundacion_project.security.filter.JwtAuthenticationFilter;
import com.project.springboot.app.fundacion_project.fundacion_project.security.filter.JwtValidationFilter;

@Configuration
public class SpringSecurityConfig {
    //Injecting the dependencies in order to work with the tokens we previously configured
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    AuthenticationManager authenticationManager() throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    //Initializing passwordEncoder as a bean so it can be used across the app.
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //Adding the filters to validate all the requests, authorize or deny permissions, etc.
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        //All the user routes are public in order for users to create their own account, but the rest of the
        // routes are denied because the user needs to be authenticated.
        return http.authorizeHttpRequests( (auth) -> auth
        .requestMatchers("/login").permitAll()
        .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
        .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
        .anyRequest().authenticated())
        .addFilter(new JwtAuthenticationFilter(authenticationManager())) //Adding the authentications
        .addFilter(new JwtValidationFilter(authenticationManager()))
        .csrf(config -> config.disable()) // To avoid vulnerabilities
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .build(); //Session creationg policy will be STATELESS so everything authentication-related will be handled by the token
    }
}
