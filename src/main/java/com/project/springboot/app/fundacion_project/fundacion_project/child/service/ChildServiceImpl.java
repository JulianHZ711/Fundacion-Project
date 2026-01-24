package com.project.springboot.app.fundacion_project.fundacion_project.child.service;

import org.springframework.stereotype.Service;

import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildRequestDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.dto.ChildResponseDto;
import com.project.springboot.app.fundacion_project.fundacion_project.child.model.Child;
import com.project.springboot.app.fundacion_project.fundacion_project.child.repository.ChildRepository;

@Service
public class ChildServiceImpl implements ChildService {

    private final ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        this.childRepository = childRepository;
    }

    @Override
    public ChildResponseDto createChild(ChildRequestDto dto) {

        Child child = new Child();
        child.setFullName(dto.getFullName());
        child.setAge(dto.getAge());

        child = childRepository.save(child);

        ChildResponseDto response = new ChildResponseDto();
        response.setId(child.getId());
        response.setFullName(child.getFullName());
        response.setAge(child.getAge());

        return response;
    }

    
}
