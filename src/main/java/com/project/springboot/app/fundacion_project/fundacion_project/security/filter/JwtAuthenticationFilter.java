package com.project.springboot.app.fundacion_project.fundacion_project.security.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.project.springboot.app.fundacion_project.fundacion_project.user.dto.UserDTO;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.core.exc.StreamReadException;
import tools.jackson.databind.DatabindException;
import tools.jackson.databind.ObjectMapper;

import static com.project.springboot.app.fundacion_project.fundacion_project.security.TokenJwtConfig.*;
//This class calls UserDetailsService class in order to authenticate an user in the database.
//This filter is for authenticating and generating the token, and then we validate if its correct.
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

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
    Authentication authResult) throws IOException, ServletException {
        //Obtaining the username by calling authResult.getPrincipal which returns an object depending on the class
        // we want to authenticate (in our case, UserDTO)
        User user = (User) authResult.getPrincipal();
        String username = user.getUsername();
        Object role = authResult.getCredentials(); //Getting the roles

        //Claims are data, they are part of the payload. We can add more claims to our generated token
        Claims claims = Jwts.claims().build();
        claims.put("authorities", role);

        //Building the JWT token using their own secret key for each session
        String token = Jwts.builder()
                        .subject(username)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis() + 3600000)) //1h duration token
                        .issuedAt(new Date())
                        .signWith(SECRET_KEY)
                        .compact();

        //Sending the token to the client
        response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN + token);

        //Creating a JSON response, more front-end-friendly
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("username", username);
        body.put("message", String.format("Hola %s, has iniciado sesion con exito!", username));

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
        response.setContentType("application/json");
        response.setStatus(200);
    }

    
}
