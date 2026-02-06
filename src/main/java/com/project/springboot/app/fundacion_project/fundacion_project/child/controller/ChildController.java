package com.project.springboot.app.fundacion_project.fundacion_project.child.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.service.ChildService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/children")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

     @GetMapping
    public List<ChildResponseDto> findAll() {
        return childService.findAll();
    }

    @GetMapping("/{document}")
    public ChildResponseDto findById(@PathVariable String document) {
        return childService.findById(document);
    }

    @PostMapping
    public ResponseEntity<?> create(
            @Valid @RequestBody ChildRequestDto dto,
            BindingResult result) {

        if (result.hasErrors()) return validation(result);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(childService.create(dto));
    }

    @PutMapping("/{document}")
    public ChildResponseDto update(
            @PathVariable String document,
            @Valid @RequestBody ChildRequestDto dto) {

        return childService.update(document, dto);
    }

    @DeleteMapping("/{document}")
    public ResponseEntity<Void> delete(@PathVariable String document) {
        childService.delete(document);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<?> validation(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(err ->
                errors.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errors);
    }

    
}
