package com.project.springboot.app.fundacion_project.fundacion_project.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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

        // All the user routes are public in order for users to create their own account,
        // but the rest of the routes are denied because the user needs to be authenticated.
        return http.authorizeHttpRequests(auth -> auth

            // Public login endpoint
            .requestMatchers("/login").permitAll()

            // USERS → only ADMIN
            .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
            .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")

            // CHILDREN

            // Create / Update / Delete children → ADMIN only
            .requestMatchers(HttpMethod.POST, "/children").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/children/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.DELETE, "/children/**").hasRole("ADMIN")

            // Read children → all roles
            .requestMatchers(HttpMethod.GET, "/children/**")
            .hasAnyRole("ADMIN", "COORDINATOR", "NUTRITIONIST", "PSYCHOLOGIST", "SOCIAL_WORKER")

            // CLINICAL HISTORY
            // Full CRUD access for all roles
            .requestMatchers("/clinical-histories/**")
            .hasAnyRole("ADMIN", "COORDINATOR", "NUTRITIONIST", "PSYCHOLOGIST", "SOCIAL_WORKER")

            // Any other request must be authenticated
            .anyRequest().authenticated()
        )

        // Adding the authentications
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .addFilter(new JwtValidationFilter(authenticationManager()))

        // To avoid vulnerabilities
        .csrf(config -> config.disable())

        // Passing the cors config
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))

        // Session creation policy will be STATELESS so everything authentication-related will be handled by the token
        .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

        .build();
    }


    //Setting up the beans for the CORS configuration
    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config); //Applying the configuration set up done on the previous lines to all app routes

        return source;
    }

    //Applying a filter to the corst config so it can be always applied to any route
    @Bean
    FilterRegistrationBean<CorsFilter> corsFilter(){
        FilterRegistrationBean<CorsFilter> corsBean = new FilterRegistrationBean<>(
            new CorsFilter(corsConfigurationSource())
        );

        corsBean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return corsBean;
    }
}
