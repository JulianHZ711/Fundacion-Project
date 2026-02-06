package com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.controller;

import java.io.IOException;
import java.util.List;

import jakarta.validation.constraints.NotNull;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.dto.ClinicalHistoryResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.model.ClinicalHistory;
import com.project.springboot.app.fundacion_project.fundacion_project.clinicalhistory.service.ClinicalHistoryService;



@RestController
@RequestMapping("/clinical-histories")
@Validated
public class ClinicalHistoryController {

    private final ClinicalHistoryService service;

    public ClinicalHistoryController(ClinicalHistoryService service) {
        this.service = service;
    }

    // ================= UPLOAD PDF =================

    @PostMapping("/upload")
    public ResponseEntity<ClinicalHistoryResponseDto> upload(
            @RequestParam("childDocument") @NotNull(message = "childDocument is required") String childDocument,
            @RequestParam("file") MultipartFile file,
            @RequestParam("fileName") @NotNull(message = "fileName is required") String fileName) throws IOException {

        return ResponseEntity.ok(service.upload(childDocument, file, fileName));
    }

    // ================= LIST BY CHILD =================

    @GetMapping("/child/{childDocument}")
    public ResponseEntity<List<ClinicalHistoryResponseDto>> findByChild(
            @PathVariable String childDocument) {

        return ResponseEntity.ok(service.findByChild(childDocument));
    }

    // ================= FIND BY ID =================

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalHistoryResponseDto> findById(@PathVariable Long id) {

        return ResponseEntity.ok(service.findById(id));
    }

    // ================= DOWNLOAD PDF =================

    
    @GetMapping("/download/{id}")
        public ResponseEntity<byte[]> download(@PathVariable Long id) {
            
            ClinicalHistory history = service.findEntityById(id);
            
            // Sanitizar el nombre del archivo
            String fileName = history.getFileName().replaceAll("[^a-zA-Z0-9.-]", "_");
            
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + fileName + "\"")
                    .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
                    .contentLength(history.getFile().length)
                    .body(history.getFile());
        }

    // ================= DELETE =================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
