package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.exception;

public class ClinicalHistoryNotFoundException extends RuntimeException {
      public ClinicalHistoryNotFoundException(Long id) {
        super("Historia clínica con ID " + id + " no encontrada");
    }
    
    public ClinicalHistoryNotFoundException(String message) {
        super(message);
    }
}
