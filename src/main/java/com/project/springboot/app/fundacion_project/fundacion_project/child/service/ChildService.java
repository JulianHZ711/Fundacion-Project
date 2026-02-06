package com.project.springboot.app.fundacion_project.fundacion_project.child.service;

import java.util.List;

import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildResponseDto;


public interface ChildService {

    ChildResponseDto create(ChildRequestDto dto);

    List<ChildResponseDto> findAll();

    ChildResponseDto findById(String document);

    ChildResponseDto update(String document, ChildRequestDto dto);

    void delete(String document);

}
