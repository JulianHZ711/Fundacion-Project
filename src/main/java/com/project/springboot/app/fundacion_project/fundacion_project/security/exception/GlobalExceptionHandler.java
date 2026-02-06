package com.project.springboot.app.fundacion_project.fundacion_project.security.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.exception.ClinicalHistoryNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(err ->
                        errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String,String>> handleNotFound() {

        Map<String,String> error = new HashMap<>();
        error.put("error", "Resource not found");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    // ========== Clinical History Not Found ==========
    
    @ExceptionHandler(ClinicalHistoryNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleClinicalHistoryNotFound(
            ClinicalHistoryNotFoundException ex) {
        
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", HttpStatus.NOT_FOUND.value());
        error.put("error", "Not Found");
        error.put("message", ex.getMessage());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}