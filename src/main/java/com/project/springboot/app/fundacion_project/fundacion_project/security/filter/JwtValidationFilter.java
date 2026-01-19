package com.project.springboot.app.fundacion_project.fundacion_project.security.filter;

import static com.project.springboot.app.fundacion_project.fundacion_project.security.TokenJwtConfig.CONTENT_TYPE;
import static com.project.springboot.app.fundacion_project.fundacion_project.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.project.springboot.app.fundacion_project.fundacion_project.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.project.springboot.app.fundacion_project.fundacion_project.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.project.springboot.app.fundacion_project.fundacion_project.security.SimpleGrantedAuthorityJsonCreator;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tools.jackson.databind.ObjectMapper;

public class JwtValidationFilter extends BasicAuthenticationFilter {

    public JwtValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
    throws IOException, ServletException {
        //Obtaining the token using the request and the constant we created on the TokenJwtConfig java class
        String header = request.getHeader(HEADER_AUTHORIZATION);

        //If the token received is null or the token does not start with our predefined standard "bearer", then
        // don't validate this session
        if(header == null || !header.startsWith(PREFIX_TOKEN)){
            return;
        }

        //Getting rid of the prefix "bearer" for easier validation
        String token = header.replace(PREFIX_TOKEN, "");

        try{
            //Validating the token using the secret key constant
            Claims claims = Jwts.parser().verifyWith(SECRET_KEY).build().parseSignedClaims(token).getPayload();

            //Getting the username
            String username = claims.getSubject();

            //Getting the role
            Object authorityClaims = claims.get("authorities");

            //Converting the role from an object to a real grantedAuthority object 
            GrantedAuthority authority = new ObjectMapper().readValue(authorityClaims.toString().getBytes(), SimpleGrantedAuthority.class);

            //Logging in using UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, authority);

            //Authenticating the session token
            SecurityContextHolder .getContext().setAuthentication(authenticationToken);

            //Continue with the filter chain
            chain.doFilter(request, response);
        } catch (JwtException e){
            Map<String, String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token JWT no es valido");

            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(CONTENT_TYPE);
        }
    }
}
