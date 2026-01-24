package com.project.springboot.app.fundacion_project.fundacion_project.child.service;

import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildResponseDto;

public interface ChildService {

     ChildResponseDto createChild(ChildRequestDto dto);
}
