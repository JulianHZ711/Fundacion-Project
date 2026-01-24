package com.project.springboot.app.fundacion_project.fundacion_project.child.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.service.ChildService;

@RestController
@RequestMapping("/api/children")
@PreAuthorize("hasRole('ADMIN')")
public class ChildController {

    private final ChildService childService;

    public ChildController(ChildService childService) {
        this.childService = childService;
    }

    @PostMapping
    public ResponseEntity<ChildResponseDto> create(@RequestBody ChildRequestDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(childService.createChild(dto));
    }
}
